package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "request")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Request cannot be without author")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Member author;

    @NotBlank(message = "Request must have option for changing role or rank")
    @Column(name = "rank_or_role")
    private String objectOfChanging;

    @NotBlank(message = "Request cannot be without description")
    private String description;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modifier")
    private Member modifier;

    public Request() {
    }

    public Request(Member author, String objectOfChanging, String description, String status, Member modifier) throws IllegalArgumentException {
        this.author = author;
        this.objectOfChanging = objectOfChanging;
        this.description = description;
        this.status = status;
        this.modifier = modifier;
    }
}
