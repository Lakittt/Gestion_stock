
package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Produit;

public class ProduitRepository {

    
    private List<Produit> produits = new ArrayList<>();

    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    // 3. Rechercher un produit par son ID
    public Optional<Produit> rechercherParId(int id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // 4. Lister tous les produits
    public List<Produit> listerProduits() {
        return new ArrayList<>(produits);
    }

    // 5. Modifier un produit
    public boolean modifierProduit(Produit nouveauProduit) {

        Optional<Produit> resultat = rechercherParId(nouveauProduit.getId());

        if (resultat.isPresent()) {
            Produit ancienProduit = resultat.get();

            ancienProduit.setNom(nouveauProduit.getNom());
            ancienProduit.setDescription(nouveauProduit.getDescription());
            ancienProduit.setPrix(nouveauProduit.getPrix());
            ancienProduit.setQuantiteStock(nouveauProduit.getQuantiteStock());
            ancienProduit.setSeuilAlerte(nouveauProduit.getSeuilAlerte());
            ancienProduit.setIdCategorie(nouveauProduit.getIdCategorie());
            ancienProduit.setIdFournisseur(nouveauProduit.getIdFournisseur());

            return true;
        }

        return false;
    }

    // 6. Archiver un produit
    public boolean archiverProduit(int id) {

        Optional<Produit> resultat = rechercherParId(id);

        if (resultat.isPresent()) {
            resultat.get().setArchive(true);
            return true;
        }

        return false;
    }

    // 7. Rechercher les produits actifs
    public List<Produit> listerProduitsActifs() {
        return produits.stream() 
                .filter(p -> !p.isArchive())
                .toList();
    }

    // 8. Supprimer tous les produits (réinitialisation)
    public void vider() {
        produits.clear();
    }
}
