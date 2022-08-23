package io.github.hyeonic.transaction.member;

public interface MemberRepository {

    Member save(final Member member);

    Member findById(final Long id);

    void update(final Member member);
}
