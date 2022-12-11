package io.github.hyeonic.concurrencysynchronized.application;

import org.springframework.stereotype.Component;

@Component
public class SynchronizedProductService {

    private final ProductService productService;

    public SynchronizedProductService(final ProductService productService) {
        this.productService = productService;
    }

    public synchronized void purchase(final Long id, final Long quantity) {
        productService.purchase(id, quantity);
    }
}
