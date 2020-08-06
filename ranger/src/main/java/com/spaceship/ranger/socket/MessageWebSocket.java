package com.spaceship.ranger.socket;

import com.spaceship.ranger.bean.MessageBean;
import com.spaceship.ranger.bean.MessageUserBean;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(
        value = "/web_socket/message/{userid}",
        encoders = MessageEncoder.class,
        decoders = MessageDecoder.class
)
public class MessageWebSocket {

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

        SocketClientManager.put(userid, client);

        MessageBean message = new MessageBean();
        String username = SocketClientManager.getUsername(session);
        message.setContent(String.format("%s%s%s\n", "用户", username ,"已进入"));
        SocketMessageUtil.sendMessageToAll(message);

    }

    /**
     * 接受到客户端消息时触发
     */
    @OnMessage
    public void onMessage (
            MessageBean message,
            @PathParam("userid") String userid,
            Session session
    ) {
        String username = SocketClientManager.getUsername(session);
        message.setFrom(new MessageUserBean(userid, username));
        SocketMessageUtil.sendMessageToAll(message);
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
        SocketClientManager.remove(userid);

        MessageBean message = new MessageBean();
        message.setContent(String.format("%s%s%s\n", "用户：", userid ,"已退出"));
        SocketMessageUtil.sendMessageToAll(message);
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



}
