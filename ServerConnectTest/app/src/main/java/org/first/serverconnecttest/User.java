package org.first.serverconnecttest;

import java.nio.charset.Charset;

public class User {

    String email;
    String name;
    String pw;
    public User(String email, String name, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
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

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
