package io.github.hyeonic.circularreference.member;

import io.github.hyeonic.circularreference.category.Category;
import io.github.hyeonic.circularreference.category.CategoryService;
import io.github.hyeonic.circularreference.subscription.SubscriptionService;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private static final String MY_SCHEDULE = "내 일정";

    private final CategoryService categoryService;
    private final SubscriptionService subscriptionService;
    private final MemberRepository memberRepository;

    public MemberService(final CategoryService categoryService, final SubscriptionService subscriptionService,
                         final MemberRepository memberRepository) {
        this.categoryService = categoryService;
        this.subscriptionService = subscriptionService;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member save(final String email, final String displayName) {
        Member newMember = memberRepository.save(new Member(email, displayName));

        Category newCategory = categoryService.save(newMember.getId(), MY_SCHEDULE);
        subscriptionService.save(newMember.getId(), newCategory.getId());

        return newMember;
    }

    public Member findById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
