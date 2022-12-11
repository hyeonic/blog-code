package io.github.hyeonic.concurrencysynchronized.application;

import io.github.hyeonic.concurrencysynchronized.domain.Product;
import io.github.hyeonic.concurrencysynchronized.domain.ProductRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void purchase(final Long id, final Long quantity) {
        var foundProduct = getProduct(id);
        foundProduct.decrease(quantity);
    }

    private Product getProduct(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
