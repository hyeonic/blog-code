package io.github.hyeonic.jpasave.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PrimitiveMember {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    protected PrimitiveMember() {
    }

    public PrimitiveMember(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
