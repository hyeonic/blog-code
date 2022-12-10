package io.github.hyeonic.springtransaction.account.v5;

import io.github.hyeonic.springtransaction.account.Account;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("jdbcAccountRepositoryV5")
public class JdbcAccountRepository {

    private static final String TABLE_NAME = "account";
    private static final String KEY_NAME = "id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcAccountRepository(final JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(KEY_NAME);
    }

    @Transactional
    public Account save(final Account account) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("holder", account.getHolder())
                .addValue("amount", account.getAmount());

        Long id = simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).longValue();
        return new Account(id, account.getHolder(), account.getAmount());
    }

    @Transactional(readOnly = true)
    public Optional<Account> findByHolder(final String holder) {
        try {
            String sql = "SELECT id, holder, amount FROM account WHERE holder = :holder";
            SqlParameterSource parameterSource = new MapSqlParameterSource("holder", holder);
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(sql, parameterSource, generateProductMapper()));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Account> generateProductMapper() {
        return (resultSet, rowNum) -> new Account(
                resultSet.getLong("id"),
                resultSet.getString("holder"),
                resultSet.getLong("amount")
        );
    }

    @Transactional
    public void update(final Account account) {
        String sql = "UPDATE account SET holder = :holder, amount = :amount WHERE id = :id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", account.getId())
                .addValue("holder", account.getHolder())
                .addValue("amount", account.getAmount());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
