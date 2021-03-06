package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "ranks")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "validation.text.error.required.field")
    @Column(unique = true, name = "rank_name")
    private String rankName;

    public Rank() {
    }

    public Rank(long id, String rankName) {
        this.id = id;
        this.rankName = rankName;
    }

    public Rank(String rankName) throws IllegalArgumentException {
        this.rankName = rankName;
    }
}
