package io.github.hyeonic.jpasave.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PrimitiveMemberRepositoryTest {

    private final PrimitiveMemberRepository primitiveMemberRepository;

    @Autowired
    public PrimitiveMemberRepositoryTest(final PrimitiveMemberRepository primitiveMemberRepository) {
        this.primitiveMemberRepository = primitiveMemberRepository;
    }

    @Test
    void 식별자가_없는_member를_저장한다() {
        PrimitiveMember primitiveMember = new PrimitiveMember(1, "매트");

        PrimitiveMember actual = primitiveMemberRepository.save(primitiveMember);

        assertAll(() -> {
            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getName()).isEqualTo("매트");
        });
    }
}
