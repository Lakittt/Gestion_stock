package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Utilisateur;

public class UtilisateurRepository {

    // 1. Liste pour stocker les utilisateurs
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    // 2. Ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // 3. Rechercher un utilisateur par son ID
    public Optional<Utilisateur> rechercherParId(int id) {
        return utilisateurs.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    // 4. Rechercher un utilisateur par son identifiant
    public Optional<Utilisateur> rechercherParIdentifiant(String identifiant) {
        return utilisateurs.stream()
                .filter(u -> u.getIdentifiant().equals(identifiant))
                .findFirst(); 
    }

    // 5. Lister tous les utilisateurs
    public List<Utilisateur> listerUtilisateurs() {
        return new ArrayList<>(utilisateurs);
    }

    // 6. Supprimer un utilisateur
    public boolean supprimerUtilisateur(int id) {

        Optional<Utilisateur> resultat = rechercherParId(id);

        if (resultat.isPresent()) {
            utilisateurs.remove(resultat.get());
            return true;
        }

        return false;
    }

    // 7. Vider la liste des utilisateurs
    public void vider() {
        utilisateurs.clear();
    }
}