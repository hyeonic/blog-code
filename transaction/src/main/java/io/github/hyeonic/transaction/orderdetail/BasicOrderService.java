package io.github.hyeonic.transaction.orderdetail;

import io.github.hyeonic.transaction.member.Member;
import io.github.hyeonic.transaction.member.MemberRepository;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

@Service
public class BasicOrderService {

    private final DataSource dataSource;
    private final MemberRepository memberRepository;
    private final OrderDetailRepository orderDetailRepository;

    public BasicOrderService(final DataSource dataSource, final MemberRepository memberRepository,
                             final OrderDetailRepository orderDetailRepository) {
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void order(final OrderRequest request) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);

            Member customer = memberRepository.findById(request.getCustomerId());
            Member owner = memberRepository.findById(request.getOwnerId());

            customer.minusMoney(request.getProductPrice());
            owner.plusMoney(request.getProductPrice());

            memberRepository.update(customer);
            memberRepository.update(owner);

            OrderDetail orderDetail =
                    new OrderDetail(request.getProductName(), request.getProductPrice(), customer.getId(), owner.getId());

            orderDetailRepository.save(orderDetail);

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new IllegalStateException(e);
        } finally {
            connection.close();
        }
    }
}
