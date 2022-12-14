package io.github.hyeonic.concurrencynamedlock.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserLockTemplate {

    private static final String GET_LOCK = "SELECT GET_LOCK(?, ?)";
    private static final String RELEASE_LOCK = "SELECT RELEASE_LOCK(?)";

    private final DataSource dataSource;

    public UserLockTemplate(@Qualifier("lockDataSource") final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeWithLockWithoutResult(final String userLockName, double timeout, final Executor callback) {
        executeWithLock(userLockName, timeout, () -> {
            callback.execute();
            return null;
        });
    }

    public <T> T executeWithLock(final String userLockName, double timeout, final Supplier<T> supplier) {
        try (var connection = dataSource.getConnection()) {
            return execute(connection, userLockName, timeout, supplier);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T execute(final Connection connection, final String userLockName, final double timeout,
                          final Supplier<T> supplier) {
        try {
            getLock(connection, userLockName, timeout);
            return supplier.get();
        } finally {
            releaseLock(connection, userLockName);
        }
    }

    private void getLock(final Connection connection, final String userLockName, double timeout) {
        try (var preparedStatement = connection.prepareStatement(GET_LOCK)) {
            preparedStatement.setString(1, userLockName);
            preparedStatement.setDouble(2, timeout);
            preparedStatement.executeQuery();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void releaseLock(final Connection connection, final String userLockName) {
        try (var preparedStatement = connection.prepareStatement(RELEASE_LOCK)) {
            preparedStatement.setString(1, userLockName);
            preparedStatement.executeQuery();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
