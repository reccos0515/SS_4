package com.atristan.Entity;

public class Member {

    private int id;
    private String name;
    private String interest;

    public Member(int id, String name, String interest) {
        this.id = id;
        this.name = name;
        this.interest = interest;
    }

    public Member(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
