package io.github.hyeonic.externalresourcetransaction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberRegister {

    private static final String CONGRATULATIONS_MESSAGE = "가입을 축하드립니다!";

    private final MemberService memberService;
    private final OAuthClient oAuthClient;
    private final EmailSender emailSender;

    public Member register(final String code) {
        Member member = oAuthClient.getMember(code);
        Member savedMember = memberService.save(member);
        emailSender.send(savedMember.getEmail(), CONGRATULATIONS_MESSAGE);
        return savedMember;
    }
}
