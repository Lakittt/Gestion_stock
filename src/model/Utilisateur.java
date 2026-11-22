
package model;

import java.util.Objects;

public class Utilisateur {

    
    private int id;
    private String identifiant;
    private String motDePasse;

    
    public Utilisateur(int id, String identifiant, String motDePasse) {
        this.id = id;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    
    public int getId() {
        return id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    
    @Override
    public String toString() {
        return "Utilisateur{" +"id=" + id + ", identifiant='" + identifiant + '\'' +'}';
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Utilisateur autre = (Utilisateur) obj;

        return id == autre.id;
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
