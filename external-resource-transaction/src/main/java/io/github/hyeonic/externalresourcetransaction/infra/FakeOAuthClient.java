package io.github.hyeonic.externalresourcetransaction.infra;

import io.github.hyeonic.externalresourcetransaction.member.Member;
import io.github.hyeonic.externalresourcetransaction.member.OAuthClient;
import org.springframework.stereotype.Component;

@Component
public class FakeOAuthClient implements OAuthClient {

    @Override
    public Member getMember(final String code) {
        try {
            Thread.sleep(2000);
            return new Member("mat@email.com", "mat", "sample.png");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
