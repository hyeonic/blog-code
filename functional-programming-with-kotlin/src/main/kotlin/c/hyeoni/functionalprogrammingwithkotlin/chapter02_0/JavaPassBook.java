package c.hyeoni.functionalprogrammingwithkotlin.chapter02_0;

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
