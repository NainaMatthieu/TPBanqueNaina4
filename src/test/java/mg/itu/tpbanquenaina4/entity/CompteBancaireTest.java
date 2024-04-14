package mg.itu.tpbanquenaina4.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * classe de test unitaire pour l'entité CompteBancaire
 *
 * @author NainaMatthieu
 */
public class CompteBancaireTest {

    public CompteBancaireTest() {
    }

    /**
     * Test of deposer method, of class CompteBancaire.
     */
    @Test
    public void testDeposer() {
        System.out.println("deposer");
        int montant = 100;
        CompteBancaire instance = new CompteBancaire();
        instance.deposer(montant);
        assertEquals(instance.getSolde(), montant, "Mauvais calcul du solde après un dépôt");
    }

    @Test
    public void testConstructor() {
        int montant = 100;
        CompteBancaire instance = new CompteBancaire("toto", montant);
        assertEquals(instance.getNom(), "toto", "Nom titulaire compte mal enregistré dans l'instance");
        assertEquals(instance.getSolde(), montant, "Montant compte mal enregistré dans l'instance");
    }
    

}
