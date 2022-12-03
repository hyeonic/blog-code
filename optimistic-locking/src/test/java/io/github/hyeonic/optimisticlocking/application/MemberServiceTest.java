package io.github.hyeonic.optimisticlocking.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.github.hyeonic.optimisticlocking.domain.Member;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootTest
class MemberServiceTest {

    private final MemberService memberService;

    @Autowired
    MemberServiceTest(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    void member를_저장한다() {
        Member member = memberService.save(new Member("매트"));

        System.out.println(member);
    }

    @Test
    void member를_수정한다() {
        Member member = memberService.save(new Member("version 0"));

        memberService.changeName(member.getId(), "version 1");
        memberService.changeName(member.getId(), "version 2");
        Member actual = memberService.findById(member.getId());

        System.out.println(actual);
    }

    @Test
    void member를_동시에_수정한다() throws InterruptedException {
        Member member = memberService.save(new Member("version 0"));

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                try {
                    memberService.changeName(member.getId(), "version change");
                } catch (final ObjectOptimisticLockingFailureException e) {
                    count.incrementAndGet();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        Member actual = memberService.findById(member.getId());

        assertAll(() -> {
            assertThat(count.get()).isEqualTo(1);
            assertThat(actual.getName()).isEqualTo("version change");
        });
    }
}
