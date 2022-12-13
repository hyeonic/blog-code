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
class ProductServiceTest {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    ProductServiceTest(final ProductRepository productRepository,
                       final ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
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

    private void process(final Product product, final CountDownLatch countDownLatch) {
        try {
            productService.purchase(product.getId(), 1L);
        } finally {
            countDownLatch.countDown();
        }
    }
}
