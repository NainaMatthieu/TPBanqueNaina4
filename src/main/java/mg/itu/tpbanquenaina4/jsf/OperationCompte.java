package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;

/**
 *
 * @author NainaMatthieu
 */
@Named(value = "operationCompte")
@ViewScoped
public class OperationCompte implements Serializable {

    private Long id;
    private CompteBancaire compte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of OperationCompte
     */
    public OperationCompte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }

}
