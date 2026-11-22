
package service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Produit;
import repository.ProduitRepository;

public class StatistiquesService {

    // 1. Repository pour accéder aux produits
    private final ProduitRepository produitRepository;

    // 2. Constructeur
    public StatistiquesService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // 3. Récupérer les produits actifs
    public List<Produit> listerProduitsActifs() {
        return produitRepository.listerProduitsActifs();
    }

    // 4. Lister les produits qui nécessitent une alerte
    public List<Produit> listerProduitsEnAlerte() {
        return listerProduitsActifs().stream()
                .filter(p -> p.getQuantiteStock() <= p.getSeuilAlerte())
                .toList();
    }

    // 5. Récupérer uniquement les noms des produits
    public List<String> listerNomsProduits() {
        return listerProduitsActifs().stream()
                .map(Produit::getNom)
                .toList();
    }

    // 6. Trier les produits par prix croissant
    public List<Produit> trierProduitsParPrix() {
        return listerProduitsActifs().stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix)) 
                .toList();
    }

    // 7. Récupérer les identifiants des catégories sans doublons
    public List<Integer> listerCategoriesUtilisees() {
        return listerProduitsActifs().stream()
                .map(Produit::getIdCategorie)
                .distinct()
                .toList();
    }

    // 8. Afficher les produits les plus chers
    public List<Produit> topProduitsLesPlusChers(int limite) {

        if (limite <= 0) {
            return List.of();
        }

        return listerProduitsActifs().stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix).reversed())
                .limit(limite)
                .toList();
    }

    // 9. Lister les produits dont le prix est inférieur à un prix maximum
    public List<Produit> listerProduitsSousPrix(double prixMaximum) {
        return listerProduitsActifs().stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix))
                .takeWhile(p -> p.getPrix() < prixMaximum)
                .toList();
    }

    // 10. Compter les produits actifs
    public long compterProduitsActifs() {
        return listerProduitsActifs().stream()
                .count();
    }

    // 11. Afficher tous les produits actifs
    public void afficherProduitsActifs() {
        listerProduitsActifs().stream()
                .forEach(System.out::println);
    }

    // 12. Regrouper les produits par catégorie
    public Map<Integer, List<Produit>> regrouperProduitsParCategorie() {
        return listerProduitsActifs().stream()
                .collect(Collectors.groupingBy(Produit::getIdCategorie));
    }

    // 13. Trouver le produit le plus cher
    public Optional<Produit> trouverProduitPlusCher() {
        return listerProduitsActifs().stream()
                .max(Comparator.comparingDouble(Produit::getPrix));
    }

    // 14. Trouver le produit le moins cher
    public Optional<Produit> trouverProduitMoinsCher() {
        return listerProduitsActifs().stream()
                .min(Comparator.comparingDouble(Produit::getPrix));
    }

    // 15. Calculer la valeur totale du stock
    public double calculerValeurTotaleStock() {
        return listerProduitsActifs().stream()
                .mapToDouble(p -> p.getPrix() * p.getQuantiteStock())
                .sum();
    }
}
