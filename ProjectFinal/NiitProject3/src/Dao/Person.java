package Dao;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private String gender;
    private String uNAME;
    private String uPass;

    public Person() {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
        this.uNAME = uNAME;
        this.uPass = uPass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getuNAME() {
        return uNAME;
    }

    public void setuNAME(String uNAME) {
        this.uNAME = uNAME;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }
    @Override
    public String toString() {
        return "Employee [username=" + uNAME + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + email
                + ", password=" + uPass + ", role=" + role + ", gender=" + gender + ", mobileNumber=" + phone + "]";
    }
}
