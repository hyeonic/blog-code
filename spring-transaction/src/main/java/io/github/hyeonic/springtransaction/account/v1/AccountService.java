package io.github.hyeonic.springtransaction.account.v1;

import io.github.hyeonic.springtransaction.account.Account;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

@Service("accountServiceV1")
public class AccountService {

    private final DataSource dataSource;
    private final JdbcAccountRepository accountRepository;

    public AccountService(final DataSource dataSource, final JdbcAccountRepository accountRepository) {
        this.dataSource = dataSource;
        this.accountRepository = accountRepository;
    }

    public void withdraw(final Account account, final Long amount) throws SQLException {
        var connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            accountRepository.update(connection,
                    new Account(account.getId(), account.getHolder(), account.getAmount() - amount));
            connection.commit();
        } catch (final SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
