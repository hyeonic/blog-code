package io.github.hyeonic.externalresourcetransaction.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    public Member(final String email, final String displayName, final String profileImageUrl) {
        this.email = email;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
    }
}
