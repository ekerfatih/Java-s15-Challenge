package org.workintech.challange.models;

public class Member {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Reader getReader() {
        return reader;
    }

    private Reader reader;

    public Member(String userName, String password, Reader reader) {
        this.userName = userName;
        this.password = password;
        this.reader = reader;
    }
}
