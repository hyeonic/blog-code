package io.github.hyeonic.transaction.orderdetail;

import io.github.hyeonic.transaction.member.Member;
import io.github.hyeonic.transaction.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class DeclarativeOrderService {

    private final MemberRepository memberRepository;
    private final OrderDetailRepository orderDetailRepository;

    public DeclarativeOrderService(final MemberRepository memberRepository,
                                   final OrderDetailRepository orderDetailRepository) {
        this.memberRepository = memberRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    public void order(final OrderRequest request) {
        Member customer = memberRepository.findById(request.getCustomerId());
        Member owner = memberRepository.findById(request.getOwnerId());

        customer.minusMoney(request.getProductPrice());
        owner.plusMoney(request.getProductPrice());

        memberRepository.update(customer);
        memberRepository.update(owner);

        OrderDetail orderDetail =
                new OrderDetail(request.getProductName(), request.getProductPrice(), customer.getId(), owner.getId());

        orderDetailRepository.save(orderDetail);
    }
}
