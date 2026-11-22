
package service;

import java.util.List;
import java.util.Optional;

import model.Fournisseur;
import repository.FournisseurRepository;
import repository.ProduitRepository;

public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final ProduitRepository produitRepository;

    // Constructeur
    public FournisseurService(
            FournisseurRepository fournisseurRepository,
            ProduitRepository produitRepository) {

        this.fournisseurRepository = fournisseurRepository;
        this.produitRepository = produitRepository;
    }

    // Ajouter un fournisseur
    public boolean ajouterFournisseur(Fournisseur fournisseur) {

        if (fournisseur == null
                || fournisseur.getNom() == null
                || fournisseur.getNom().isBlank()) {
            return false;
        }

        // Vérifier si l'identifiant existe déjà
        if (fournisseurRepository
                .rechercherParId(fournisseur.getId()).isPresent()) {
            return false;
        }

        // Vérifier si l'adresse email existe déjà
        if (fournisseur.getEmail() != null
                && !fournisseur.getEmail().isBlank()) {

            boolean emailExiste = fournisseurRepository
                    .listerFournisseurs()
                    .stream()
                    .anyMatch(f -> fournisseur.getEmail()
                            .equalsIgnoreCase(f.getEmail()));

            if (emailExiste) {
                return false;
            }
        }

        fournisseurRepository.ajouterFournisseur(fournisseur);
        return true;
    }

    // Rechercher un fournisseur
    public Optional<Fournisseur> rechercherParId(int id) {
        return fournisseurRepository.rechercherParId(id);
    }

    // Lister les fournisseurs
    public List<Fournisseur> listerFournisseurs() {
        return fournisseurRepository.listerFournisseurs();
    }

    // Modifier un fournisseur
    public boolean modifierFournisseur(Fournisseur fournisseur) {

        if (fournisseur == null
                || fournisseur.getNom() == null
                || fournisseur.getNom().isBlank()) {
            return false;
        }

        Optional<Fournisseur> ancien =
                fournisseurRepository.rechercherParId(fournisseur.getId());

        if (ancien.isEmpty()) {
            return false;
        }

        // Vérifier l'unicité de l'email
        if (fournisseur.getEmail() != null
                && !fournisseur.getEmail().isBlank()) {

            boolean emailExiste = fournisseurRepository
                    .listerFournisseurs()
                    .stream()
                    .anyMatch(f ->
                            f.getId() != fournisseur.getId()
                            && fournisseur.getEmail()
                                    .equalsIgnoreCase(f.getEmail()));

            if (emailExiste) {
                return false;
            }
        }

        return fournisseurRepository.modifierFournisseur(fournisseur);
    }

    // Supprimer un fournisseur
    public boolean supprimerFournisseur(int id) {

        Optional<Fournisseur> fournisseur =
                fournisseurRepository.rechercherParId(id);

        if (fournisseur.isEmpty()) {
            return false;
        }

        // Vérifier si des produits utilisent ce fournisseur
        boolean utilise = produitRepository.listerProduits()
                .stream()
                .anyMatch(p -> p.getIdFournisseur() == id);

        if (utilise) {
            return false;
        }

        return fournisseurRepository.supprimerFournisseur(id);
    }
}
