package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "finances")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Finances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "validation.text.error.required.field")
    @ManyToOne
    @JoinColumn(name = "id_member")
    private Member member;

    @NotNull(message = "validation.text.error.required.field")
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "already_paid")
    private Boolean alreadyPaid = false;

    public Finances() {
    }

    public Finances(Member member, Event event) {
        this.member = member;
        this.event = event;
    }
}
