package io.github.hyeonic.externalresourcetransaction.member.dto;

import io.github.hyeonic.externalresourcetransaction.member.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;
    private final String email;
    private final String displayName;
    private final String profileImageUrl;

    public MemberResponse(final Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.displayName = member.getDisplayName();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
