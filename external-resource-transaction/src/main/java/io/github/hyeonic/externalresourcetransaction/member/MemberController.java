package io.github.hyeonic.externalresourcetransaction.member;

import io.github.hyeonic.externalresourcetransaction.member.dto.MemberRegisterRequest;
import io.github.hyeonic.externalresourcetransaction.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberRegister memberRegister;

    @PostMapping
    public ResponseEntity<MemberResponse> register(@RequestBody MemberRegisterRequest request) {
        Member savedMember = memberRegister.register(request.getCode());
        MemberResponse response = new MemberResponse(savedMember);
        return ResponseEntity.ok(response);
    }
}
