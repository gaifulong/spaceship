package com.spaceship.ranger.socket;

import com.alibaba.fastjson.JSON;
import com.spaceship.ranger.bean.MessageBean;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<MessageBean> {
    @Override
    public String encode(MessageBean message) throws EncodeException {
        return JSON.toJSON(message).toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
