package com.hoaxify.ws;

import com.hoaxify.ws.hoax.Hoax;
import com.hoaxify.ws.hoax.HoaxService;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUser(UserService userService, HoaxService hoaxService){
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {
				for(int i=1;i<=100;i++){
					User user =new User();
					user.setUsername("user"+i);
					user.setPassword("Password"+i);
					user.setDisplayName("dispName"+i);
					userService.save(user);
				}
				for(int i=1;i<50;i++){
					Hoax hoax= new Hoax();
					hoax.setContent("hoax content - "+i);
					hoaxService.save(hoax);
				}
			}
		};
	}

}
