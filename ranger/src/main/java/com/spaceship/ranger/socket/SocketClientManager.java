package com.spaceship.ranger.socket;

import org.springframework.util.StringUtils;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketClientManager {

    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_NAME = "username";

    public static final ConcurrentHashMap<String, List<WebSocketClientData>>
            mWebSocketClientMap = new ConcurrentHashMap<>();

    public static List<WebSocketClientData> get (
            String userid
    ) {
        return mWebSocketClientMap.get(userid);
    }

    public static void put (
            String userid,
            WebSocketClientData client
    ) {
        List<WebSocketClientData> webSocketClientList = mWebSocketClientMap.get(userid);
        if (webSocketClientList == null) {
            webSocketClientList = new ArrayList<>();
        }
        webSocketClientList.add(client);
        mWebSocketClientMap.put(userid, webSocketClientList);
    }

    public static void remove (
            String userid
    ) {
        mWebSocketClientMap.remove(userid);
    }

    public static Collection<List<WebSocketClientData>> allValues () {
        return mWebSocketClientMap.values();
    }

    /**
     * 获取用户名
     *
     * @param session {@link Session}
     * @return 用户名
     */
    public static String getUsername (
            final Session session
    ) {
        String username = null;
        Map<String, List<String>> map = session.getRequestParameterMap();
        if (map != null) {
            List<String> list = map.get(KEY_USER_NAME);
            if (list != null && list.size() > 0) {
                username = list.get(0);
            }
        }
        if (StringUtils.isEmpty(username)) {
            username = session.getPathParameters().get(KEY_USER_ID);
        }
        return username;
    }

}
