package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;

/**
 *
 * @author NainaMatthieu
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private List<CompteBancaire> allComptes;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

     /**
     * Retourne la liste des comptes bancaires
     *
     * @return La liste des comptes bancaires.
     */
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllComptes();
        }
        return allComptes;
    }
}
