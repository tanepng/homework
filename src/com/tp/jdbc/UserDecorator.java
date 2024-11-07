package com.tp.jdbc;

public class UserDecorator implements IUser {
    private IUser user;
    public UserDecorator(IUser user){
        this.user = user;
    }
    @Override
    public void register() {
        user.register();
    }
    @Override
    public void login() {
        user.login();
    }
    public IUser getUser(){
        return this.user;
    }
}
