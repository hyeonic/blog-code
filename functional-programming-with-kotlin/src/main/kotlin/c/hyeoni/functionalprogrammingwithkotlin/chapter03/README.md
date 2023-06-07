# 03. 재귀

함수형 프로그래밍은 명령문을 반복할 때 `루프` 대신 `재귀`를 사용한다.

> 재귀란? 어떤 함수의 구현 내부에서 `자기 자신을 호출하는 함수`를 정의하는 방법을 의미한다.

```kotlin
fun fibonacci(n: Int): Int {
    return when (n) {
        0 -> 0
        1 -> 1
        else -> fibonacci(n - 1) + fibonacci(n - 2)
    }
}
```

재귀로 구현한 함수는 아래와 같은 특징을 가질 수 있다.

* 고정된 메모리를 할당하지 않는다.
* 값을 변경하지 않는다.

컴파일러는 내부적으로 현재 호출하는 함수에 대한 정보를 `스택`에 기록한다. 재귀 함수도 함수이기 때문에 마찬가지로 스택에 기록된다.
프로그래머가 직접 메모리를 제어하지 않는 다는 장점도 존재하지만 호출에 제한을 두지 않으면 쉽게 `Stack Overflow`에 빠질 수 있다.

> 함수형에서는 `어떻게(How)` 값을 계산할 수 있는지 선언하는 대신 `무엇(What)`을 선언할지 고민해야 한다. `루프`는 `어떻게 동작해야 하는지 명령`하는 구문이다.

## 재귀 함수 설계하기

* 재귀에 `종료조건(edge-condition)`을 작성한다. 작성하지 않으면 무한루프에 빠질 수 있다.
* 함수의 입력을 분할하여 재귀 호출해야 한다.
* 재귀는 반복할수록 `종료조건에 수렴`해야 한다.

> 종료 조건 정의 시 더는 쪼개지지 않는 값을 통해 재귀의 진행을 막는다. 이러한 값을 `항등값`이라 한다. `항등값`은 어떤 값에 연산을 진행해도 자기 자신이 되는 값을 말한다.
> * 곱셈에서의 항등값은 1이다. 
> * 덧셈에서 항등값은 0이다.

## 재귀 함수와 수학적 귀납법

모든 재귀 함수는 논리적으로 `수학적 귀납법`으로 증명이 가능하다.

> 수학적 귀납법이란? 모든 자연수가 어떤 주어진 성질을 만족시킨다는 명제를 증명하는 방법이다.

### 재귀로 power() 함수 구현하기

```kotlin
fun power(x: Double, n: Int): Double {
    return when(n) {
        0 -> 1.0 // x의 0승은 1
        else -> x * power(x, n - 1)
    }
}
```

### 재귀로 factorial() 함수 구현하기

```kotlin
fun factorial(n: Long): Long {
    return when(n) {
        0L -> 1L // 0! = 1
        else -> n * factorial(n - 1)
    }
}
```

### 재귀로 리스트에 최대값 추출하는 maximum() 함수 구현하기

```kotlin
fun List<Int>.head(): Int = this.first()

fun List<Int>.tail(): List<Int> = this.drop(1)

fun maximum(numbers: List<Int>): Int {
    return when {
        numbers.isEmpty() -> throw NoSuchElementException("List is empty.")
        numbers.size == 1 -> numbers.head()
        else -> {
            val head = numbers.head()
            val tail = numbers.tail()
            val maxNumber = maximum(tail)
            if (head > maxNumber) head else maxNumber
        }
    }
}
```

### 재귀로 reverse() 함수 구현하기

```kotlin
fun String.head(): Char = this.first()

fun String.tail(): String = this.drop(1)

fun reverse(str: String): String {
    return when {
        str.isEmpty() -> ""
        else -> reverse(str.tail()) + str.head()
    }
}
```

### 재귀로 toBinary() 함수 구현하기

```kotlin
fun toBinary(n: Int): String {
    return when {
        n < 2 -> "1" // 2로 나눌 수 없는 경우 1
        else -> toBinary(n / 2) + (n % 2) // (n / 2)로 n을 줄여간다.
    }
}
```

### 재귀로 replicate() 함수 구현하기

```kotlin
fun replicate(n: Int, element: Int): List<Int> {
    return when (n) {
        0 -> listOf()
        else -> replicate(n - 1, element) + listOf(element)
    }
}
```

`Collection`의 `+` 연산자 활용

```kotlin
public operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T> {
    if (elements is Collection) {
        val result = ArrayList<T>(this.size + elements.size)
        result.addAll(this)
        result.addAll(elements)
        return result
    } else {
        val result = ArrayList<T>(this)
        result.addAll(elements)
        return result
    }
}
```

## References.

 * [수학적 귀납법](https://ko.wikipedia.org/wiki/%EC%88%98%ED%95%99%EC%A0%81_%EA%B7%80%EB%82%A9%EB%B2%95)
