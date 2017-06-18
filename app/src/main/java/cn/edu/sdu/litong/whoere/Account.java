package cn.edu.sdu.litong.whoere;

import java.io.Serializable;

/**
 * Created by hasee on 2017/5/2.
 */

public class Account implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    public Account(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String name){
        this.username=name;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
