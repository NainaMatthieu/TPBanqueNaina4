/**
 *
 * Cette classe représente une entité CompteBancaire dans le système.
 * Un CompteBancaire est un compte financier associé à un client, caractérisé par un nom et un solde.
 */
package mg.itu.tpbanquenaina4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * Classe représentant un compte bancaire.
 *
 * Un compte bancaire est un compte financier associé à un client.
 *
 * Il est caractérisé par un nom et un solde.
 *
 * @author NainaMatthieu
 */
@Entity
@Table(name = "COMPTEBANCAIRE")
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById", query = "SELECT c FROM CompteBancaire c WHERE c.id = :id")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int solde;

    public CompteBancaire() {
    }

    /**
     * Constructeur de la classe CompteBancaire.
     *
     * @param nom Le nom du titulaire du compte.
     * @param solde Le solde initial du compte.
     */
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Ajoute un montant au solde du compte.
     *
     * @param montant Montant à déposer.
     */
    public void deposer(int montant) {
        solde += montant;
    }

    /**
     * Retire un montant du solde du compte, si possible. Si le montant à
     * retirer est supérieur au solde, le solde devient 0.
     *
     * @param montant Montant à retirer.
     */
    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
    }

    /**
     * Obtient le id du compte.
     *
     * @return Le id du compte.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtient le solde du compte.
     *
     * @return Le solde du compte.
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Définit le solde du compte.
     *
     * @param solde Nouveau solde du compte.
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Obtient le nom du titulaire du compte.
     *
     * @return Le nom du titulaire du compte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du titulaire du compte.
     *
     * @param nom Nouveau nom du titulaire du compte.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Calcule le hashcode du compte bancaire en utilisant son identifiant.
     *
     * @return Le hashcode du compte bancaire.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Vérifie l'égalité de ce compte bancaire avec un autre objet.
     *
     * @param object Objet à comparer.
     * @return True si les objets sont égaux, sinon False.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Obtient une représentation sous forme de chaîne de caractères de ce
     * compte bancaire.
     *
     * @return Une chaîne représentant ce compte bancaire.
     */
    @Override
    public String toString() {
        return "mg.itu.tpbanquenaina4.entity.CompteBancaire[ id=" + id + " ]";
    }

}
