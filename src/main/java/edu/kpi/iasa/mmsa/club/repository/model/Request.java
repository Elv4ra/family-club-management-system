package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "request")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Request cannot be without author")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Member author;

    @NotNull(message = "Request must have option for changing role or rank")
    @Column(name = "rank_or_role")
    private RankOrRole objectOfChanging;

    @NotNull(message = "Request cannot be without description")
    private String description;

    private Status status = Status.INPROCESS;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modifier")
    private Member modifier = null;

    public Request() {
    }

    public Request(Member author, RankOrRole objectOfChanging, String description) {
        this.author = author;
        this.objectOfChanging = objectOfChanging;
        this.description = description;
    }
}
