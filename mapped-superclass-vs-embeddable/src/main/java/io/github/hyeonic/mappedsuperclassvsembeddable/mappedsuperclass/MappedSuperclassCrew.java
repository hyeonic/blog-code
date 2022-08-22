package io.github.hyeonic.mappedsuperclassvsembeddable.mappedsuperclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MappedSuperclassCrew extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Track track;

    protected MappedSuperclassCrew() {
    }

    public MappedSuperclassCrew(final String name, final Integer age, final Track track) {
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

    @Override
    public String toString() {
        return "MappedSuperclassCrew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", track=" + track +
                ", createAt=" + getCreatedAt() +
                '}';
    }
}
