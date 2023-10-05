package io.github.hyeonic.externalresourcetransaction.member;

import io.github.hyeonic.externalresourcetransaction.honor.Honor;
import io.github.hyeonic.externalresourcetransaction.honor.HonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private static final String NEWBIE = "뉴비";

    private final MemberRepository memberRepository;
    private final HonorRepository honorRepository;

    @Transactional
    public Member save(final Member member) {
        Member savedMember = memberRepository.save(member);
        honorRepository.save(new Honor(savedMember, NEWBIE));
        return savedMember;
    }
}
