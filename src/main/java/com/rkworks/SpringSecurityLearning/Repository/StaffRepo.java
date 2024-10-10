package com.rkworks.SpringSecurityLearning.Repository;


import com.rkworks.SpringSecurityLearning.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {

    Staff findByUsername(String username);

}
