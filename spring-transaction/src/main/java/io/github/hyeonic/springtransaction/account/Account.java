package io.github.hyeonic.springtransaction.account;

public class Account {

    private final Long id;
    private final String holder;
    private Long amount;

    public Account(final String holder, final Long amount) {
        this(null, holder, amount);
    }

    public Account(final Long id, final String holder, final Long amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public void withdraw(final Long amount) {
        this.amount -= amount;
    }

    public Long getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder='" + holder + '\'' +
                ", amount=" + amount +
                '}';
    }
}
