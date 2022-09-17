package io.github.hyeonic.externalresourcetransaction.member;

public interface OAuthClient {

    Member getMember(final String code);
}
