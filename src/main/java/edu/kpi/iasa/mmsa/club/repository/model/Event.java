package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "events")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "garbage name cannot be empty")
    private String eventName;

    @NotNull(message = "garbage rank cannot be empty")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rank")
    private Rank eventRank;

    @NotNull(message = "garbage cannot be without organizer")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_organizer")
    private Member organizer;

    @NotNull(message = "garbage cannot be without date")
    private Timestamp date;

    @NotBlank(message = "garbage cannot be without duration")
    private String duration;

    @Column(name = "cost_per_human")
    private Double cost;

    @NotBlank(message = "garbage cannot be without place")
    private String place;

    @Column(name = "number_of_spots")
    private Integer freeSpots;

    @Column(name = "already_occupied")
    private Integer occupiedSpots = 1;

    public Event() {
    }

    public Event(String eventName, Rank eventRank, Member organizer, Timestamp date, String duration, Double cost, String place, Integer freeSpots) throws IllegalArgumentException {
        this.eventName = eventName;
        this.eventRank = eventRank;
        this.organizer = organizer;
        this.date = date;
        this.duration = duration;
        this.cost = cost;
        this.place = place;
        this.freeSpots = freeSpots;
    }
}
