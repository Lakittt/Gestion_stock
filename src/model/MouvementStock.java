
package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MouvementStock {

    private int id;
    private int idProduit;
    private TypeMouvement type;
    private int quantite;
    private LocalDateTime dateMouvement;
    private String commentaire;

    public MouvementStock(int id, int idProduit, TypeMouvement type,int quantite, LocalDateTime dateMouvement,
                          String commentaire) {

        this.id = id;
        this.idProduit = idProduit;
        this.type = type;
        this.quantite = quantite;
        this.dateMouvement = dateMouvement;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public TypeMouvement getType() {
        return type;
    }

    public int getQuantite() {
        return quantite;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    @Override
    public String toString() {
        return "MouvementStock{" + "id=" + id + ", idProduit=" + idProduit + ", type=" + type + ", quantite=" + quantite +
                ", dateMouvement=" + dateMouvement +", commentaire='" + commentaire + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MouvementStock autre = (MouvementStock) obj;

        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
