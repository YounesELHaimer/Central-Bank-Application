package com.example.centralbank;
import java.util.Date;

public class Virement {
    private String typeDeTransaction;
    private float montant;
    private Date date;

    public Virement() {
        // Default constructor required for Firebase
    }

    public Virement(String typeDeTransaction, float montant, Date date) {
        this.typeDeTransaction = typeDeTransaction;
        this.montant = montant;
        this.date = date;
    }

    // Getters and Setters
    public String getTypeDeTransaction() {
        return typeDeTransaction;
    }

    public void setTypeDeTransaction(String typeDeTransaction) {
        this.typeDeTransaction = typeDeTransaction;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
