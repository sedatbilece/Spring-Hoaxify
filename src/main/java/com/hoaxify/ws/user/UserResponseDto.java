package com.hoaxify.ws.user;

import lombok.Data;

@Data
public class UserResponseDto {

    private long id;

    private String username;

    private String displayName;

    private String image;

    public UserResponseDto(long id, String username, String displayName, String image) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.image = image;
    }
}
