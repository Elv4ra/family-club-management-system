package edu.kpi.iasa.mmsa.club.repository.model;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NotBlank(message = "Request cannot be without author")
    @Column(name = "id_author")
    private Integer idAuthor;

    //@NotBlank(message = "Request must have option for changing role or rank")
    @Column(name = "rank_or_role")
    private String objectOfChanging;

    //@NotBlank(message = "Request cannot be without description")
    private String description;

    private String status;

    @Column(name = "id_modifier")
    private Integer idModifier;

    public Request() {
    }

    public Request(Integer idAuthor, String objectOfChanging, String description, String status, Integer idModifier) {
        this.idAuthor = idAuthor;
        this.objectOfChanging = objectOfChanging;
        this.description = description;
        this.status = status;
        this.idModifier = idModifier;
    }

    public long getId() {
        return id;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getObjectOfChanging() {
        return objectOfChanging;
    }

    public void setObjectOfChanging(String objectOfChanging) {
        this.objectOfChanging = objectOfChanging;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdModifier() {
        return idModifier;
    }

    public void setIdModifier(Integer idModifier) {
        this.idModifier = idModifier;
    }
}
