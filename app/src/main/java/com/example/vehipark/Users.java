package com.example.vehipark;

public class Users {

    private String name;
    private String address;
    private String contact;
    private String email;


    public Users(){

    }

    public Users(String name, String address, String contact, String email) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getuId() {
        return name;
    }

    public void setuId(String name) {
        this.name = name;
    }

}
