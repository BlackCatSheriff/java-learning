package edu.ouc.it.domain;

import java.util.Date;

/**
 * User
 *
 * @author skyUnv
 * created on 2018/11/3 15:12
 */
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
    private Date bornDate;

    public User(String name, String email, int age, Date bornDate) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.bornDate = bornDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public User() {
    }
}
