# proxy-run-catching

## 문제 상황

JpaRepository에 getReferenceById를 통해 조회를 진행하면 실제 데이터베이스에 조회하지 않고 프록시 객체를 조회한다. 
칼럼에 최초 접근 할 때 데이터베이스에 실제 쿼리를 조회한다.

```java
@NoRepositoryBean
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    // ...
    /**
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link javax.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     * @since 2.7
     */
    T getReferenceById(ID id);
    // ...
}
```

이것을 runCatching을 통해 조회하려 할 때 id를 통해 접근할 경우 예외가 Result에 담기지 않고 밖으로 노출되는 문제가 발생했다. 
이것은 결국 Result를 통해 발생한 예외를 제어할 수 없는 문제에 직면하게 되었다.

## 문제 상황 재연

```kotlin
@SpringBootTest
class GetReferenceByIdTest @Autowired constructor(
    private val repository: MemberRepository
) {

    @Test
    @Transactional
    fun `getReferenceById를 활용한 뒤 proxy 객체를 조회하고 id에 접근한다`() {
        val result = runCatching {
            repository.getReferenceById(1L).also { it.id }
        }

        println(result)
    }
}
```

`also`를 통해 `it.id`로 접근하여 초기화를 진행할 경우 예외가 catch 되어 Result 실패 객체를 만들지 않고 바로 예외를 던져버린다. 

## 해결 방법

```kotlin
@SpringBootTest
class GetReferenceByIdTest @Autowired constructor(
    private val repository: MemberRepository
) {

    @Test
    @Transactional
    fun `Hibernate의 isInitialized를 활용하여 초기화 여부를 확인한다`() {
        val result = runCatching {
            repository.getReferenceById(1L)
                .also { proxyEntity -> Hibernate.unproxy(proxyEntity) }
        }

        println(result)
    }
}
```

`Hibernate`의 `unproxy`를 사용하여 초기화할 수 있다.

```java
public final class Hibernate {
    // ...
    public static Object unproxy(Object proxy) {
        if ( proxy instanceof HibernateProxy ) {
            HibernateProxy hibernateProxy = (HibernateProxy) proxy;
            LazyInitializer initializer = hibernateProxy.getHibernateLazyInitializer();
            return initializer.getImplementation();
        }
        else {
            return proxy;
        }
    }
    // ...
}
```

 * `public static Object unproxy(Object proxy)`: 프록시 모드를 해제한다. 프록시가 초기화 되지 않은 경우 자동으로 초기화를 트리거한다. 제공된 객체가 null이거나 프록시가 아닌 경우 객체 그대로 반환된다.

## 우려 사항

프록시를 강제로 초기화 하는 방법은 찾았지만 지나치게 Hibernate에 의존적이다. 당장 JPA의 구현체를 변경할 일은 없지만 의존성을 숨기기 위해서는 좀 더 추상화된 개념이 필요할 것 같다.

## 의문점

왜 id 조회를 진행할 때만 프록시 객체에서 던지는 예외를 정상적으로 catch하지 못하는 걸까? id 조회와 다른 칼럼 조회의 가장 큰 차이점은 실제 데이터베이스를 조회하는지에 대한 여부이다. 
또한 `Trasient`로 선언된 프로퍼티를 호출할 때도 프록시 객체가 정상적으로 unproxy되어 예외를 catch한다. 이것은 id의 특수한 구조 때문인가? 추후 알아보도록 해야 겠다. 
