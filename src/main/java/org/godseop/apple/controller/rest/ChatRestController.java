package org.godseop.apple.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.godseop.apple.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRestController {

    @MessageMapping("/apple")
    @SendTo("/chat/roomId")
    public String broadcast(ChatMessage inMessage) {
        // 단순 메세지만 브로드캐스트
        return inMessage.getContent();
    }


}
