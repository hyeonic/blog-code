# 02. 코틀린으로 함수형 프로그래밍 시작하기

이 책에서 2장에는 코틀린의 기본적인 문법과 개념에 대해서 설명하고 있다. 모든 내용을 요약하기엔 양이 방대하기 때문에 특히 강조하고 싶은 부분을 위주로 작성하려 한다.

## 필드와 프로퍼티

### 필드

java에서는 보통 클래스 내부에 속성값을 관리하기 위해 필드를 활용한다.

```java
public class JavaPassBook {

    private final Long accountNumber;
    private final Integer balance;

    public JavaPassBook(final Long accountNumber, final Integer balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }
}
```

위 코드에서 `accountNumber`와 `balance`가 바로 필드이다. 이러한 필드는 선언된 위치에 따라 불리는 이름이 다르지만 자세한 내용은 생략한다.

> 참고로 위 필드는 클래스를 기반으로 생성된 `인스턴스`의 구성 요소이므로 `인스턴스 변수`라 부른다.

### 프로퍼티

프로퍼티는 위에서 언급한 필드와 더불어서 접근자로 불리는 함수가 선언된다. 아래는 코틀린의 프로퍼티를 활용하여 선언한 것이다.

```kotlin
class KotlinPassBook(
    val accountNumber: Long,
    val balance: Int
)
```

위 코드를 `decompile`하여 살펴보면 아래와 같은 코드를 확인할 수 있다.

```java
public final class KotlinPassBook {

    private final long accountNumber;
    private final int balance;

    public final long getAccountNumber() {
        return this.accountNumber;
    }

    public final int getBalance() {
        return this.balance;
    }

    public KotlinPassBook(long accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
```

몇 가지 특이한 점을 살펴볼 수 있는데, 단순히 변수만 선언하였다고 생각할 수 있지만 해당 변수에 접근하기 위한 `getter`도 함께 생성된 형태를 가지고 있다.

> 여기서 getter만 자동 생성된 이유는 val을 통해 읽기 전용 프로퍼티를 선언했기 때문이다.

## null

kotlin은 특별한 null 처리 방법을 가지고 있다. 프로퍼티에 아무것도 명시하지 않으면 기분적으로 `nullable`하지 않다.
만약 `nullable`하게 활용하고 싶다면 `?` 키워드를 활용한다.

## 익명 함수

익명 함수는 말그대로 함수의 이름을 선언하지 않고 구현부만 작성하는 것을 말한다. kotlin은 기본적으로 함수를 매개변수와 반환값으로 활용할 수 있기 때문에
이러한 익명 함수는 아주 편리하게 사용될 수 있다.

```kotlin
listOf(1, 2, 3, 4, 5).map { it * 2 }
```

위 코드를 자세히 살펴보자.

```kotlin
public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
    return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
}
```

`.map`은 기본적으로 `transform`이라는 이름을 가진 함수를 매개변수로 활용한다. 이러한 함수의 타입은 `(T) -> R`이다.
이 함수의 구현부는 이름이 없는 `{ it * 2 }`을 통해 전달된다고 볼 수 있다.

이 처럼 함수의 시그니쳐만을 명시하게 되면 다양한 구현부를 전달할 수 있기 때문에 확장성이 굉장히 좋아진다.

> java의 functional interface나 anonymous class를 생각하면 쉽다. 다만 kotlin은 함수 전달이 가능하기에 굳이 추상 객체나 인터페이스를 활용하지 않아도 된다.

## 확장 함수

kotlin은 상속이나 내부를 수정하지 않아도 이미 작성된 클래스에 함수나 프로퍼티를 추가할 수 있다. 이러한 함수를 확장 함수라 한다.

간단한 예시를 살펴보자. 아래는 kotlin에서 사용하고 있는 `String` 객체이다.

```kotlin
public class String : Comparable<String>, CharSequence {
    companion object {}

    public operator fun plus(other: Any?): String

    public override val length: Int

    public override fun get(index: Int): Char

    public override fun subSequence(startIndex: Int, endIndex: Int): CharSequence

    public override fun compareTo(other: String): Int
}
```

알다시피 kotlin에서 `String`은 java보다 풍부한 함수를 제공한다. 그런데 어찌된 영문인지 `String.kt`에는 위의 코드가 전부이다.
그럼 그 많은 기능은 대체 어디에 선언되어 있는 것일까?

아래 처럼 `strings.kt`에 확장 함수를 통해 다양한 기능이 선언되어 있다. `kotlin`에서는 이러한 형태를 종종 확인할 수 있다.

