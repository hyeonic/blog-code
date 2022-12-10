package io.github.hyeonic.springtransaction.account.v2;

import io.github.hyeonic.springtransaction.account.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("accountServiceV2")
public class AccountService {

    private final PlatformTransactionManager platformTransactionManager;
    private final JdbcAccountRepository accountRepository;

    public AccountService(final PlatformTransactionManager platformTransactionManager,
                          final JdbcAccountRepository accountRepository) {
        this.platformTransactionManager = platformTransactionManager;
        this.accountRepository = accountRepository;
    }

    public void withdraw(final Account account, final Long amount) {
        var transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            accountRepository.update(new Account(account.getId(), account.getHolder(), account.getAmount() - amount));
            platformTransactionManager.commit(transactionStatus);
        } catch (final Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            throw new RuntimeException(e);
        }
    }
}
