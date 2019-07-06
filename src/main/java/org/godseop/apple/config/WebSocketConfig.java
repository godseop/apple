package org.godseop.apple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

//    @Override
//    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//        messages
//                .nullDestMatcher().authenticated()
//                .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
//                .simpDestMatchers("/app/**").hasRole("USER")
//                .simpSubscribeDestMatchers("/user/**", "/topic/friends/*").hasRole("USER")
//                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
//                .anyMessage().denyAll();
//
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry.enableSimpleBroker("/chat");
        messageBrokerRegistry.setApplicationDestinationPrefixes("/apple");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/websockethandler")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
