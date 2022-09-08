package io.github.hyeonic.circularreference.presentation;

import io.github.hyeonic.circularreference.event.MemberSaveAfterEvent;
import io.github.hyeonic.circularreference.member.Member;
import io.github.hyeonic.circularreference.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberSaveAfterEvent memberSaveAfterEvent;

    public MemberController(final MemberService memberService, final MemberSaveAfterEvent memberSaveAfterEvent) {
        this.memberService = memberService;
        this.memberSaveAfterEvent = memberSaveAfterEvent;
    }

    @PostMapping
    public ResponseEntity<Member> save(@RequestParam String email, @RequestParam String displayName) {
        Member member = memberService.save(email, displayName, memberSaveAfterEvent);
        return ResponseEntity.ok(member);
    }
}
