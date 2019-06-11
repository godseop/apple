package org.godseop.apple.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AppleLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${server.servlet.context-path}")
    private String servletContextPath;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userName = authentication.getName();
        String refererUrl = request.getHeader("Referer");

        log.error("Logout user: {} from: {}", userName, refererUrl);

        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(servletContextPath);
    }
}
