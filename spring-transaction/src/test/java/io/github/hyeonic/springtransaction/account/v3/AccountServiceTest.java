package io.github.hyeonic.springtransaction.account.v3;

import io.github.hyeonic.springtransaction.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    private final JdbcAccountRepository jdbcAccountRepositoryV3;
    private final AccountService accountServiceV3;

    @Autowired
    AccountServiceTest(final JdbcAccountRepository jdbcAccountRepositoryV3, final AccountService accountServiceV3) {
        this.jdbcAccountRepositoryV3 = jdbcAccountRepositoryV3;
        this.accountServiceV3 = accountServiceV3;
    }

    @Test
    void withdraw() {
        Account account = jdbcAccountRepositoryV3.save(new Account("hyeonic", 10000L));

        accountServiceV3.withdraw(account, 5000L);
        Account actual = jdbcAccountRepositoryV3.findByHolder("hyeonic").get();

        System.out.println(actual);
    }
}
