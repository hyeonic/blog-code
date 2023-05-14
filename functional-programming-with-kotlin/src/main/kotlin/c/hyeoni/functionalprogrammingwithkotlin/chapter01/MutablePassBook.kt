package c.hyeoni.functionalprogrammingwithkotlin.chapter01

class MutablePassBook(
    var accountNumber: String,
    var balance: Long
) {

    fun transfer(money: Long) {
        this.balance -= money
    }
}
