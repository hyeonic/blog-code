package io.github.hyeonic.jpasave.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    MemberRepositoryTest(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @Rollback(value = false)
    void 식별자가_없는_member를_저장한다() {
        Member member = new Member(null, "매트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }

    @Test
    @Rollback(value = false)
    void 식별자를_1로_지정한_member를_저장한다() {
        Member member = new Member(1L, "매트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }

    @Test
    @Rollback(value = false)
    void 저장된_member를_다시_저장한다() {
        Member member = memberRepository.save(new Member(1L, "매트"));
        member.setName("패트");

        Member actual = memberRepository.save(member);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }
}
