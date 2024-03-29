# spring-data-envers

Spring Data Envers는 Spring Data JPA용 repository에서 일반적인 Envers 쿼리를 사용할 수 있도록 도와준다. 항상 Spring Data JPA와 함께 사용된다는 점에서 다른
Spring Data 모듈과 다르다.

## Envers란?

Envers는 JPA 엔티티에 Auditing 기능을 추가하는 [Hibernate 모듈](https://hibernate.org/orm/envers/)이다. 이러한 Envers를 사용하면 엔티티에서 수행되는 변경
사항을 감사하고 기록할 수 있다. 단순히 변경을 감사하는 것 뿐만 아니라 변경되는 행위에 따라 그에 대한 기록을 별도의 테이블을 통해 자동으로 기록한다.

정리하면 특정 엔티티의 변경 이력이 중요한 비즈니스의 경우 기록하기 위한 용도로 아주 적합한 도구가 될 수 있다.

## 적용하기

> 간단한 적용 예시를 위해 JPA와 Entity에 대한 설명은 최소화한다.

자 이제 Envers를 적용해보자. 먼저 간단한 예시를 위한 Member와 Post 엔티티이다. Member는 여러 개의 게시글(Post)를 작성할 수 있다고 가정한다. 두 테이블 모두 변경 이력에 대한 기록이
표현하다고 가정한 뒤 이것을 구현하기 위해 `@Audited`을 활용한다.

```kotlin
@Audited
@Entity
class Member(
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column
    var name: String = name
        protected set

    override fun toString(): String {
        return "Member(id=$id, name='$name')"
    }
}
```
 * `id`: 고유 식별자 id이다.
 * `name`: member의 이름을 나타낸다.

```kotlin
@Audited
@Entity
class Post(
    content: String,
    member: Member
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column
    var content: String = content
        protected set

    @Column
    var views: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member
        protected set

    override fun toString(): String {
        return "Post(id=$id, content='$content', views=$views, member=$member)"
    }
}
```
 * `id`: 고유 식별자이다.
 * `content`: 게시글의 내용이다.
 * `views`: 게시글의 조회수를 나타낸다.
 * `member`: 누가 작성한 게시글인지에 대한 정보이다.

이제 생성된 쿼리를 확인해본다.

```text
Hibernate:

create table member (
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
)
Hibernate:

create table member_aud (
    id bigint not null,
    rev integer not null,
    revtype tinyint,
    name varchar(255),
    primary key (id, rev)
)
Hibernate:

create table post (
    id bigint generated by default as identity,
    content varchar(255),
    views bigint,
    member_id bigint,
    primary key (id)
)
Hibernate:

create table post_aud (
    id bigint not null,
    rev integer not null,
    revtype tinyint,
    content varchar(255),
    views bigint,
    member_id bigint,
    primary key (id, rev)
)
```

> ddl-auto 설정을 create로 두었을 때 생긴 테이블 정보이다.

`_aud`의 형식을 가진 테이블이 추가로 생긴 것을 확인할 수 있다. 해당 테이블에 revtype을 통해 변경 이력에 대한 타입을 관리한다.

revtype 칼럼에서 각각의 숫자가 의미하는 바는 아래와 같다.
 * `0(ADD)`: 행 추가
 * `1(MOD)`: 행 수정
 * `2(DEL)`: 행 삭제

## References.

* [Spring Data Envers - Reference Documentation](https://docs.spring.io/spring-data/envers/docs/current/reference/html/)
* [dlxotn216/spring-data-envers](https://github.com/dlxotn216/spring-data-envers)
* [Spring Boot + Envers로 엔티티 이력관리하기](https://haviyj.tistory.com/40)
* [Envers / spring-data-envers](https://code-resting.tistory.com/64)
* [Hibernate ORM 이력관리 모듈 Envers 사용 가이드](https://directori.tistory.com/138)
* [spring data envers 로 데이터 변경 로깅하기](https://sehajyang.github.io/2020/04/15/springboot-envers-logging-for-revision/)
* [spring-data-envers로 데이터 변경 이력(히스토리)을 관리해보자!](https://applepick.tistory.com/171)
* [3.1. What is Spring Data Envers?](https://docs.spring.io/spring-data/envers/docs/current/reference/html/)
* [21. Envers](https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#envers)

* [Spring Data Envers - Reference Documentation](https://docs.spring.io/spring-data/envers/docs/current/reference/html/)
* [envers](https://hibernate.org/orm/envers/)
* [21. Envers](https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#envers)
