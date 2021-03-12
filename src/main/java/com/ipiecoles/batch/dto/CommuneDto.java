package com.ipiecoles.batch.dto;

public class CommuneDto {
    private String insee;
    private String nom;
    private String cp;
    private String ligne5;
    private String libelle;
    private String gps;

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLigne5() {
        return ligne5;
    }

    public void setLigne5(String ligne5) {
        this.ligne5 = ligne5;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "CommuneDto{" +
                "insee='" + insee + '\'' +
                ", nom='" + nom + '\'' +
                ", cp='" + cp + '\'' +
                ", ligne5='" + ligne5 + '\'' +
                ", libelle='" + libelle + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }
}
