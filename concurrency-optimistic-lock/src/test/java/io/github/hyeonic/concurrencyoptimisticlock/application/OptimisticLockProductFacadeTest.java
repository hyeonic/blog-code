package io.github.hyeonic.concurrencyoptimisticlock.application;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.hyeonic.concurrencyoptimisticlock.domain.Product;
import io.github.hyeonic.concurrencyoptimisticlock.domain.ProductRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(ReplaceUnderscores.class)
class OptimisticLockProductFacadeTest {

    private final ProductRepository productRepository;
    private final OptimisticLockProductFacade optimisticLockProductFacade;

    @Autowired
    OptimisticLockProductFacadeTest(final ProductRepository productRepository,
                                    final OptimisticLockProductFacade optimisticLockProductFacade) {
        this.productRepository = productRepository;
        this.optimisticLockProductFacade = optimisticLockProductFacade;
    }

    @Test
    void 동시에_100개의_상품을_구매한다() throws InterruptedException {
        var product = productRepository.save(new Product("치킨", 100L));

        var executorService = Executors.newFixedThreadPool(10);
        var countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> process(product, countDownLatch));
        }

        countDownLatch.await();

        var actual = productRepository.findById(product.getId()).orElseThrow();

        assertThat(actual.getQuantity()).isEqualTo(0L);
    }

    @Test
    void 상품의_수량은_100개_이지만_동시에_200개의_상품을_구매한다() throws InterruptedException {
        var product = productRepository.save(new Product("치킨", 100L));

        var executorService = Executors.newFixedThreadPool(10);
        var countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> process(product, countDownLatch));
        }

        countDownLatch.await();

        var actual = productRepository.findById(product.getId()).orElseThrow();

        assertThat(actual.getQuantity()).isEqualTo(0L);
    }

    private void process(final Product product, final CountDownLatch countDownLatch) {
        try {
            optimisticLockProductFacade.purchase(product.getId(), 1L);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            countDownLatch.countDown();
        }
    }
}
