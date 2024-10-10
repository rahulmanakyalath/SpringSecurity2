package com.rkworks.SpringSecurityLearning.SecurityConfig;


import com.fasterxml.jackson.databind.annotation.NoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

// BY USING THIS BEAN WE CAN CUSTOMISE THE DEFAULT BASIC AUTH METHOD AS WE NEED.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.csrf(customizer ->customizer.disable());// TO DISABLE THE NEED FOR PASSING X-CSRF-TOKEN FOR PUT,POST AND DELETE METHODS
//        http.authorizeHttpRequests(request->request.anyRequest().authenticated());//TO AUTHENTICATE ALL REQUEST
//        http.httpBasic(Customizer.withDefaults());//TO ADD AUTHENTICATION IN POSTMAN AND ALSO TO REMOVE NEED FOR FROM IN WEBPAGE
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//TO MAKE HTTP STATELESS
// return http.build();
        //written using lamda expression simplified form.
      return http
       .csrf(customizer ->customizer.disable())
              .authorizeHttpRequests(request->request.anyRequest().authenticated())
              .httpBasic(Customizer.withDefaults())
              .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();

    }

    //USING AN INBUILD INTERFACE WHICH CAN TAKE CUSTOMISED USER DETAILS FROM DB OTHER THAN HARD CODING IN APP PROPERTY FILES.
//    @Bean
//    public UserDetailsService userDetailsService(){ //UserDetailsService IS AN INTERFACE WE NEED TO FIND A CLASS WHICH IMPLEMENTS IT TO GET RETURN TYPE
//   //[public class InMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService]
//        UserDetails user1 = User.withDefaultPasswordEncoder()  //THE METHID IS DEPRECIATED , SHOULD CREATE A NEW METHOD TO ENCRPYT PASSWORD.
//                .username("Ram")
//                .password("123")              //HARD CODING VALUES JUST OT SHOW EXAMPLE .
//                .roles("USER")                // SHOULD TAKE THIS VALUES FROM DB.
//                .build();
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("Sam")
//                .password("1234")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);//
//    }
     // CUSTOMISE THE AUTHENTICATION PROVIDER SO WE CAN ADD DATABASE TO AUTHENTICATE THE USERS.

    @Bean
    public AuthenticationProvider authenticationProvider(){//Authentication provider is an interface.
        DaoAuthenticationProvider provider  = new DaoAuthenticationProvider();//DaoAp is a class which implement the AUTH interface
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));//TO CHECK WHETHER THE HASHED PASSWORD IS CORRECT .
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    
}
