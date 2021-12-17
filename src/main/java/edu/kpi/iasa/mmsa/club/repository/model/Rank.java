package edu.kpi.iasa.mmsa.club.repository.model;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "rank_name")
    //NotBlank(message = "Rank name cannot be empty")
    private String rankName;

    public Rank() {
    }

    public Rank(String rankName) {
        this.rankName = rankName;
    }

    public long getId() {
        return id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
