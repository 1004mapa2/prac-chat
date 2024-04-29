package com.ex.socket1.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sessionList = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // 유저 닉네임 받아오기
        String name = "test11";
        log.info("payload: {}", payload);
        sessionList.forEach(s -> {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 입장 메서드
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = "test11";
        sessionList.add(session);
        sessionList.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(name + "입장"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        log.info("session: {}", session + "클라이언트 접속");
    }

    /**
     * 퇴장 메서드
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        String name = "test22";
        sessionList.forEach(s -> {
            try {
                s.sendMessage(new TextMessage(name + "퇴장"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        log.info("session: {}", session + "클라이언트 접속 해제");
    }
}
