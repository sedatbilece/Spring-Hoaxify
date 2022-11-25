package com.hoaxify.ws.user;


import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public ResponseEntity<?> save(User user) {

        ApiError error = new ApiError(400,"validation error","/api/v1/users");
        Map<String,String> validationerror = new HashMap<>();
        if (user.getUsername()==null || user.getUsername().isEmpty()){
            validationerror.put("username","User Name cannot be empty");
        }
        else{
              User usname = userRepository.findByUsername(user.getUsername());
              if(usname!=null){
                  validationerror.put("username","User Name  must be unique");
              }
            if(user.getUsername().length()<4 || user.getUsername().length()>50){
                validationerror.put("username","User Name  must be 4-50 Character"); }
        }
        if (user.getDisplayName()==null || user.getDisplayName().isEmpty()){
            validationerror.put("displayName","Display Name cannot be empty");
        }
        else{
            if(user.getDisplayName().length()<4 || user.getDisplayName().length()>50){
                validationerror.put("displayName","Display Name  must be 4-50 Character"); }
        }
        if (user.getPassword()==null || user.getPassword().isEmpty()){
            validationerror.put("password","Password cannot be empty");
        }
        else{
            if(user.getPassword().length()<8 || user.getPassword().length()>20){
                validationerror.put("password","Password  must be 8-20 Character"); }
            if(! user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")){
                validationerror.put("password","Password must have big char,small char and digit ");
            }

        }
        if(validationerror.size()>0){
            error.setValidationErrors(validationerror);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return  ResponseEntity.ok(new GenericResponse("user created"));
    }
}
