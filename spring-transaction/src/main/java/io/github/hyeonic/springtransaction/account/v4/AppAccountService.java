package io.github.hyeonic.springtransaction.account.v4;

import io.github.hyeonic.springtransaction.account.Account;
import org.springframework.stereotype.Service;

@Service("accountServiceV4")
public class AppAccountService implements AccountService {

    private final JdbcAccountRepository jdbcAccountRepositoryV4;

    public AppAccountService(final JdbcAccountRepository jdbcAccountRepositoryV4) {
        this.jdbcAccountRepositoryV4 = jdbcAccountRepositoryV4;
    }

    public void withdraw(final Account account, final Long amount) {
        jdbcAccountRepositoryV4.update(generateAccount(account, amount));
    }

    private Account generateAccount(final Account account, final Long amount) {
        return new Account(account.getId(), account.getHolder(), account.getAmount() - amount);
    }
}
