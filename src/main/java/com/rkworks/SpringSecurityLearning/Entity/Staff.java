package com.rkworks.SpringSecurityLearning.Entity;


import jakarta.persistence.*;

@Entity(name="staff")
public class Staff {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="staffid")
    int id;
    @Column(name="username")
    String username;
    @Column(name="password")
    String password;

    public Staff() {
    }

    public Staff(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
