package io.github.hyeonic.concurrencyoptimisticlock.application;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockProductFacade {

    private final ProductService productService;

    public OptimisticLockProductFacade(final ProductService productService) {
        this.productService = productService;
    }

    public void purchase(final Long id, final Long quantity) throws InterruptedException {
        while (true) {
            try {
                productService.purchase(id, quantity);
                break;
            } catch (final ObjectOptimisticLockingFailureException e) {
                Thread.sleep(50);
            }
        }
    }
}
