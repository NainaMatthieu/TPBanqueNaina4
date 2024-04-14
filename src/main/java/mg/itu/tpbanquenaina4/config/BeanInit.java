package mg.itu.tpbanquenaina4.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;
import java.util.logging.Logger;

/**
 * Bean pour créer 4 CompteBancaire au démarrage de l'application si la table
 * des comptes est vide
 *
 * @author NainaMatthieu
 */
@ApplicationScoped
public class BeanInit {

    private final static Logger logger = Logger.getLogger("mg.itu.tpbanquenaina4.config.BeanInit");

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        if (!gestionnaireCompte.getAllComptes().isEmpty()) {
            logger.info("La base de données n'est pas vide");
            return;
        }
        logger.warning("Aucun compte dans la base de données. Création de comptes");
        gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }
}
