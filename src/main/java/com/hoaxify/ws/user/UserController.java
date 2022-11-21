package com.hoaxify.ws.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/api/v1/users")
    public String sayHi(){
        return "hello from user controller";
    }

    @PostMapping("/api/v1/users")
    public void createUser(@RequestBody User user){
            log.info(user.toString());
    }
}
