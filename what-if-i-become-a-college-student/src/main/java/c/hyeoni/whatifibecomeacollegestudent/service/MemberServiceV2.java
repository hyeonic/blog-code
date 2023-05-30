package c.hyeoni.whatifibecomeacollegestudent.service;

import c.hyeoni.whatifibecomeacollegestudent.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 필드 주입 방식
 */
@Service
public class MemberServiceV2 {

    @Autowired
    private MemberRepository memberRepository;
}
