package com.spaceship.ranger.socket;

import com.alibaba.fastjson.JSON;
import com.spaceship.ranger.bean.MessageBean;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/web_socket/message/{userid}")
public class MessageWebSocket {

    private static final ConcurrentHashMap<String, List<WebSocketClientData>> mWebSocketClientMap = new ConcurrentHashMap<>();

    /**
     * 建立连接时触发，绑定参数
     * @param session
     * 与客户端连接的会话，需要通过通过它给客户端发送数据
     * @param userid
     * 用户id
     */
    @OnOpen
    public void onOpen (
            Session session,
            @PathParam("userid") String userid
    ) {
        WebSocketClientData client = new WebSocketClientData();
        client.setSession(session);
        client.setUrl(session.getRequestURI().toString());

        List<WebSocketClientData> webSocketClientList = mWebSocketClientMap.get(userid);
        if (webSocketClientList == null) {
            webSocketClientList = new ArrayList<>();
        }
        webSocketClientList.add(client);
        mWebSocketClientMap.put(userid, webSocketClientList);

        MessageBean message = new MessageBean();
        message.setContent(String.format("%s%s%s\n", "用户：", userid ,"已进入"));
        sendMessageToAll(message);
    }

    /**
     * 接受到客户端消息时触发
     */
    @OnMessage
    public void onMessage (
            String params,
            Session session
    ) {
        MessageBean message = JSON.parseObject(params, MessageBean.class);
        sendMessageToAll(message);
    }

    /**
     * 连接关闭时触发
     * 注意不要向客户端发送消息了
     * @param userid
     * 用户id
     */
    @OnClose
    public void onClose (
            @PathParam("userid") String userid
    ) {
        mWebSocketClientMap.remove(userid);

        MessageBean message = new MessageBean();
        message.setContent(String.format("%s%s%s\n", "用户：", userid ,"已退出"));
        sendMessageToAll(message);
    }

    /**
     * 通信发生错误时触发
     * @param session
     * 发生错误的会话
     * @param error
     * 错误信息
     */
    @OnError
    public void onError (
            Session session,
            Throwable error
    ) {
        System.out.println("websocket 连接 发生错误");
        error.printStackTrace();
    }

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
        List<WebSocketClientData> webSocketClientList = mWebSocketClientMap.get(userid);
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
    public static void sendMessageToAll (MessageBean message) {
        String json = JSON.toJSON(message).toString();
        mWebSocketClientMap.values().forEach(t ->
                t.forEach(i -> {
                    try {
                        i.getSession().getBasicRemote().sendText(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
        );
    }

}
