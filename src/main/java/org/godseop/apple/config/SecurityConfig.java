package org.godseop.apple.config;

import org.godseop.apple.security.CustomLogoutSuccessHandler;
import org.godseop.apple.security.MemberAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberAuthenticationProvider memberAuthenticationProvider;

    public SecurityConfig(MemberAuthenticationProvider memberAuthenticationProvider) {
        this.memberAuthenticationProvider = memberAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //fucking security
        //http.authorizeRequests().anyRequest().permitAll();

        http.authorizeRequests()
                .antMatchers( "/", "/login", "/join", "/member/register", "/error/**").permitAll()
                .antMatchers("/**").hasRole("BASIC")
                .antMatchers("/member/**").access("hasRole('ADMIN') or hasRole('DBA')")  //.hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .usernameParameter("uid")
                .passwordParameter("password");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/static/**");
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(memberAuthenticationProvider);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}
