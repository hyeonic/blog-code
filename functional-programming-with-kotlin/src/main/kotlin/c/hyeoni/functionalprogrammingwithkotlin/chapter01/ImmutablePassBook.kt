package c.hyeoni.functionalprogrammingwithkotlin.chapter01

class ImmutablePassBook(
    val accountNumber: String,
    val balance: Long
) {

    fun transfer(money: Int): ImmutablePassBook = ImmutablePassBook(this.accountNumber, this.balance - money)
}
