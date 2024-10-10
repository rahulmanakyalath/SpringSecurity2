package com.rkworks.SpringSecurityLearning.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Security")
public class SecurityController {


    @GetMapping("/test")
    public String testSecurity(HttpServletRequest request){//[since every controller is a servlet we can get the HttpServelet request ]

        return "Entered the secure get method" + request.getSession().getId();

    }
}
