
package model;

import java.util.Objects;

public class Produit {

    private int id;
    private String nom;
    private String description;
    private double prix;
    private int quantiteStock;
    private int seuilAlerte;
    private int idCategorie;
    private int idFournisseur;
    private boolean archive;

    
    public Produit(int id, String nom, String description, double prix,
                   int quantiteStock, int seuilAlerte,
                   int idCategorie, int idFournisseur) {

        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.seuilAlerte = seuilAlerte;
        this.idCategorie = idCategorie;
        this.idFournisseur = idFournisseur;
        this.archive = false;
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

    public double getPrix() {
        return prix;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public int getSeuilAlerte() {
        return seuilAlerte;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public boolean isArchive() {
        return archive;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public void setSeuilAlerte(int seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

 // Vérifier si le produit doit déclencher une alerte
    public boolean estEnAlerte() {
        return quantiteStock <= seuilAlerte;
    }

    // Calculer la valeur du stock de ce produit
    public double calculerValeurStock() {
        return prix * quantiteStock;
    }

    // 5. toString()
    @Override
    public String toString() {
        return "Produit{" +"id=" + id +", nom='" + nom + '\'' +", description='" + description + '\'' +", prix=" + prix +
                ", quantiteStock=" + quantiteStock +", seuilAlerte=" + seuilAlerte + ", idCategorie=" + idCategorie +
                ", idFournisseur=" + idFournisseur +", archive=" + archive +'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Produit autre = (Produit) obj;

        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
