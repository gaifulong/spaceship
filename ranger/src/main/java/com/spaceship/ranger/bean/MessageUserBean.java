package com.spaceship.ranger.bean;

import java.io.Serializable;

public class MessageUserBean implements Serializable {
    private String userId;
    private String username;

    public MessageUserBean () {}
    public MessageUserBean (String userId,String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
