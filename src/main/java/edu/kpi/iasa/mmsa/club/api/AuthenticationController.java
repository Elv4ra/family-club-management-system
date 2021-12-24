package edu.kpi.iasa.mmsa.club.api;

import edu.kpi.iasa.mmsa.club.api.dto.JwtRequestDto;
import edu.kpi.iasa.mmsa.club.api.dto.JwtResponseDto;
import edu.kpi.iasa.mmsa.club.api.dto.MemberDto;
import edu.kpi.iasa.mmsa.club.api.dto.RegistrationDto;
import edu.kpi.iasa.mmsa.club.config.security.jwt.JwtProcessor;
import edu.kpi.iasa.mmsa.club.repository.model.Member;
import edu.kpi.iasa.mmsa.club.repository.model.Rank;
import edu.kpi.iasa.mmsa.club.service.MemberService;
import edu.kpi.iasa.mmsa.club.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Controller
public class AuthenticationController {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtProcessor jwtProcessor,
                                    UserDetailsService userDetailsService,
                                    MemberService memberService,
                                    PasswordEncoder passwordEncoder,
                                    RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtProcessor = jwtProcessor;
        this.userDetailsService = userDetailsService;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody JwtRequestDto jwtRequestDto) {
        String username = jwtRequestDto.getLogin();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, jwtRequestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtProcessor.createJwt(username,
                (Collection<GrantedAuthority>) userDetails.getAuthorities());
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signUp(@RequestBody RegistrationDto registrationDto) {
        Member member = createMember(registrationDto);
        return ResponseEntity.ok(createMemberDto(memberService.createMember(member)));
    }

    private MemberDto createMemberDto(Member member) {
        return new MemberDto(member.getLogin());
    }

    private Member createMember(RegistrationDto registrationDto) {
        Member member = Member.builder()
                .login(registrationDto.getLogin())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .name(registrationDto.getName())
                .alias(registrationDto.getAlias())
                .memberRank(new Rank(1, "Bronze"))
                .phone(registrationDto.getPhone())
                .isActiveMember(true)
                .joiningDate(new Timestamp(System.currentTimeMillis()))
                .build();
        member.setRoles(Collections.singleton(roleService.getRoleByCode(DEFAULT_ROLE)));
        return member;
    }
}
