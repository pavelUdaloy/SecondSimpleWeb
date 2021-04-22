package by.innowise.second.simple.security;

import by.innowise.second.simple.filter.CustomAuthenticationProvider;
import by.innowise.second.simple.filter.CustomJwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUtil jwtTokenUtil;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(JwtUtil jwtTokenUtil,
                          CustomAuthenticationProvider customAuthenticationProvider) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth", "/reg").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .addFilterBefore(new CustomJwtFilter(jwtTokenUtil, authenticationManagerBean()), AnonymousAuthenticationFilter.class);
    }
}
