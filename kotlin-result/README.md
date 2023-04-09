# kotlin-result

kotlin의 `Result<T>`는 성공적인 결과를 `T` 타입의 값으로 캡슐화하거나 임의의 `Throwable` 예외가 있는 실패를 캡슐화하는 구별된 공용체이다.

```kotlin
@SinceKotlin("1.3")
@JvmInline
public value class Result<out T> @PublishedApi internal constructor(
    @PublishedApi
    internal val value: Any?
) : Serializable {
    // discovery

    /**
     * Returns `true` if this instance represents a successful outcome.
     * In this case [isFailure] returns `false`.
     */
    public val isSuccess: Boolean get() = value !is Failure

    /**
     * Returns `true` if this instance represents a failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    public val isFailure: Boolean get() = value is Failure

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
}
```

 * `isSuccess`: 이 인스턴스가 성공적인 결과를 나타내는 경우 `true` 반환한다. 이 경우 `isFailure` `false` 반환한다.
 * `isFailure`: 이 인스턴스가 실패한 결과를 나타내면 `true` 반환한다. 이 경우 `isSuccess` `false` 반환한다.
 * `success(value: T)`: 주어진 value 성공적인 값으로 캡슐화하는 인스턴스를 반환한다.
 * `failure(exception: Throwable)`: 주어진 `Throwable` 을 실패로 캡슐화하는 인스턴스를 반환한다.
