package com.hoaxify.ws.user;


import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    public GenericResponse createUser(@RequestBody User user){
        userService.save(user);
        return  new GenericResponse("user created");
    }
}
