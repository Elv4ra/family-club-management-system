package edu.kpi.iasa.mmsa.club.repository.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "members")
public class Member {

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
    private Rank rank = new Rank(1, "Bronze");

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
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActiveMember() {
        return isActiveMember;
    }

    public void setActiveMember(Boolean activeMember) {
        isActiveMember = activeMember;
    }

    public Timestamp getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Timestamp joiningDate) {
        this.joiningDate = joiningDate;
    }
}
