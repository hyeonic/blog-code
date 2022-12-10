package io.github.hyeonic.springtransaction.account.v5;

import io.github.hyeonic.springtransaction.account.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountServiceV5")
public class AccountService {

    private final JdbcAccountRepository jdbcAccountRepositoryV5;

    public AccountService(final JdbcAccountRepository jdbcAccountRepositoryV5) {
        this.jdbcAccountRepositoryV5 = jdbcAccountRepositoryV5;
    }

    @Transactional
    public void withdraw(final Account account, final Long amount) {
        jdbcAccountRepositoryV5.update(new Account(account.getId(), account.getHolder(), account.getAmount() - amount));
    }
}
