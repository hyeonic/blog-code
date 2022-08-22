package io.github.hyeonic.mappedsuperclassvsembeddable.embedded;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmbeddedCrewRepository extends JpaRepository<EmbeddedCrew, Long> {

    @Query("SELECT c "
            + "FROM EmbeddedCrew c "
            + "WHERE c.traceDateTime.createAt > :dateTime")
    List<EmbeddedCrewRepository> findByCreatedAtGreaterThan(final LocalDateTime dateTime);
}
