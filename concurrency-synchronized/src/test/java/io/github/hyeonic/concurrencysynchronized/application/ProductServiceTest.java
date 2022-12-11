package io.github.hyeonic.concurrencysynchronized.application;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.hyeonic.concurrencysynchronized.domain.Product;
import io.github.hyeonic.concurrencysynchronized.domain.ProductRepository;
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
    private final SynchronizedProductService synchronizedProductService;

    @Autowired
    ProductServiceTest(final ProductRepository productRepository,
                       final ProductService productService,
                       final SynchronizedProductService synchronizedProductService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.synchronizedProductService = synchronizedProductService;
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
            synchronizedProductService.purchase(product.getId(), 1L);
        } finally {
            countDownLatch.countDown();
        }
    }
}
