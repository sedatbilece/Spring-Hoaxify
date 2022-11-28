package com.hoaxify.ws.auth;


import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class AuthController {


    private UserRepository userRepository;
     PasswordEncoder passwordEncoder;
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/api/v1/auth")
    ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization" ,required = false) String authorization ){

        if(authorization==null){
            ApiError error = new ApiError(401,"Unauthorized request","/api/v1/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        String base64encode= authorization.split("Basic ")[1];
        String decoded=new String(Base64.getDecoder().decode(base64encode));
        String[] parts =decoded.split(":");
        String username=parts[0]; String password =parts[1];

        User inDB= userRepository.findByUsername(username);
        if(inDB==null){
            ApiError error = new ApiError(401,"Unauthorized request","/api/v1/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashPassword = inDB.getPassword();
        if(!passwordEncoder.matches(password,hashPassword)){
            ApiError error = new ApiError(401,"Unauthorized request","/api/v1/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

            return ResponseEntity.ok(authorization);
    }
}
