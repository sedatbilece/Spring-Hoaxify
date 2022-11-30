package com.hoaxify.ws.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.shared.Views;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @JsonView(Views.Base.class)
    private String username;

    @NotNull
    @JsonView(Views.Base.class)
    private String displayName;

    private String password;

    @JsonView(Views.Base.class)
    private String image;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
