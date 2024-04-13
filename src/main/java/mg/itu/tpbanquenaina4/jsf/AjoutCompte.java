package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.jsf.util.Util;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;

/**
 * Bean pour la création d'un nouveau compte bancaire
 *
 * @author NainaMatthieu
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private String nom;

    @PositiveOrZero
    private int solde;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouterCompte() {
        CompteBancaire compteToCreate = new CompteBancaire();
        compteToCreate.setNom(nom);
        compteToCreate.setSolde(solde);
        try {
            gestionnaireCompte.creerCompte(compteToCreate);
        } catch (PersistenceException  e) {
            Util.messageErreur(e.getMessage(), "Une erreur s'est produite!", "form:erreur");
            return null;
        }
        String message = String.format("Création du compte bancaire  de %s avec solde %s effectué avec succès",nom, solde);
        Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
