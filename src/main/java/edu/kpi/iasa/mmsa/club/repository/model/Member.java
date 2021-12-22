package edu.kpi.iasa.mmsa.club.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "members")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public final class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Login cannot be empty")
    @Column(unique = true)
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Alias cannot be empty")
    private String alias;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rank")
    private Rank memberRank = new Rank(1, "Bronze");

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @Column(name = "is_active")
    private Boolean isActiveMember = true;

    @Column(name = "date_joining")
    private Timestamp joiningDate = new Timestamp(System.currentTimeMillis());

    public Member() {
    }

    public Member(String login, String password, String name, String alias, String phone) throws IllegalArgumentException{
        this.login = login;
        this.password = password;
        this.name = name;
        this.alias = alias;
        this.phone = phone;
        //this.events = new ArrayList<>();
    }
}
