package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.MouvementStock;
import model.TypeMouvement;

public class MouvementRepository {

    // 1. Liste pour stocker les mouvements
    private List<MouvementStock> mouvements = new ArrayList<>();

    // 2. Ajouter un mouvement
    public void ajouterMouvement(MouvementStock mouvement) {
        mouvements.add(mouvement);
    }

    // 3. Rechercher un mouvement par son ID
    public Optional<MouvementStock> rechercherParId(int id) {
        return mouvements.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }

    // 4. Lister tous les mouvements
    public List<MouvementStock> listerMouvements() {
        return new ArrayList<>(mouvements);
    }

    // 5. Rechercher les mouvements d'un produit
    public List<MouvementStock> rechercherParProduit(int idProduit) {
        return mouvements.stream()
                .filter(m -> m.getIdProduit() == idProduit)
                .toList();
    }

    // 6. Filtrer les mouvements par type
    public List<MouvementStock> rechercherParType(TypeMouvement type) {
        return mouvements.stream()
                .filter(m -> m.getType() == type)
                .toList();
    }

    // 7. Compter le nombre de mouvements
    public long compterMouvements() {
        return mouvements.stream()
                .count();
    }

    // 8. Vider la liste des mouvements
    public void vider() {
        mouvements.clear();
    }
}