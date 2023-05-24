package com.example.centralbank;
import java.util.Date;

public class Virement {
    private String typeDeTransaction;
    private float montant;
    private Date date;

    private String name;



    public Virement(String typeDeTransaction, float montant, Date date, String name, String numeroDeCompte) {
        this.typeDeTransaction = typeDeTransaction;
        this.montant = montant;
        this.date = date;
        this.name = name;
        this.numeroDeCompte = numeroDeCompte;
    }

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public void setNumeroDeCompte(String numeroDeCompte) {
        this.numeroDeCompte = numeroDeCompte;
    }

    private String numeroDeCompte;

    public String getNameBenef() {
        return nameBenef;
    }

    public void setNameBenef(String nameBenef) {
        this.nameBenef = nameBenef;
    }

    private String numeroDeCompteBenef;

    private String nameBenef;

    public Virement(String typeDeTransaction, float montant, Date date, String name, String numeroDeCompte, String numeroDeCompteBenef, String nameBenef) {
        this.typeDeTransaction = typeDeTransaction;
        this.montant = montant;
        this.date = date;
        this.name = name;
        this.numeroDeCompte = numeroDeCompte;
        this.numeroDeCompteBenef = numeroDeCompteBenef;
        this.nameBenef = nameBenef;
    }

    public String getName() {
        return name;
    }

    public String getNumeroDeCompteBenef() {
        return numeroDeCompteBenef;
    }

    public void setNumeroDeCompteBenef(String numeroDeCompteBenef) {
        this.numeroDeCompteBenef = numeroDeCompteBenef;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Virement(String typeDeTransaction, float montant, Date date, String name, String numeroDeCompte, String numeroDeCompteBenef) {
        this.typeDeTransaction = typeDeTransaction;
        this.montant = montant;
        this.date = date;
        this.name = name;
        this.numeroDeCompte = numeroDeCompte;
        this.numeroDeCompteBenef = numeroDeCompteBenef;
    }

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
