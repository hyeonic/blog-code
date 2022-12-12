package io.github.hyeonic.concurrencypessimisticlock.domain;

import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> findByIdWithPessimisticLock(final Long id);

    @Modifying
    @Query("UPDATE Product p "
            + "SET p.quantity = p.quantity - :quantity "
            + "WHERE p.id = :id AND p.quantity > 0")
    void decreaseQuantity(final Long quantity, final Long id);
}
