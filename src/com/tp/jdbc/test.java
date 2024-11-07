package com.tp.jdbc;

public class test {
    public static void main(String[] args) {
        BasicUser user = new BasicUser("lua","ABC12345");
        VerifyUserDecorator verifyUserDecorator = new VerifyUserDecorator(user);
        verifyUserDecorator.register();
    }
}
