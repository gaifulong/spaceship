package com.spaceship.ranger.socket;

import com.spaceship.ranger.bean.MessageBean;

import java.io.IOException;
import java.util.List;

public class SocketMessageUtil {

    /**
     * 向客户端发送消息
     * @param userid
     * 目标客户端用户id
     * @param message
     * 消息内容
     */
    public static void sendMessage(
            String userid,
            String message
    ) {
        List<WebSocketClientData> webSocketClientList = SocketClientManager.get(userid);
        if (webSocketClientList != null) {
            webSocketClientList.forEach(t -> {
                try {
                    t.getSession().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            });
        }
    }

    /**
     * 给所有客户端发送消息
     * @param message
     * 消息
     */
    public static void sendMessageToAll (
            final MessageBean message
    ) {
        SocketClientManager.allValues().forEach(t ->
                t.forEach(i -> {
                    try {
                        i.getSession().getBasicRemote().sendObject(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
        );
    }

}
