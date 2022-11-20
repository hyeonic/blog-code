# jpa-save

`Spring Data Jpa`에서 제공하는 `SimpleJpaRepository` 구현체를 통해 `EntityManager`의 `save()`와 `merge()`의 차이를 살펴본다.

## 개발 환경

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.5'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'io.github.hyeonic'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

## SimpleJpaRepository

`SimpleJpaRepository`는 `org.springframework.data.repository.CrudRepository` 인터페이스의 기본 구현이다. 
이것은 일반 `EntityManager` 보다 더 정교한 인터페이스를 제공합니다. 

먼저 엔티티를 영속 시키기 위한 `save()` 메서드를 중점적으로 살펴보려한다. 예시를 살펴보기 위해 먼저 Member 엔티티를 선언한다.

```java
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Member() {
    }

    public Member(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

간단한 테스트를 통해 동작 방식을 확인해보자.

```java
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    void 식별자가_없는_member를_저장한다() {
        Member member = new Member(null, "매트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }
}
```

`new Member(null, "매트")`를 저장하는 테스트이다. `save()`를 따라가보자.

```java
@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> { 
    // ...
    @Transactional 
    @Override 
    public <S extends T> S save(S entity) {
        
        Assert.notNull(entity, "Entity must not be null.");
        
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }
    // ...
}
```

로직을 살펴보면 `entityInformation.isNew(entity)`를 통해 해당 `entity`가 `new`인지 확인한다.
 * `entityInformation.isNew(entity)`가 `true`이면 `em.persist(entity)`한다.
 * `entityInformation.isNew(entity)`가 `false`이면 `em.merge(entity)`한다.

그렇다면 `isNew()`는 어떻게 새로운 `entity`인지 확인할까? 구현 코드를 살펴보자.

```java
public class JpaMetamodelEntityInformation<T, ID> extends JpaEntityInformationSupport<T, ID> {
    // ...
    @Override
    public boolean isNew(T entity) {

        if (!versionAttribute.isPresent()
                || versionAttribute.map(Attribute::getJavaType).map(Class::isPrimitive).orElse(false)) {
            return super.isNew(entity);
        }

        BeanWrapper wrapper = new DirectFieldAccessFallbackBeanWrapper(entity);

        return versionAttribute.map(it -> wrapper.getPropertyValue(it.getName()) == null).orElse(true);
    }
    // ...
}
```

`JpaMetamodelEntityInformation`는 JPA Metamodel을 사용하여 도메인 클래스의 id 필드를 찾는 `org.springframework.data.repository.core.EntityInformation` 구현체이다.
해당 클래스의 `isNew()`를 살펴보면 `super.isNew(entity)`을 호출하고 있다.

```java
public abstract class AbstractEntityInformation<T, ID> implements EntityInformation<T, ID> {
    // ...
    public boolean isNew(T entity) {

        ID id = getId(entity);
        Class<ID> idType = getIdType();

        if (!idType.isPrimitive()) {
            return id == null;
        }

        if (id instanceof Number) {
            return ((Number) id).longValue() == 0L;
        }

        throw new IllegalArgumentException(String.format("Unsupported primitive id type %s", idType));
    }
    // ...
}
```

`AbstractEntityInformation`는 `EntityInformation`의 구현을 위한 기본 클래스이다. 
`getId(Object)`가 null을 반환하거나 식별자가 `Java primitive`이고 `getId(Object)`가 0을 반환할 때마다 새로운 엔티티로 간주한다.

먼저 `getId()`를 통해 제네릭으로 명시한 `ID`를 반환한다.

```java
public class JpaMetamodelEntityInformation<T, ID> extends JpaEntityInformationSupport<T, ID> {
    // ...
    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public ID getId(T entity) {

        // check if this is a proxy. If so use Proxy mechanics to access the id.
        PersistenceProvider persistenceProvider = PersistenceProvider.fromMetamodel(metamodel);

        if (persistenceProvider.shouldUseAccessorFor(entity)) {
            return (ID) persistenceProvider.getIdentifierFrom(entity);
        }

        // if not a proxy use Spring mechanics to access the id.
        BeanWrapper entityWrapper = new DirectFieldAccessFallbackBeanWrapper(entity);

        if (idMetadata.hasSimpleId()) {
            // 실제 반환하는 부분
            return (ID) entityWrapper.getPropertyValue(idMetadata.getSimpleIdAttribute().getName());
        }

        BeanWrapper idWrapper = new IdentifierDerivingDirectFieldAccessFallbackBeanWrapper(idMetadata.getType(), metamodel);
        boolean partialIdValueFound = false;

        for (SingularAttribute<? super T, ?> attribute : idMetadata) {
            Object propertyValue = entityWrapper.getPropertyValue(attribute.getName());

            if (propertyValue != null) {
                partialIdValueFound = true;
            }

            idWrapper.setPropertyValue(attribute.getName(), propertyValue);
        }

        return partialIdValueFound ? (ID) idWrapper.getWrappedInstance() : null;
    }
    // ...
}
```

실제 디버깅을 진행해보면 `idMetadata.hasSimpleId()`를 통해 simpleId 유무를 확인하고 `(ID)`를 통해 명시한 타입으로 변환하고 있다.
`Java primitive type`인 경우 제네릭으로 표현할 수 없기 때문에 그에 해당하는 `Wrapper Class`으로 변환하는 것으로 추측한다.

정리하면 `save()` 시점에 `isNew()` 내부에 `getId(Object)`가 null을 반환하거나 식별자가 `Java primitive`이고 `getId(Object)`가 0을 반환할 때마다 새로운 엔티티로 간주한다.
새로운 엔티티로 간주된 것은 `em.persist(entity)`를 통해 처리된다.

이 밖에도 새로운 엔티티를 식별하는 방법은 여러가지가 있다. `@Version`를 활용한 방법, 엔티티 클래스에 `Persistable<ID>` 인터페이스를 구현하는 것 등이 있다.
자세한 구현 방법은 따로 다루지 않겠다. 

### [번외] 엔티티 id, primitive type vs Wrapper Class

`Wrapper Class`는 객체이므로 `null` 대입이 가능하다. 반면 primitive type은 null을 대입할 수 없다. 
실제 hibernate 공식 문서를 살펴보면 `Wrapper Class`를 추천하고 있다.

> We recommend that you declare consistently-named identifier attributes on persistent classes and that you use a nullable (i.e., non-primitive) type.
> 
> 영속 클래스에는 일관되게 명명된 식별자 속성을 선언하고 null 가능한(즉, 원시가 아닌) 유형을 사용하는 것이 좋다.

## persist() vs merge()

지금 까지 `isNew()`를 통해 내부적으로 어떻게 새로운 엔티티로 간주하는지 알아보았다. 새로운 엔티티로 간주된 것과 아닌 것의 `save()` 로직에 대해 살펴보자.

`persist()`는 Member 데이터를 기반으로 `insert`를 호출한다.

```java
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRepositoryTest {
    // ...
    @Test
    @Rollback(value = false)
    void 식별자가_없는_member를_저장한다() {
        Member member = new Member(null, "매트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }
    // ...
}
```

 * `Rollback(value = false)`: 롤백하지 않고 insert하여 실행되는 쿼리를 확인한다.

```
Hibernate: insert into member (name, id) values (?, ?)
```

다음은 임의의 식별자를 명시한 뒤 `save()`한다.

```java
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRepositoryTest {
    // ...
    @Test
    @Rollback(value = false)
    void 식별자를_1로_지정한_member를_저장한다() {
        Member member = new Member(1L, "매트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }
    // ...
}
```

위와 같이 준영속 상태에서 저장할 경우 `merge()`가 발생한다. `merge()`는 `select`를 통해 엔티티가 존재하는지 확인한 뒤 없으면 `insert`를 진행한다.

```
Hibernate: select member0_.id as id1_0_0_, member0_.name as name2_0_0_ from member member0_ where member0_.id=?
Hibernate: insert into member (name, id) values (?, ?)
```

## 정리

지금까지 `SimpleJpaRepository`의 `save()`를 통해 `persist()`와 `merge()`의 차이에 대해 알아보았다. 
`save()`는 `isNew()` 통해 새로운 엔티티인지를 판별한다. 예를들어 `Long`을 활용할 경우 `null`이거나 `0L`일 때 `true`를 반환하게 된다.

그 밖에도 다양한 방식을 통해 새로운 엔티티로 인식될 수 있다. 
핵심은 `merge()`로 인식될 되기 이전에 엔티티는 영속성 컨텍스트에서 관리되지 않는 준영속 상태이기 때문에 `Dirty Checking`과 같은 이점을 활용할 수 없다.
이것을 잘 고려하여 엔티티의 id를 설정해야 한다.
