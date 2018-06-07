package com.structs2.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Dong on 2018/6/7.
 */
public class LoginAction extends ActionSupport {
    private String username;
    private String password;

    public String execute() throws Exception {
        if (username.equals("admin") && password.equals("123")) {
            return SUCCESS;
        } else {
            return ERROR;
        }
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

}
