package c.hyeoni.whatifibecomeacollegestudent.service;

import c.hyeoni.whatifibecomeacollegestudent.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 세터 주입 방식
 */
@Service
public class MemberServiceV3 {

    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
