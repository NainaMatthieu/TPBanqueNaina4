package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.jsf.util.Util;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;

/**
 * Bean pour gérer le transfert d'argent d'un compte à un autre
 *
 * @author NainaMatthieu
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgent {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long idCompteSource;

    private Long idCompteTarget;
    private int montant;

    public Long getIdCompteSource() {
        return idCompteSource;
    }

    public void setIdCompteSource(Long idCompteSource) {
        this.idCompteSource = idCompteSource;
    }

    public Long getIdCompteTarget() {
        return idCompteTarget;
    }

    public void setIdCompteTarget(Long idCompteTarget) {
        this.idCompteTarget = idCompteTarget;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public String transferer() {
        boolean erreur = false;
        CompteBancaire comptebancaireSource = gestionnaireCompte.findById(idCompteSource);
        if (comptebancaireSource == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (comptebancaireSource.getSolde() < montant) {
                 Util.messageErreur("Le solde de votre compte est insuffisant", "Solde insuffisant!", "form:montant");
                erreur = true;
            }
        }
         if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        CompteBancaire comptebancaireTarget = gestionnaireCompte.findById(idCompteTarget);
        if(comptebancaireTarget==null){
             Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
             return null;
        }
        gestionnaireCompte.transferer(comptebancaireSource, comptebancaireTarget, montant);
         String message = String.format("Transfert de %s effectué avec succès du compte de %s vers le compte de %s.",
                                         montant, comptebancaireSource.getNom(), comptebancaireTarget.getNom());
         Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