```kotlin
// ...
public inline fun String.trim(predicate: (Char) -> Boolean): String =
    (this as CharSequence).trim(predicate).toString()

public inline fun CharSequence.trimStart(predicate: (Char) -> Boolean): CharSequence {
    for (index in this.indices)
        if (!predicate(this[index]))
            return subSequence(index, length)

    return ""
}

public inline fun String.trimStart(predicate: (Char) -> Boolean): String =
    (this as CharSequence).trimStart(predicate).toString()

public inline fun CharSequence.trimEnd(predicate: (Char) -> Boolean): CharSequence {
    for (index in this.indices.reversed())
        if (!predicate(this[index]))
            return subSequence(0, index + 1)

    return ""
}

public inline fun String.trimEnd(predicate: (Char) -> Boolean): String =
    (this as CharSequence).trimEnd(predicate).toString()

public fun CharSequence.trim(vararg chars: Char): CharSequence = trim { it in chars }

public fun String.trim(vararg chars: Char): String = trim { it in chars }

public fun CharSequence.trimStart(vararg chars: Char): CharSequence = trimStart { it in chars }

public fun String.trimStart(vararg chars: Char): String = trimStart { it in chars }

public fun CharSequence.trimEnd(vararg chars: Char): CharSequence = trimEnd { it in chars }

public fun String.trimEnd(vararg chars: Char): String = trimEnd { it in chars }

public fun CharSequence.trim(): CharSequence = trim(Char::isWhitespace)

@kotlin.internal.InlineOnly
public inline fun String.trim(): String = (this as CharSequence).trim().toString()

public fun CharSequence.trimStart(): CharSequence = trimStart(Char::isWhitespace)

@kotlin.internal.InlineOnly
public inline fun String.trimStart(): String = (this as CharSequence).trimStart().toString()

public fun CharSequence.trimEnd(): CharSequence = trimEnd(Char::isWhitespace)

@kotlin.internal.InlineOnly
public inline fun String.trimEnd(): String = (this as CharSequence).trimEnd().toString()
// ...
```

확장 함수도 추상 함수로 활용할 수 있다. 특정 인터페이스나 추상 클래스에 확장 함수를 추상 함수로 활용할 경우 해당 객체 내부에서만 확장 함수를 사용할 수 있도록 제한할 수 있다.

```kotlin
interface Service {

    fun String.addHello()

    fun run() {
        "hyeonic".addHello() // 사용 가능
    }
}

fun run() {
    "hyeonic".addHello() // 컴파일 에러
}
```

> 간단한 예시로 작성했지만 활용도는 어마무시하다고 볼 수 있다. 자세한 예시는 적절한 상황이 온다면 다뤄볼 예정이다.

## 추상 프로퍼티

kotlin은 프로퍼티로 추상화하여 사용할 수 있다.

```kotlin
interface Repository {

    val tableName: String
}
```

```kotlin
class PassBookRepository : Repository {

    override val tableName: String = "PassBook"
}
```

## data 클래스

`data class`는 기본적으로 getter, setter (var 프로퍼티 생성 시), hashCode, equals, toString 함수를 자동으로 생성해준다.

```kotlin
data class PassBook(
    val accountNumber: Long,
    val balance: Int
)
```

```java
public final class PassBook {
   private final long accountNumber;
   private final int balance;

   public final long getAccountNumber() {
      return this.accountNumber;
   }

   public final int getBalance() {
      return this.balance;
   }

   public PassBook(long accountNumber, int balance) {
      this.accountNumber = accountNumber;
      this.balance = balance;
   }

   public final long component1() {
      return this.accountNumber;
   }

   public final int component2() {
      return this.balance;
   }

   @NotNull
   public final PassBook copy(long accountNumber, int balance) {
      return new PassBook(accountNumber, balance);
   }

   public static PassBook copy$default(PassBook var0, long var1, int var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.accountNumber;
      }

      if ((var4 & 2) != 0) {
         var3 = var0.balance;
      }

      return var0.copy(var1, var3);
   }

   @NotNull
   public String toString() {
      return "PassBook(accountNumber=" + this.accountNumber + ", balance=" + this.balance + ")";
   }

   public int hashCode() {
      return Long.hashCode(this.accountNumber) * 31 + Integer.hashCode(this.balance);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof PassBook) {
            PassBook var2 = (PassBook)var1;
            if (this.accountNumber == var2.accountNumber && this.balance == var2.balance) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
```

`val`를 활용하게 되면 불변 객체로 사용할 수도 있다. data class 덕분에 반복적으로 작성하던 부가적인 코드를 줄일 수 있게 되었다.
