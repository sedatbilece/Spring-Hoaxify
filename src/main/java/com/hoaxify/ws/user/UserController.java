package com.hoaxify.ws.user;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
public class UserController {



    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    Page<User> getAllUsers(@RequestParam int currentPage,@RequestParam(required = false,defaultValue = "10") int pageSize){
        return userService.getAllUsers(currentPage,pageSize);
    }

    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
       return userService.save(user);
    }
}
