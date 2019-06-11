package org.godseop.apple.config;

import org.godseop.apple.security.AppleLogoutSuccessHandler;
import org.godseop.apple.security.MemberAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberAuthenticationProvider memberAuthenticationProvider;

    public SecurityConfig(MemberAuthenticationProvider memberAuthenticationProvider) {
        this.memberAuthenticationProvider = memberAuthenticationProvider;
    }

    // TODO Redis에 세션을 저장
    // TODO Remember me를 이용한 자동로그인 구현

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //fucking security
        //http.authorizeRequests().anyRequest().permitAll();
        //http.csrf().disable();

        http.authorizeRequests()
                //.antMatchers("/**").hasIpAddress("1.1.1.1") // IP주소 한정 접근허용
                //.antMatchers("/**").anonymous() // 인증되지않은 사용자만 접근
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers( "/", "/login", "/join", "/error/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasAnyRole("ADMIN", "AUTHOR")
                .antMatchers("/**").hasRole("BASIC")
                .anyRequest().authenticated()

                .and()

            .cors()
                //.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()) // 디폴트 CORS 설정

                .and()

            .requiresChannel() // 선택한 URL에 HTTPS를 적용
                .antMatchers("/**").requiresInsecure()
                //.antMatchers("/**").requiresSecure();

                .and()

            .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/auth")
                //.successHandler(loginSuccessHandler())
                //.failureHandler(loginFailureHandler())
                .defaultSuccessUrl("/home")
                .usernameParameter("uid")
                .passwordParameter("password")

                .and()

            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")


                .and()

            .httpBasic();

        http.sessionManagement()
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false) // 기존사용자의 세션 종료(true: 이미로그인이면 로그인X)
            .expiredUrl("/error/expired");

        http.exceptionHandling().accessDeniedPage("/error/403");

        http.authenticationProvider(memberAuthenticationProvider);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/static/**");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new AppleLogoutSuccessHandler();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
