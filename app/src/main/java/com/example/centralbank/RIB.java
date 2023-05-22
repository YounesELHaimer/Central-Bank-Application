package com.example.centralbank;

public class RIB {
    private String email;
    private String codeBanque;
    private String codeVille;
    private String prefixe;
    private String numeroDeCompte;
    private String chiffresCles;

    private String user;

    public RIB(String email, String codeBanque, String codeVille, String prefixe, String numeroDeCompte, String chiffresCles, String user) {
        this.email = email;
        this.codeBanque = codeBanque;
        this.codeVille = codeVille;
        this.prefixe = prefixe;
        this.numeroDeCompte = numeroDeCompte;
        this.chiffresCles = chiffresCles;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public RIB() {
        // Default constructor required for Firebase database operations
    }

    public RIB(String email, String codeBanque, String codeVille, String prefixe, String numeroDeCompte, String chiffresCles) {
        this.email = email;
        this.codeBanque = codeBanque;
        this.codeVille = codeVille;
        this.prefixe = prefixe;
        this.numeroDeCompte = numeroDeCompte;
        this.chiffresCles = chiffresCles;
    }

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public void setNumeroDeCompte(String numeroDeCompte) {
        this.numeroDeCompte = numeroDeCompte;
    }

    public String getChiffresCles() {
        return chiffresCles;
    }

    public void setChiffresCles(String chiffresCles) {
        this.chiffresCles = chiffresCles;
    }
}

