package io.github.hyeonic.mappedsuperclassvsembeddable.embedded;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmbeddedCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Track track;

    @Embedded
    private TraceDateTime traceDateTime;

    protected EmbeddedCrew() {
    }

    public EmbeddedCrew(final String name, final Integer age, final Track track) {
        this.name = name;
        this.age = age;
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Track getTrack() {
        return track;
    }

    public TraceDateTime getTraceDateTime() {
        return traceDateTime;
    }
}
