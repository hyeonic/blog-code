package io.github.hyeonic.transaction.member;

public class Member {

    private Long id;
    private Integer money;

    public Member(final Long id, final Member member) {
        this(id, member.getMoney());
    }

    public Member(final Integer money) {
        this(null, money);
    }

    public Member(final Long id, final Integer money) {
        this.id = id;
        this.money = money;
    }

    public void plusMoney(final Integer price) {
        this.money += price;
    }

    public void minusMoney(final Integer price) {
        this.money -= price;
    }

    public Long getId() {
        return id;
    }

    public Integer getMoney() {
        return money;
    }
}
