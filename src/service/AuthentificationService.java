
package service;

import java.util.Optional;

import model.Utilisateur;
import repository.UtilisateurRepository;

public class AuthentificationService {

    // 1. Repository utilisé pour rechercher les utilisateurs
    private final UtilisateurRepository utilisateurRepository;

    // 2. Constructeur
    public AuthentificationService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // 3. Authentifier un utilisateur
    public Optional<Utilisateur> authentifier(String identifiant, String motDePasse) {

        // Vérifier que les informations saisies ne sont pas vides
        if (identifiant == null || identifiant.isBlank()
                || motDePasse == null || motDePasse.isEmpty()) {
            return Optional.empty();
        }

        // Rechercher l'utilisateur par son identifiant
        Optional<Utilisateur> resultat =
                utilisateurRepository.rechercherParIdentifiant(identifiant);

        // Vérifier si l'utilisateur existe
        if (resultat.isPresent()) {

            Utilisateur utilisateur = resultat.get(); 

            // Vérifier le mot de passe
            if (utilisateur.getMotDePasse().equals(motDePasse)) {
                return Optional.of(utilisateur);
            }
        }

        // Identifiant ou mot de passe incorrect
        return Optional.empty();
    }
}
