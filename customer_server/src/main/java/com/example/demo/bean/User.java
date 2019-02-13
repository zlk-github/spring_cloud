package com.example.demo.bean;

/**用户类
 * @author  zhoulk
 * @date 2019/1/14
 */
public class User {
    /**姓名*/
    private String name;
    /**密码*/
    private String  password;

    public User(){
        super();
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
