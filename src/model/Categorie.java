
package model;

import java.util.Objects;

public class Categorie {

    private int id;
    private String nom;
    private String description;

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" +"id=" + id +", nom='" + nom + '\'' + ", description='" + description + '\'' +'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Categorie autre = (Categorie) obj;

        return id == autre.id;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(id);
    }
}
