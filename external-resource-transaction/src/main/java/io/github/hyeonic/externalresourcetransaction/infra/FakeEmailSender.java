package io.github.hyeonic.externalresourcetransaction.infra;

import io.github.hyeonic.externalresourcetransaction.member.EmailSender;
import org.springframework.stereotype.Component;

@Component
public class FakeEmailSender implements EmailSender {

    @Override
    public void send(final String email, final String message) {
        try {
            Thread.sleep(2000);
            System.out.println(email + " " + message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
