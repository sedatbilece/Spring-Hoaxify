package com.hoaxify.ws.user;

import lombok.Data;

@Data
public class UserResponse {

    private long id;

    private String username;

    private String displayName;

    private String image;

    public UserResponse(long id, String username, String displayName, String image) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.image = image;
    }
}
