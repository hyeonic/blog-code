package io.github.hyeonic.circularreference.category;

import io.github.hyeonic.circularreference.member.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected Category() {
    }

    public Category(final String name, final Member member) {
        this.name = name;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Member getMember() {
        return member;
    }
}
