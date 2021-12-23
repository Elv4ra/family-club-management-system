package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "member_role")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_member")
    private Member member;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public MemberRole() {
    }

    public MemberRole(Member member, Role role) {
        this.member = member;
        this.role = role;
    }
}
