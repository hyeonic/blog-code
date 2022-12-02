package io.github.hyeonic.optimisticlocking.application;

import io.github.hyeonic.optimisticlocking.domain.Member;
import io.github.hyeonic.optimisticlocking.domain.MemberRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member save(final Member member) {
        return memberRepository.save(member);
    }

    public Member findById(final Long id) {
        return getMember(id);
    }

    @Transactional
    public void changeName(final Long id, final String name) {
        Member foundMember = getMember(id);
        foundMember.changeName(name);
    }

    private Member getMember(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
