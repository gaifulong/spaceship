package com.spaceship.ranger.socket;

import javax.websocket.Session;

/**
 * WebSocket 客户端连接信息
 */
public class WebSocketClientData {
    /**
     * 与客户端连接的会话，需要通过通过它给客户端发送数据
     */
    private Session session;
    /**
     * 连接的url
     */
    private String url;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
