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
    private static final String CONGRATULATIONS_MESSAGE = "가입을 축하드립니다!";

    private final MemberRepository memberRepository;
    private final HonorRepository honorRepository;
    private final OAuthClient oAuthClient;
    private final EmailSender emailSender;

    @Transactional
    public Member register(final String code) {
        Member member = oAuthClient.getMember(code);

        Member savedMember = memberRepository.save(member);
        honorRepository.save(new Honor(savedMember, NEWBIE));

        emailSender.send(savedMember.getEmail(), CONGRATULATIONS_MESSAGE);

        return savedMember;
    }
}
