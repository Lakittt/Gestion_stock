
package service;

import java.util.List;
import java.util.Optional;

import model.Categorie;
import repository.CategorieRepository;
import repository.ProduitRepository;

public class CategorieService {

    private final CategorieRepository categorieRepository;
    private final ProduitRepository produitRepository;

    // Constructeur
    public CategorieService(
            CategorieRepository categorieRepository,
            ProduitRepository produitRepository) {

        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
    }

    // Ajouter une catégorie
    public boolean ajouterCategorie(Categorie categorie) {

        if (categorie == null
                || categorie.getNom() == null
                || categorie.getNom().isBlank()) {
            return false;
        }

        // Vérifier si le nom existe déjà
        boolean existe = categorieRepository.listerCategories()
                .stream()
                .anyMatch(c -> c.getNom().equalsIgnoreCase(categorie.getNom()));

        if (existe) {
            return false;
        }

        // Vérifier si l'identifiant existe déjà
        if (categorieRepository
                .rechercherParId(categorie.getId()).isPresent()) {
            return false;
        }

        categorieRepository.ajouterCategorie(categorie);
        return true;
    }

    // Rechercher une catégorie par ID
    public Optional<Categorie> rechercherParId(int id) {
        return categorieRepository.rechercherParId(id);
    }

    // Lister toutes les catégories
    public List<Categorie> listerCategories() {
        return categorieRepository.listerCategories();
    }

    // Modifier une catégorie
    public boolean modifierCategorie(Categorie categorie) {

        if (categorie == null
                || categorie.getNom() == null
                || categorie.getNom().isBlank()) {
            return false;
        }

        Optional<Categorie> ancienne =
                categorieRepository.rechercherParId(categorie.getId());

        if (ancienne.isEmpty()) {
            return false;
        }

        // Vérifier que le nom n'appartient pas à une autre catégorie
        boolean nomExiste = categorieRepository.listerCategories()
                .stream()
                .anyMatch(c ->
                        c.getId() != categorie.getId()
                        && c.getNom().equalsIgnoreCase(categorie.getNom()));

        if (nomExiste) {
            return false;
        }

        return categorieRepository.modifierCategorie(categorie);
    }

    // Supprimer une catégorie
    public boolean supprimerCategorie(int id) {

        Optional<Categorie> categorie =
                categorieRepository.rechercherParId(id);

        if (categorie.isEmpty()) {
            return false;
        }

        // Vérifier si des produits utilisent cette catégorie
        boolean utilisee = produitRepository.listerProduits()
                .stream()
                .anyMatch(p -> p.getIdCategorie() == id);

        if (utilisee) {
            return false;
        }

        return categorieRepository.supprimerCategorie(id);
    }
}
