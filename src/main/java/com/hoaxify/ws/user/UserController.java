package com.hoaxify.ws.user;


import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {



    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/v1/users")
    public String sayHi(){
        return "hello from user controller";
    }

    @PostMapping("/api/v1/users")
    public void createUser(@RequestBody User user){
            userRepository.save(user);
    }
}
