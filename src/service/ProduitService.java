
package service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import model.Produit;
import repository.ProduitRepository;
import repository.CategorieRepository;
import repository.FournisseurRepository;

public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;

    public ProduitService(
            ProduitRepository produitRepository,
            CategorieRepository categorieRepository,
            FournisseurRepository fournisseurRepository) {

        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    public boolean ajouterProduit(Produit produit) {

        if (produit == null) return false;

        if (produit.getPrix() <= 0
                || produit.getQuantiteStock() < 0
                || produit.getSeuilAlerte() < 0) {
            return false;
        }

        if (produitRepository.rechercherParId(produit.getId()).isPresent()) {
            return false;
        }

        if (categorieRepository.rechercherParId(
                produit.getIdCategorie()).isEmpty()) {
            return false;
        }

        if (fournisseurRepository.rechercherParId(
                produit.getIdFournisseur()).isEmpty()) {
            return false;
        }

        produitRepository.ajouterProduit(produit);
        return true;
    }

    public boolean modifierProduit(Produit produit) {

        if (produit == null || produit.getPrix() <= 0
                || produit.getQuantiteStock() < 0
                || produit.getSeuilAlerte() < 0) {
            return false;
        }

        Optional<Produit> resultat =
                produitRepository.rechercherParId(produit.getId());

        if (resultat.isEmpty() || resultat.get().isArchive()) {
            return false;
        }

        if (categorieRepository.rechercherParId(
                produit.getIdCategorie()).isEmpty()) {
            return false;
        }

        if (fournisseurRepository.rechercherParId(
                produit.getIdFournisseur()).isEmpty()) {
            return false;
        }

        return produitRepository.modifierProduit(produit);
    }

    public boolean archiverProduit(int id) {
        return produitRepository.archiverProduit(id);
    }

    public Optional<Produit> rechercherParId(int id) {
        return produitRepository.rechercherParId(id);
    }

    public List<Produit> listerProduitsActifs() {
        return produitRepository.listerProduitsActifs();
    }

    // Filtrage dynamique avec Predicate et Lambda
    public List<Produit> filtrerProduits(Predicate<Produit> critere) {

        return produitRepository.listerProduitsActifs()
                .stream()
                .filter(critere)
                .toList();
    }

    // Exemple : rechercher les produits chers
    public List<Produit> rechercherProduitsChers(double prixMinimum) {

        return filtrerProduits(p -> p.getPrix() > prixMinimum);
    }

    // Exemple : rechercher les produits en alerte
    public List<Produit> rechercherProduitsEnAlerte() {

        return filtrerProduits(p -> p.estEnAlerte());
    }
}
