package fr.cda.java.model.gestion;

import fr.cda.java.model.util.Adresse;
import java.util.ArrayList;
import java.util.List;

/**
 * Client
 *
 * <p>Description : …</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Client extends Societe {

    static int identifiant;
    long chiffreAffaire;
    int nombreEmployes;
    List<Contrat> listeContrats = new ArrayList<>();

    public Client(int identifiant, String raisonSociale, Adresse adresse,
            String telephone, String adresseMail, String commentaire) {
        super(identifiant, raisonSociale, adresse, telephone, adresseMail, commentaire);
    }


    /**
     * @return chiffreAffaire description
     */
    public long getChiffreAffaire() {
        return chiffreAffaire;
    }

    /**
     * @param chiffreAffaire description
     */
    public void setChiffreAffaire(long chiffreAffaire) {

        this.chiffreAffaire = chiffreAffaire;
    }

    /**
     * @return nombreEmployes description
     */
    public int getNombreEmployes() {
        return nombreEmployes;
    }

    /**
     * @param nombreEmployes description
     */
    public void setNombreEmployes(int nombreEmployes) {

        this.nombreEmployes = nombreEmployes;
    }


}
