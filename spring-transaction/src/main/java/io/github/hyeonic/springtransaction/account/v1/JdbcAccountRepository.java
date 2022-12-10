package io.github.hyeonic.springtransaction.account.v1;

import io.github.hyeonic.springtransaction.account.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository("jdbcAccountRepositoryV1")
public class JdbcAccountRepository {

    public Account save(final Connection connection, final Account account) throws SQLException {
        var sql = "INSERT INTO account(holder, amount) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, account.getHolder());
            preparedStatement.setLong(2, account.getAmount());
            preparedStatement.executeUpdate();

            return findByHolder(connection, account.getHolder())
                    .orElseThrow(NoSuchElementException::new);
        }
    }

    public Optional<Account> findByHolder(final Connection connection, final String holder) throws SQLException {
        var sql = "SELECT * FROM account WHERE holder = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, holder);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Account(
                        resultSet.getLong("id"),
                        resultSet.getString("holder"),
                        resultSet.getLong("amount")
                ));
            }

        }

        return Optional.empty();
    }

    public void update(final Connection connection, final Account account) throws SQLException {
        var sql = "UPDATE account SET holder = ?, amount = ? WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, account.getId());
            preparedStatement.setString(2, account.getHolder());
            preparedStatement.setLong(3, account.getAmount());
            preparedStatement.executeUpdate();
        }
    }
}
