package com.spaceship.ranger.socket;

import com.alibaba.fastjson.JSON;
import com.spaceship.ranger.bean.MessageBean;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<MessageBean>{
    @Override
    public MessageBean decode(String s) throws DecodeException {
        return JSON.parseObject(s, MessageBean.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
