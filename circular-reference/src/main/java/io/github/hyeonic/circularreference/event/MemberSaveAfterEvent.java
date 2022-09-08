package io.github.hyeonic.circularreference.event;

import io.github.hyeonic.circularreference.category.Category;
import io.github.hyeonic.circularreference.category.CategoryService;
import io.github.hyeonic.circularreference.member.Member;
import io.github.hyeonic.circularreference.subscription.SubscriptionService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class MemberSaveAfterEvent {

    private final CategoryService categoryService;
    private final SubscriptionService subscriptionService;

    public MemberSaveAfterEvent(final CategoryService categoryService, final SubscriptionService subscriptionService) {
        this.categoryService = categoryService;
        this.subscriptionService = subscriptionService;
    }

    @Transactional
    public void process(final String name, final Member member) {
        Category newCategory = categoryService.save(member.getId(), name);
        subscriptionService.save(member.getId(), newCategory.getId());
    }
}
