package io.github.hyeonic.mappedsuperclassvsembeddable.embedded;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TraceDateTime {

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    protected TraceDateTime() {
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
