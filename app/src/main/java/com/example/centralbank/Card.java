package com.example.centralbank;

public class Card {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String accountNumber;
    private String dateExp;
    private String user;
    private boolean blockage;
    private boolean sansContact;

    public Card() {
        // Default constructor required for Firebase Realtime Database
    }

    public Card(String email, String accountNumber, String dateExp, String user, boolean blockage, boolean sansContact) {
        this.email = email;
        this.accountNumber = accountNumber;
        this.dateExp = dateExp;
        this.user = user;
        this.blockage = blockage;
        this.sansContact = sansContact;
    }


    // Getters and setters for the card attributes

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDateExp() {
        return dateExp;
    }

    public void setDateExp(String dateExp) {
        this.dateExp = dateExp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isBlockage() {
        return blockage;
    }

    public void setBlockage(boolean blockage) {
        this.blockage = blockage;
    }

    public boolean isSansContact() {
        return sansContact;
    }

    public void setSansContact(boolean sansContact) {
        this.sansContact = sansContact;
    }
}
