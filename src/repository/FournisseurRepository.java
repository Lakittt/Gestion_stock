package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Fournisseur;

public class FournisseurRepository {

    // 1. Liste pour stocker les fournisseurs
    private List<Fournisseur> fournisseurs = new ArrayList<>();

    // 2. Ajouter un fournisseur
    public void ajouterFournisseur(Fournisseur fournisseur) {
        fournisseurs.add(fournisseur);
    }

    // 3. Rechercher un fournisseur par son ID
    public Optional<Fournisseur> rechercherParId(int id) {
        return fournisseurs.stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    // 4. Lister tous les fournisseurs
    public List<Fournisseur> listerFournisseurs() {
        return new ArrayList<>(fournisseurs);
    }

    // 5. Modifier un fournisseur
    public boolean modifierFournisseur(Fournisseur nouveauFournisseur) {

        Optional<Fournisseur> resultat =
                rechercherParId(nouveauFournisseur.getId());

        if (resultat.isPresent()) {
            Fournisseur ancienFournisseur = resultat.get();

            ancienFournisseur.setNom(nouveauFournisseur.getNom());
            ancienFournisseur.setTelephone(nouveauFournisseur.getTelephone());
            ancienFournisseur.setEmail(nouveauFournisseur.getEmail());
            ancienFournisseur.setAdresse(nouveauFournisseur.getAdresse());

            return true;
        }

        return false;
    }

    // 6. Supprimer un fournisseur
    public boolean supprimerFournisseur(int id) {

        Optional<Fournisseur> resultat = rechercherParId(id);

        if (resultat.isPresent()) {
            fournisseurs.remove(resultat.get());
            return true;
        }

        return false;
    }

    // 7. Vider la liste des fournisseurs
    public void vider() {
        fournisseurs.clear();
    }
}