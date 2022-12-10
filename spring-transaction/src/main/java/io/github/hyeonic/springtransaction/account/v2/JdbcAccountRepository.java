package io.github.hyeonic.springtransaction.account.v2;

import io.github.hyeonic.springtransaction.account.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository("jdbcAccountRepositoryV2")
public class JdbcAccountRepository {

    private final DataSource dataSource;

    public JdbcAccountRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Account save(final Account account) {
        var connection = DataSourceUtils.getConnection(dataSource);
        var sql = "INSERT INTO account(holder, amount) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getHolder());
            preparedStatement.setLong(2, account.getAmount());
            preparedStatement.executeUpdate();

            return findByHolder(account.getHolder())
                    .orElseThrow(NoSuchElementException::new);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public Optional<Account> findByHolder(final String holder) {
        var connection = DataSourceUtils.getConnection(dataSource);
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

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return Optional.empty();
    }

    public void update(final Account account) {
        var connection = DataSourceUtils.getConnection(dataSource);
        var sql = "UPDATE account SET holder = ?, amount = ? WHERE id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getHolder());
            preparedStatement.setLong(2, account.getAmount());
            preparedStatement.setLong(3, account.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
