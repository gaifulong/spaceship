package com.spaceship.ranger.controller;

import com.spaceship.ranger.socket.MessageWebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "WebSocket-test")
@RestController("message")
public class MessageWebSocketController {

    @ApiOperation("通知test")
    @GetMapping
    public void test (@RequestParam String userId) {
        MessageWebSocket.sendMessage(userId, "您有新消息，请注意查收");
    }

}
