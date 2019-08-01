package org.godseop.apple.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AppleLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
}
