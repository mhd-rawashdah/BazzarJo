package edu.zu.demo.graduation.bazzar.Model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String gender;
    private String date;
    private String DateOfBirth;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

   /* public Buyer(String firstName, String lastName, String phoneNumber, String password, String gender, String date) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        this.gender = gender;

        this.date = date;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        if (check_Name(firstName)) {
            this.firstName = firstName;
            return true;
        }
        return false;
    }

    public String getlastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if (check_Name(lastName)) {
            this.lastName = lastName;
            return true;
        }
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private boolean check_Name(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    }


