package com.example.centralbank;


public class User {
    public String name;
    public String lastName;
    public String phone;
    public String operator;

    private String frontCIN;

    private String backCIN;

    private String signatureImageUrl;

    public User(String name, String lastName, String phone, String operator, String frontCIN, String backCIN, String signatureImageUrl, Agency agency, String address, String dateOfBirth, String familyStatus, String professionalStatus, String face, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.operator = operator;
        this.frontCIN = frontCIN;
        this.backCIN = backCIN;
        this.signatureImageUrl = signatureImageUrl;
        this.agency = agency;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.familyStatus = familyStatus;
        this.professionalStatus = professionalStatus;
        this.face = face;
        this.email = email;
        this.password = password;
    }

    public String getSignatureImageUrl() {
        return signatureImageUrl;
    }

    public void setSignatureImageUrl(String signatureImageUrl) {
        this.signatureImageUrl = signatureImageUrl;
    }

    public User(String name, String lastName, String phone, String operator, String frontCIN, String backCIN, Agency agency, String address, String dateOfBirth, String familyStatus, String professionalStatus, String face, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.operator = operator;
        this.frontCIN = frontCIN;
        this.backCIN = backCIN;
        this.agency = agency;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.familyStatus = familyStatus;
        this.professionalStatus = professionalStatus;
        this.face = face;
        this.email = email;
        this.password = password;
    }

    private Agency agency;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public User(String name, String lastName, String phone, String operator, String frontCIN, String backCIN, String address, String dateOfBirth, String familyStatus, String professionalStatus, String face, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.operator = operator;
        this.frontCIN = frontCIN;
        this.backCIN = backCIN;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.familyStatus = familyStatus;
        this.professionalStatus = professionalStatus;
        this.face = face;
        this.email = email;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getProfessionalStatus() {
        return professionalStatus;
    }

    public void setProfessionalStatus(String professionalStatus) {
        this.professionalStatus = professionalStatus;
    }

    private String address;
    private String dateOfBirth;
    private String familyStatus;
    private String professionalStatus;

    public User(String name, String lastName, String phone, String operator, String frontCIN, String backCIN, String face, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.operator = operator;
        this.frontCIN = frontCIN;
        this.backCIN = backCIN;
        this.face = face;
        this.email = email;
        this.password = password;
    }

    public String getFrontCIN() {
        return frontCIN;
    }

    public void setFrontCIN(String frontCIN) {
        this.frontCIN = frontCIN;
    }

    public String getBackCIN() {
        return backCIN;
    }

    public void setBackCIN(String backCIN) {
        this.backCIN = backCIN;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    private String face;



    public String email;

    private String password;

    public User(String name, String lastName, String phone, String operator, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.operator = operator;

        this.email = email;
        this.password = password;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String email, String signatureImageUrl) {
        this.email = email;
        this.signatureImageUrl = signatureImageUrl;
    }


}







