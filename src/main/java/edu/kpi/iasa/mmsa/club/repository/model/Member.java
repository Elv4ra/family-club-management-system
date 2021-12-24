package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "members")
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "validation.text.error.required.field")
    @Size(min = 6, max = 50, message = "validation.text.error.from.six.to.fifty")
    @Column(unique = true)
    private String login;

    @NotNull(message = "validation.text.error.required.field")
    private String password;

    @NotNull(message = "validation.text.error.required.field")
    @Size(min = 3, max = 50, message = "validation.text.error.from.three.to.fifty")
    private String name;

    @NotNull(message = "validation.text.error.required.field")
    @Size(min = 3, max = 50, message = "validation.text.error.from.three.to.fifty")
    private String alias;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rank")
    private Rank memberRank = new Rank(1, "Bronze");

    @NotNull(message = "validation.text.error.required.field")
    @Pattern(regexp = "\\+\\d{12}", message = "validation.text.phone.error.sample")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActiveMember = true;

    @Column(name = "date_joining")
    private Timestamp joiningDate = new Timestamp(System.currentTimeMillis());

    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = {@JoinColumn(name = "id_member", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")})
    private Collection<Role> roles = new HashSet<>();

    public Member() {
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public Collection<Role> getRoles() {
        return roles;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getRoles());
        return authorities;
    }

    public boolean hasRole(String role) {
        for (Role r : getRoles()) {
            if (r.getCode().equals(role)) return true;
        }
        return false;
    }

    public String toString() {
        return login;
    }
}
