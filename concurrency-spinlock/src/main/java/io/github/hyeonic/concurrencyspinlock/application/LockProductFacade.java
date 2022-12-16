package io.github.hyeonic.concurrencyspinlock.application;

import org.springframework.stereotype.Component;

@Component
public class LockProductFacade {

    private final RedisLockRepository redisLockRepository;
    private final ProductService productService;

    public LockProductFacade(final RedisLockRepository redisLockRepository,
                                 final ProductService productService) {
        this.redisLockRepository = redisLockRepository;
        this.productService = productService;
    }

    public void purchase(final Long key, final Long quantity) {
        try {
            redisLockRepository.lock(key);
            productService.purchase(key, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
