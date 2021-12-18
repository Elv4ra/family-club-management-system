package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "rank_name")
    @NotBlank(message = "Rank name cannot be empty")
    private String rankName;

    @OneToMany(mappedBy = "rank", fetch = FetchType.LAZY)
    private List<Member> members;

    public Rank() {
    }

    public Rank(long id, String rankName) {
        this.id = id;
        this.rankName = rankName;
    }

    public Rank(String rankName) throws IllegalArgumentException {
        this.rankName = rankName;
        this.members = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(Rank rank) {
        this.rankName = rank.getRankName();
    }
}
