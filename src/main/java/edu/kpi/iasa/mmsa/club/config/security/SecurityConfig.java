package edu.kpi.iasa.mmsa.club.config.security;

import edu.kpi.iasa.mmsa.club.config.security.jwt.JwtAuthenticationEntryPoint;
import edu.kpi.iasa.mmsa.club.config.security.jwt.JwtConfigurer;
import edu.kpi.iasa.mmsa.club.config.security.jwt.JwtProcessor;
import edu.kpi.iasa.mmsa.club.config.security.jwt.JwtProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtProperties jwtProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtConfigurer jwtConfigurer() {
        return new JwtConfigurer(jwtTokenProvider());
    }

    @Bean
    public JwtProcessor jwtTokenProvider() {
        return new JwtProcessor(userDetailsService, jwtProperties);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("ROLE_USER");
        return authorityMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signin").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .antMatchers(HttpMethod.POST, "/requests").permitAll()
                .antMatchers(HttpMethod.POST, "/finances").permitAll()
                .antMatchers(HttpMethod.POST, "/events").hasAuthority("ROLE_ORGANIZER")
                .antMatchers(HttpMethod.PUT, "/events/*").hasAuthority("ROLE_ORGANIZER")
                .antMatchers(HttpMethod.DELETE, "/events/*").hasAuthority("ROLE_ORGANIZER")
                .antMatchers(HttpMethod.PUT, "/finances/*").hasAuthority("ROLE_ORGANIZER")
                .antMatchers(HttpMethod.DELETE, "/finances/*").hasAuthority("ROLE_ORGANIZER")
                .antMatchers(HttpMethod.POST, "/ranks").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/ranks/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/ranks/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.POST, "/roles").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/roles/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/roles/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/requests/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/requests/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.POST, "/membersroles").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/membersroles/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.POST, "/members").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/members/*").hasAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/members/*").hasAuthority("ROLE_MANAGER")
                .antMatchers("/*").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(jwtConfigurer());
    }

}
