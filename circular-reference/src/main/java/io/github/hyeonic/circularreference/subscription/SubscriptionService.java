package io.github.hyeonic.circularreference.subscription;

import io.github.hyeonic.circularreference.category.Category;
import io.github.hyeonic.circularreference.category.CategoryService;
import io.github.hyeonic.circularreference.member.Member;
import io.github.hyeonic.circularreference.member.MemberService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(final MemberService memberService, final CategoryService categoryService,
                               final SubscriptionRepository subscriptionRepository) {
        this.memberService = memberService;
        this.categoryService = categoryService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public Subscription save(final Long memberId, final Long categoryId) {
        Member foundMember = memberService.findById(memberId);
        Category foundCategory = categoryService.findById(categoryId);
        Subscription newSubscription = new Subscription(foundMember, foundCategory);
        return subscriptionRepository.save(newSubscription);
    }
}
