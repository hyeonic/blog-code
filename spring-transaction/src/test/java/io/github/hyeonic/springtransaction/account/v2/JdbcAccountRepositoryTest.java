package io.github.hyeonic.springtransaction.account.v2;

import io.github.hyeonic.springtransaction.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcAccountRepositoryTest {

    private final JdbcAccountRepository accountRepository;

    @Autowired
    JdbcAccountRepositoryTest(final JdbcAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Test
    void save() {
        Account account = new Account("hyeonic", 10000L);

        Account actual = accountRepository.save(account);

        System.out.println(actual);
    }
}
