package io.github.hyeonic.transaction.orderdetail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import io.github.hyeonic.transaction.member.Member;
import io.github.hyeonic.transaction.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
class DeclarativeOrderServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DeclarativeOrderService declarativeOrderService;

    @DisplayName("치킨을 주문한다.")
    @Test
    void 치킨을_주문한다() {
        // given
        Member customer = memberRepository.save(new Member(50_000));
        Member owner = memberRepository.save(new Member(100_000));

        OrderRequest request = new OrderRequest("후라이드 치킨", 20000, customer.getId(), owner.getId());

        // when
        declarativeOrderService.order(request);

        // then
        Member foundCustomer = memberRepository.findById(customer.getId());
        Member foundOwner = memberRepository.findById(owner.getId());

        assertAll(() -> {
            assertThat(foundCustomer.getMoney()).isEqualTo(30_000);
            assertThat(foundOwner.getMoney()).isEqualTo(120_000);
        });
    }

    @DisplayName("치킨을 주문 시 문제가 발생하면 rollback한다.")
    @Test
    void 치킨을_주문_시_문제가_발생하면_rollback한다() {
        // given
        Member customer = memberRepository.save(new Member(50_000));
        Member owner = memberRepository.save(new Member(100_000));

        OrderRequest request = new OrderRequest("후라이드 치킨", 20000, null, owner.getId());

        // when
        // customerId not null 제약 조건으로 예외 발생
        assertThatThrownBy(() -> declarativeOrderService.order(request))
                .isInstanceOf(EmptyResultDataAccessException.class);

        // then
        Member foundCustomer = memberRepository.findById(customer.getId());
        Member foundOwner = memberRepository.findById(owner.getId());

        assertAll(() -> {
            assertThat(foundCustomer.getMoney()).isEqualTo(50_000);
            assertThat(foundOwner.getMoney()).isEqualTo(100_000);
        });
    }
}
