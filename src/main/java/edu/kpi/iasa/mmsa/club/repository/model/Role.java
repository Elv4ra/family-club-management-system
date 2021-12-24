package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "validation.text.error.required.field")
    private String code;

    @Column(unique = true, name = "rname")
    @NotNull(message = "validation.text.error.required.field")
    private String roleName;

    public Role() {
    }

    public Role(String roleName) throws IllegalArgumentException {
        this.roleName = roleName;
    }

    @Override
    @Transient
    public String getAuthority() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        GrantedAuthority ga = (GrantedAuthority) o;
        return (getAuthority().equals(ga.getAuthority()));
    }

    @Override
    public int hashCode() {
        return getAuthority().hashCode();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + roleName + '\'' +
                '}';
    }
}
