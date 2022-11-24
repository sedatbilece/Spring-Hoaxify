package com.hoaxify.ws.user;


import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
public class UserController {



    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public String sayHi(){
        return "hello from user controller";
    }

    @PostMapping("/api/v1/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody User user){
        if (user.getUsername()==null || user.getUsername().isEmpty()){
            ApiError error = new ApiError(400,"validation error","/api/v1/users");
            Map<String,String> validationerror = new HashMap<>();
            validationerror.put("username","username cannot be empty");
            error.setValidationErrors(validationerror);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        userService.save(user);
        return  ResponseEntity.ok(new GenericResponse("user created"));
    }
}
