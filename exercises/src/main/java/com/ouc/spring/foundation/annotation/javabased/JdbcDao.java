package com.ouc.spring.foundation.annotation.javabased;

/**
 * JdbcDao
 *
 * @author skyUnv
 * created on 2018/5/11 14:28
 */
public class JdbcDao {
    private String url;
    private String username;
    private String password;

    public JdbcDao(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void cleanup() {
        System.out.println("url: " + url + " username: " + username + " password: " + password);
    }
}
