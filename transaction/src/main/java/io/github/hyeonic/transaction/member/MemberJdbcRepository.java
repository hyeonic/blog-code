package io.github.hyeonic.transaction.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJdbcRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MemberJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Member save(final Member member) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("money", member.getMoney());

        Long id = simpleJdbcInsert.executeAndReturnKey(parameterSource).longValue();
        return new Member(id, member);
    }

    @Override
    public Member findById(final Long id) {
        String sql = "SELECT m.id as id, m.money as money "
                + "FROM member m "
                + "WHERE m.id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, generateMember());
    }

    private RowMapper<Member> generateMember() {
        return (resultSet, rowNum) ->
                new Member(
                        resultSet.getLong("id"),
                        resultSet.getInt("money")
                );
    }

    @Override
    public void update(final Member member) {
        String sql = "UPDATE member SET money = :money WHERE id = :id";

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        jdbcTemplate.update(sql, parameterSource);
    }
}
