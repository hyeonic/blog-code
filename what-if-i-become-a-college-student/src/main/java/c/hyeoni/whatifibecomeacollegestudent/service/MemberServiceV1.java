package c.hyeoni.whatifibecomeacollegestudent.service;

import c.hyeoni.whatifibecomeacollegestudent.domain.MemberRepository;
import org.springframework.stereotype.Service;

/**
 * 생성자 주입 방식
 */
@Service
public class MemberServiceV1 {

    private final MemberRepository memberRepository;

    public MemberServiceV1(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
