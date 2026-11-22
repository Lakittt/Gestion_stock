
package model;

import java.util.Objects;

public class Fournisseur {

    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;

    public Fournisseur(int id, String nom, String telephone,String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +"id=" + id +", nom='" + nom + '\'' + ", telephone='" + telephone + '\'' +
        		", email='" + email + '\'' +", adresse='" + adresse + '\'' +'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Fournisseur autre = (Fournisseur) obj;

        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
