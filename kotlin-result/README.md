# kotlin-result

kotlin의 `Result<T>`는 성공적인 결과를 `T` 타입의 값으로 캡슐화하거나 임의의 `Throwable` 예외가 있는 실패를 캡슐화하는 구별된 공용체이다.

```kotlin
@SinceKotlin("1.3")
@JvmInline
public value class Result<out T> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any?
) : Serializable {
    // ...
}
```

구성은 매우 간단한다. `Any?` 타입으로 어떤 값도 담을 수 있는 `internal val value`가 존재한다.

이러한 Result는 예외를 값으로 제어할 수 있다. 예외가 감지되면 그것을 바로 던지는 것이 아니라 Result라는 값으로 포장한다. 
즉 예외를 값으로 활용하며 개발자가 온전히 제어할 수 있게 된다.

먼저 `Result` 객체를 반환하는 `runCatching()`부터 살펴보자.

```kotlin
@InlineOnly
@SinceKotlin("1.3")
public inline fun <T, R> T.runCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
```

구성은 굉장히 간단하다. try ~ catch를 활용하여 예외가 발생하지 않으면 `Result.success(block())`를 반환한다. 만약 예외가 캐치된다면 `Result.failure(e)`를 반환하게 된다.
여기서 `Result.success`와 `Result.failure`는 Result 내부에 `companion object`에 선언되어 있다.

```kotlin
public value class Result<out T> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any?
) : Serializable {
    // ...
    public companion object {
        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")
        @InlineOnly
        @JvmName("success")
        public inline fun <T> success(value: T): Result<T> =
            Result(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */
        @Suppress("INAPPLICABLE_JVM_NAME")
        @InlineOnly
        @JvmName("failure")
        public inline fun <T> failure(exception: Throwable): Result<T> =
            Result(createFailure(exception))
    }

    internal class Failure(
        @JvmField
        val exception: Throwable
    ) : Serializable {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }
    // ...
}
```

* `success(value: T)`: 주어진 value 성공적인 값으로 캡슐화하는 인스턴스를 반환한다.
* `failure(exception: Throwable)`: 주어진 `Throwable` 을 실패로 캡슐화하는 인스턴스를 반환한다.

`Result`는 기본적으로 `internal constructor`이기 때문에 다른 모듈에서 생성자를 통한 생성을 제한한다. 즉 `companion object`에 존재하는 `success`와 `failure`를 활용하여 생성할 수 있다.

이렇게 만들어진 Result 객체는 성공일 수도 있고 실패일 수도 있다. 그것은 아래 프로퍼티를 통해 성공 실패 여부를 확인할 수 있다.

* `isSuccess`: 이 인스턴스가 성공적인 결과를 나타내는 경우 `true` 반환한다. 이 경우 `isFailure` `false` 반환한다.
* `isFailure`: 이 인스턴스가 실패한 결과를 나타내면 `true` 반환한다. 이 경우 `isSuccess` `false` 반환한다.

