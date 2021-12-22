package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "rname")
    @NotBlank(message = "Role name cannot be empty")
    private String roleName;

    public Role() {
    }

    public Role(String roleName) throws IllegalArgumentException {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
