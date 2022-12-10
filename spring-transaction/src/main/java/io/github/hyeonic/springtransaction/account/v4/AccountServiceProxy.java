package io.github.hyeonic.springtransaction.account.v4;

import io.github.hyeonic.springtransaction.account.Account;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceProxy implements AccountService {

    private final TransactionTemplate transactionTemplate;
    private final AccountService accountService;

    public AccountServiceProxy(final PlatformTransactionManager platformTransactionManager,
                               final AccountService accountService) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.accountService = accountService;
    }

    @Override
    public void withdraw(final Account account, final Long amount) {
        transactionTemplate.executeWithoutResult(transactionStatus -> accountService.withdraw(account, amount));
    }
}
