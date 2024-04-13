package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
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
        CompteBancaire comptebancaireSource = gestionnaireCompte.findById(idCompteSource);
        CompteBancaire comptebancaireTarget = gestionnaireCompte.findById(idCompteTarget);
        gestionnaireCompte.transferer(comptebancaireSource, comptebancaireTarget, montant);
        return "listeComptes?faces-redirect=true";
    }
}
