package org.godseop.apple.model;

import lombok.Data;

@Data
public class ChatMessage {

    private String token;
    private String content;
    private String type;
    private String chatId;

}
