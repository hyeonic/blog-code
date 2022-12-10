package io.github.hyeonic.springtransaction.account.v3;

import io.github.hyeonic.springtransaction.account.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service("accountServiceV3")
public class AccountService {

    private final TransactionTemplate transactionTemplate;
    private final JdbcAccountRepository jdbcAccountRepositoryV3;

    public AccountService(final PlatformTransactionManager platformTransactionManager,
                          final JdbcAccountRepository jdbcAccountRepositoryV3) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.jdbcAccountRepositoryV3 = jdbcAccountRepositoryV3;
    }

    public void withdraw(final Account account, final Long amount) {
        transactionTemplate.executeWithoutResult(
                transactionStatus -> jdbcAccountRepositoryV3.update(generateAccount(account, amount))
        );
    }

    private Account generateAccount(final Account account, final Long amount) {
        return new Account(account.getId(), account.getHolder(), account.getAmount() - amount);
    }
}
