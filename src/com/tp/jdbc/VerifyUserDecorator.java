package com.tp.jdbc;

public class VerifyUserDecorator extends UserDecorator {
    private boolean flage;
    public VerifyUserDecorator(IUser user) {
        super(user);
    }

    @Override
    public void register() {
        String username = ((BasicUser)getUser()).getUsername();
        String password = ((BasicUser)getUser()).getPassword();
        if (username.matches("^[a-zA-Z]{2,10}$") && password.matches("^.{6,12}$")) {
            getUser().register();
            flage = true;
        } else {
            System.out.println("您的用户名或者密码不符合标准,请重新输入");
            flage = false;
        }
    }
    public boolean getFlage(){
        return this.flage;
    }
}
