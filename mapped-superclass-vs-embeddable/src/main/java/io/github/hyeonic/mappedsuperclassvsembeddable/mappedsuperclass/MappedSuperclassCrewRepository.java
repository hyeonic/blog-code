package io.github.hyeonic.mappedsuperclassvsembeddable.mappedsuperclass;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MappedSuperclassCrewRepository extends JpaRepository<MappedSuperclassCrew, Long> {

    @Query("SELECT c "
            + "FROM MappedSuperclassCrew c "
            + "WHERE c.createdAt > :dateTime")
    List<MappedSuperclassCrew> findByCreatedAtGreaterThan(final LocalDateTime dateTime);
}
