package c.hyeoni.functionalprogrammingwithkotlin.chapter02

interface Service {

    fun String.addHello()

    fun run() {
        "hyeonic".addHello() // 사용 가능
    }
}

fun run() {
//    "hyeonic".addHello() 컴파일 에러
}
