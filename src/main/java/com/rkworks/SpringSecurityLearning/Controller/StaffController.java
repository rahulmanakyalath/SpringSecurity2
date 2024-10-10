package com.rkworks.SpringSecurityLearning.Controller;


import com.rkworks.SpringSecurityLearning.Entity.Staff;
import com.rkworks.SpringSecurityLearning.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @PostMapping("/details")
    public String saveStaffDetails(@RequestBody Staff staff){

        if(staffService.savestaff(staff)!=null){
            System.out.println("saved new staff");
            return "Saved new staff";
        }
       return "Staff already exist";
    }
}
