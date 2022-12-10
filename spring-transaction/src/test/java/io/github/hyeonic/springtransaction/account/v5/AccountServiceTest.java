package io.github.hyeonic.springtransaction.account.v5;

import io.github.hyeonic.springtransaction.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    private final JdbcAccountRepository jdbcAccountRepositoryV4;
    private final AccountService accountServiceV4;

    @Autowired
    AccountServiceTest(final JdbcAccountRepository jdbcAccountRepositoryV4, final AccountService accountServiceV4) {
        this.jdbcAccountRepositoryV4 = jdbcAccountRepositoryV4;
        this.accountServiceV4 = accountServiceV4;
    }

    @Test
    void withdraw() {
        Account account = jdbcAccountRepositoryV4.save(new Account("hyeonic", 10000L));

        accountServiceV4.withdraw(account, 5000L);
        Account actual = jdbcAccountRepositoryV4.findByHolder("hyeonic").get();

        System.out.println(actual);
    }
}
