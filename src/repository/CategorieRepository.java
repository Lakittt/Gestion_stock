package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Categorie;

public class CategorieRepository {

    // 1. Liste pour stocker les catégories
    private List<Categorie> categories = new ArrayList<>();

    // 2. Ajouter une catégorie
    public void ajouterCategorie(Categorie categorie) {
        categories.add(categorie);
    }

    // 3. Rechercher une catégorie par son ID
    public Optional<Categorie> rechercherParId(int id) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    // 4. Lister toutes les catégories
    public List<Categorie> listerCategories() {
        return new ArrayList<>(categories);
    }

    // 5. Modifier une catégorie
    public boolean modifierCategorie(Categorie nouvelleCategorie) {

        Optional<Categorie> resultat =
                rechercherParId(nouvelleCategorie.getId());

        if (resultat.isPresent()) {
            Categorie ancienneCategorie = resultat.get();

            ancienneCategorie.setNom(nouvelleCategorie.getNom());
            ancienneCategorie.setDescription(nouvelleCategorie.getDescription());

            return true;
        }

        return false;
    }

    // 6. Supprimer une catégorie
    public boolean supprimerCategorie(int id) {

        Optional<Categorie> resultat = rechercherParId(id);

        if (resultat.isPresent()) {
            categories.remove(resultat.get());
            return true;
        }

        return false;
    }

    // 7. Vider la liste des catégories
    public void vider() {
        categories.clear();
    }
}