package io.github.hyeonic.springtransaction.account.v4;

import io.github.hyeonic.springtransaction.account.Account;

public interface AccountService {

    void withdraw(final Account account, final Long amount);
}
