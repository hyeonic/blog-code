package io.github.hyeonic.concurrencynamedlock.application;

import io.github.hyeonic.concurrencynamedlock.infra.UserLockTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserLockProductFacade {

    private static final double TIMEOUT = 5;

    private final UserLockTemplate userLockTemplate;
    private final ProductService productService;

    public UserLockProductFacade(final UserLockTemplate userLockTemplate, final ProductService productService) {
        this.userLockTemplate = userLockTemplate;
        this.productService = productService;
    }

    public void purchase(final Long id, final Long quantity) {
        userLockTemplate.executeWithLockWithoutResult(generateUserLockName(id), TIMEOUT, () -> {
            productService.purchase(id, quantity);
        });
    }

    private String generateUserLockName(final Long id) {
        return id.toString();
    }
}
