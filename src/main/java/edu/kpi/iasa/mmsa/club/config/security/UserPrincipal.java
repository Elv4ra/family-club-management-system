package edu.kpi.iasa.mmsa.club.config.security;

import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.repository.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private final Member member;

    public UserPrincipal(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();
        String[] authorities = member.getRoles()
                .stream()
                .map(Role::getAuthority)
                .toArray(String[]::new);
        Arrays.stream(authorities)
                .forEach(s -> grantedAuthoritySet.add(new SimpleGrantedAuthority(s)));
        return grantedAuthoritySet;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return member.getIsActiveMember();
    }
}
