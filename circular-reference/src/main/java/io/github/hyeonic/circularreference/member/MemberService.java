package io.github.hyeonic.circularreference.member;

import io.github.hyeonic.circularreference.event.MemberSaveAfterEvent;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private static final String MY_SCHEDULE = "내 일정";

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member save(final String email, final String displayName, MemberSaveAfterEvent memberSaveAfterEvent) {
        Member newMember = memberRepository.save(new Member(email, displayName));

        memberSaveAfterEvent.process(MY_SCHEDULE, newMember);

        return newMember;
    }

    public Member findById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
