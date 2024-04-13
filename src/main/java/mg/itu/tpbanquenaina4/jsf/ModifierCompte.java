/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenaina4.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquenaina4.entity.CompteBancaire;
import mg.itu.tpbanquenaina4.jsf.util.Util;
import mg.itu.tpbanquenaina4.service.GestionnaireCompte;

/**
 *
 * @author NainaMatthieu
 */
@Named(value = "modifierCompte")
@ViewScoped
public class ModifierCompte implements Serializable {

    private Long id;
    private String nouveauNom;
    private CompteBancaire compte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ModifierCompte
     */
    public ModifierCompte() {
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

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    }
    public String modifierCompte(){
        String ancienNom = compte.getNom();
        compte.setNom(nouveauNom);
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage(ancienNom+" changé en "+nouveauNom);
        return "listeComptes?faces-redirect=true";
    }
}
