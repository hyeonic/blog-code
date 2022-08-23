package io.github.hyeonic.transaction.orderdetail;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailJdbcRepository implements OrderDetailRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public OrderDetailJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("order_detail")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public OrderDetail save(final OrderDetail orderDetail) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_name", orderDetail.getProductName())
                .addValue("product_price", orderDetail.getProductPrice())
                .addValue("customer_id", orderDetail.getCustomerId())
                .addValue("owner_id", orderDetail.getOwnerId());

        Long id = simpleJdbcInsert.executeAndReturnKey(parameterSource).longValue();
        return new OrderDetail(id, orderDetail);
    }
}
