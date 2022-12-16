package io.github.hyeonic.concurrencyspinlock.application;

import org.springframework.stereotype.Component;

@Component
public class SpinlockProductFacade {

    private final RedisLockRepository redisLockRepository;
    private final ProductService productService;

    public SpinlockProductFacade(final RedisLockRepository redisLockRepository,
                                 final ProductService productService) {
        this.redisLockRepository = redisLockRepository;
        this.productService = productService;
    }

    public void purchase(final Long key, final Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(key)) {
            Thread.sleep(100);
        }

        try {
            productService.purchase(key, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
