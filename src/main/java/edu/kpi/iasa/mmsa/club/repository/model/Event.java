package edu.kpi.iasa.mmsa.club.repository.model;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    //@NotBlank(message = "Event name cannot be empty")
    private String eventName;

    @Column(name = "id_rank")
    //@NotBlank(message = "Event rank cannot be empty")
    private Integer idRank;

    @Column(name = "id_organizer")
    //@NotBlank(message = "Event cannot be without organizer")
    private Integer idOrganizer;

    //@NotBlank(message = "Event cannot be without date")
    private Date date;

    //@NotBlank(message = "Event cannot be without duration")
    private String duration;

    @Column(name = "cost_per_human")
    private Double cost;

    //@NotBlank(message = "Event cannot be without place")
    private String place;

    @Column(name = "number_of_spots")
    private Integer freeSpots;

    @Column(name = "already_occupied")
    private Integer occupiedSpots;

    public Event() {
    }

    public Event(String eventName, Integer idRank, Integer idOrganizer, Date date, String duration, Double cost, String place, Integer freeSpots, Integer occupiedSpots) {
        this.eventName = eventName;
        this.idRank = idRank;
        this.idOrganizer = idOrganizer;
        this.date = date;
        this.duration = duration;
        this.cost = cost;
        this.place = place;
        this.freeSpots = freeSpots;
        this.occupiedSpots = occupiedSpots;
    }

    public long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getIdRank() {
        return idRank;
    }

    public void setIdRank(Integer idRank) {
        this.idRank = idRank;
    }

    public Integer getIdOrganizer() {
        return idOrganizer;
    }

    public void setIdOrganizer(Integer idOrganizer) {
        this.idOrganizer = idOrganizer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(Integer freeSpots) {
        this.freeSpots = freeSpots;
    }

    public Integer getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(Integer occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }
}
