package io.github.hyeonic.externalresourcetransaction.member;

public interface EmailSender {

    void send(final String email, final String message);
}
