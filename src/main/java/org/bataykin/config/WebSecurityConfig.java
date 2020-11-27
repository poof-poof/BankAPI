package org.bataykin.config;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bataykin.security.handler.AuthEntryPointJwt;
import org.bataykin.security.handler.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static lombok.AccessLevel.PRIVATE;
import static org.bataykin.constants.WebConstants.ANY_URL;
import static org.bataykin.constants.WebConstants.CSRF_IGNORE_URL;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@NoArgsConstructor(access = PRIVATE)
public class WebSecurityConfig {

    @Profile("!stub")
    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;
        private final AuthEntryPointJwt unauthorizedHandler;
        private final AuthTokenFilter authenticationJwtTokenFilter;
        private final PasswordEncoder passwordEncoder;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers(POST, CSRF_IGNORE_URL)
                    .antMatchers(OPTIONS, ANY_URL);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf
                    .ignoringAntMatchers(CSRF_IGNORE_URL)
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .authorizeRequests(authorize -> authorize
                            .antMatchers(ANY_URL)
                            .access("isAuthenticated()"))
                    .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
                    .addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(STATELESS);
        }
    }

    @Profile("stub")
    @Configuration
    static class NoSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(final WebSecurity web) {
            web.ignoring().antMatchers(ANY_URL);
        }
    }
}
