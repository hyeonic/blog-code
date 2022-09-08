package io.github.hyeonic.circularreference.category;

import io.github.hyeonic.circularreference.member.Member;
import io.github.hyeonic.circularreference.member.MemberService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    public CategoryService(final CategoryRepository categoryRepository, final MemberService memberService) {
        this.categoryRepository = categoryRepository;
        this.memberService = memberService;
    }

    @Transactional
    public Category save(final Long memberId, final String name) {
        Member member = memberService.findById(memberId);
        Category newCategory = new Category(name, member);
        return categoryRepository.save(newCategory);
    }

    public Category findById(final Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
