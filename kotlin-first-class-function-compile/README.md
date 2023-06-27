# kotlin-first-class-function-compile

1급 객체(First-class citizen)란 아래 조건을 충족해야 한다.
 * 모든 요소는 함수의 실제 매개변수가 될 수 있다.
 * 모든 요소는 함수의 반환 값이 될 수 있다.
 * 모든 요소는 할당 명령문의 대상이 될 수 있다.
 * 모든 요소는 동일 비교의 대상이 될 수 있다.

`kotlin`에서는 함수도 1급 객체로 다룰 수 있도록 지원하고 있다. 하지만 `java`에서는 이러한 함수를 제공하고 있지 않다. 
정화히 이야기하면 언어에서 함수라는 개념 자체를 지원하고 있지 않기 때문에 `함수형 인터페이스`와 같은 것들을 활용하여 유사하게 구현하고 있다. 

그렇다면 `kotlin`에서 직접 함수를 매개변수, 반환 값 등으로 활용한 뒤 `java`로 디컴파일하여 어떻게 해석이 되는지 확인해본다.

## Show Kotlin Bytecode

```kotlin
fun Int.convertString(transform: (Int) -> String): String {
    return transform(this)
}
```

위 코드는 `Int`를 `String`으로 변환 시키기 위한 확장 함수이다. 이것을 바이트코드로 변환한 뒤 `.java`로 디컴파일하면 아래와 같은 코드를 확인할 수 있다.

```java
public final class FunctionsKt {
   @NotNull
   public static final String convertString(int $this$convertString, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter(transform, "transform");
      return (String)transform.invoke($this$convertString);
   }
}
```

주로 살펴볼 부분은 바로 `Function1`이다.

```java
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package kotlin.jvm.functions

public interface Function1<in P1, out R> : kotlin.Function<R> {
    public abstract operator fun invoke(p1: P1): R
}
```

위 코드는 실제 코드를 탐색하여 `Function1`을 가져온 것이다. 여기서 매개변수로 작성한 함수가 인터페이스로 변환되어 해석된다는 것을 확인할 수 있었다.

java 바이트코드는 함수를 일급 객체로 표현할 수 있는 수단이 없다. 기본 타입이나 참조 타입만 가능하기 때문이다. 
그렇기 때문에 kotlin 컴파일러는 JVM이 함수를 일급 객체로 처리할 수 있도록 변환해야 한다.

그렇기 때문에 kotlin 런타임 라이브러리는 Function0 ~ 22까지 일련의 일반 인터페이스를 정의하여 함수를 나타낸다. 
모든 인터페이스는 `invoke()`라는 단일 메서드를 가지고 있으며 이 메서드는 함수로 구현된다. 이 또한 객체이기 때문에 할당되고 전달할 수 있게 된다.

## References.

 * [일급 객체](https://ko.wikipedia.org/wiki/%EC%9D%BC%EA%B8%89_%EA%B0%9D%EC%B2%B4)
 * [Function Type is an Interface](https://discuss.kotlinlang.org/t/function-type-is-an-interface/24902/2)
