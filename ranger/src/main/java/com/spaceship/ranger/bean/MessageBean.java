package com.spaceship.ranger.bean;

import java.io.Serializable;
import java.util.Date;

public class MessageBean implements Serializable {

    private MessageUserBean from = new MessageUserBean("", "系统");
    private MessageUserBean to;
    private String content;
    private int state;
    private int type;
    private Date createTime = new Date();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MessageUserBean getFrom() {
        return from;
    }

    public void setFrom(MessageUserBean from) {
        this.from = from;
    }

    public MessageUserBean getTo() {
        return to;
    }

    public void setTo(MessageUserBean to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
