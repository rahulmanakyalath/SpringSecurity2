package com.rkworks.SpringSecurityLearning.Services;


import com.rkworks.SpringSecurityLearning.Entity.Staff;
import com.rkworks.SpringSecurityLearning.Entity.StaffPrinciple;
import com.rkworks.SpringSecurityLearning.Repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements UserDetailsService {

    @Autowired
    StaffRepo staffRepo;


  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);// Creating a password encoder

    public Staff savestaff(Staff staff){
    Staff stf =null;
        if(staffRepo.findByUsername(staff.getUsername())==null){
        System.out.println("reached service");
        staff.setPassword(encoder.encode(staff.getPassword()));//ENCODING AND SAVING PASSWORD IN DB.
        staffRepo.save(staff);
        stf=staff;
        }
        return stf;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//UserDetails is an interface so we need to create a class to implement the methods.
        Staff staff = staffRepo.findByUsername(username);

        if(staff==null){
            System.out.println("Staff not found");
            throw new UsernameNotFoundException("Staff not found");
        }
        return new StaffPrinciple(staff);

    }
}
