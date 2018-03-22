package vn.doithe66.doithe66.model;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class RegisterModel {
    String username;
    String phoneNumber;
    String email;
    String pass;

    public RegisterModel() {
    }

    public RegisterModel(String username, String phoneNumber, String email, String pass) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return "RegisterModel{" +
                "username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
