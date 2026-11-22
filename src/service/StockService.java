
package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import model.Produit;
import model.MouvementStock;
import model.TypeMouvement;

import repository.ProduitRepository;
import repository.MouvementRepository;

public class StockService {

    private final ProduitRepository produitRepository;
    private final MouvementRepository mouvementRepository;

    // Constructeur
    public StockService(ProduitRepository produitRepository,
                        MouvementRepository mouvementRepository) {
        this.produitRepository = produitRepository;
        this.mouvementRepository = mouvementRepository;
    }

    // Enregistrer une entree de stock
    public boolean enregistrerEntree(int idMouvement, int idProduit,
                                     int quantite, String commentaire) {

        return enregistrerMouvement(idMouvement, idProduit,
                quantite, TypeMouvement.ENTREE, commentaire);
    }

    // Enregistrer une sortie de stock
    public boolean enregistrerSortie(int idMouvement, int idProduit,
                                     int quantite, String commentaire) {

        return enregistrerMouvement(idMouvement, idProduit,
                quantite, TypeMouvement.SORTIE, commentaire);
    }

    // Methode commune pour les entrees et les sorties
    private boolean enregistrerMouvement(int idMouvement, int idProduit,
                                         int quantite, TypeMouvement type,
                                         String commentaire) {

        // Verifier la quantite
        if (quantite <= 0) {
            return false;
        }

        // Verifier si le mouvement existe deja
        if (mouvementRepository.rechercherParId(idMouvement).isPresent()) {
            return false;
        }

        // Rechercher le produit
        Optional<Produit> resultat =
                produitRepository.rechercherParId(idProduit);

        if (resultat.isEmpty()) {
            return false;
        }

        Produit produit = resultat.get();

        // Verifier si le produit est archive
        if (produit.isArchive()) {
            return false;
        }

        int stockActuel = produit.getQuantiteStock();

        // Calculer la nouvelle quantite
        int nouveauStock;

        if (type == TypeMouvement.ENTREE) {

            // Eviter le depassement de la capacite int
            if (quantite > Integer.MAX_VALUE - stockActuel) {
                return false;
            }

            nouveauStock = stockActuel + quantite;

        } else {

            // Verifier si le stock est suffisant
            if (quantite > stockActuel) {
                return false;
            }

            nouveauStock = stockActuel - quantite;
        }

        // Creer le mouvement
        MouvementStock mouvement = new MouvementStock(
                idMouvement,
                idProduit,
                type,
                quantite,
                LocalDateTime.now(),
                commentaire
        );

        // Mettre a jour le stock et enregistrer le mouvement
        produit.setQuantiteStock(nouveauStock);
        mouvementRepository.ajouterMouvement(mouvement);

        return true;
    }

    // Consulter tous les mouvements
    public List<MouvementStock> listerMouvements() {
        return mouvementRepository.listerMouvements();
    }

    // Rechercher les mouvements d'un produit
    public List<MouvementStock> rechercherMouvementsParProduit(int idProduit) {
        return mouvementRepository.rechercherParProduit(idProduit);
    }

    // Rechercher les mouvements par type
    public List<MouvementStock> rechercherMouvementsParType(TypeMouvement type) {
        return mouvementRepository.rechercherParType(type);
    }

    // Rechercher un mouvement par son identifiant
    public Optional<MouvementStock> rechercherMouvementParId(int id) {
        return mouvementRepository.rechercherParId(id);
    }

    // Compter le nombre total de mouvements
    public long compterMouvements() {
        return mouvementRepository.compterMouvements();
    }
}
