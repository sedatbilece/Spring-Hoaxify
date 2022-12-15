package com.hoaxify.ws.user;


import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {



    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    PageImpl<UserResponse> getAllUsers(@RequestParam(required = false,defaultValue = "0") int currentPage,
                                       @RequestParam(required = false,defaultValue = "10") int pageSize){
        return userService.getAllUsers(currentPage,pageSize);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
       return userService.save(user);
    }

    @GetMapping("/users/{username}")
    ResponseEntity<?> getUser(@PathVariable String username){
       return  userService.getUser(username);
    }
}
