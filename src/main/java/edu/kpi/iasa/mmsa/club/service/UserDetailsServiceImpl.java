package edu.kpi.iasa.mmsa.club.service;

import edu.kpi.iasa.mmsa.club.config.security.UserPrincipal;
import edu.kpi.iasa.mmsa.club.exception.MemberNotFoundException;
import edu.kpi.iasa.mmsa.club.repository.MemberRepository;
import edu.kpi.iasa.mmsa.club.repository.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String login) throws UsernameNotFoundException {
        Member user = memberRepository.findByLogin(login).orElseThrow(MemberNotFoundException::new);
        return new UserPrincipal(user);
    }
}
