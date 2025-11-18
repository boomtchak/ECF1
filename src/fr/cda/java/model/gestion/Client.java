package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.donneeException;
import fr.cda.java.model.liste.Clients;
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

    private static int compteurIdentifiant = 0;
    private int identifiant;
    long chiffreAffaire;
    int nombreEmployes;
    List<Contrat> listeContrats = new ArrayList<>();

    public Client(String raisonSociale, Adresse adresse, String telephone, String adresseMail,
            String commentaire, long chiffreAffaire, int nombreEmployes) {

        super(compteurIdentifiant, raisonSociale, adresse, telephone, adresseMail, commentaire);
        this.setChiffreAffaire(chiffreAffaire);
        this.setNombreEmployes(nombreEmployes);
        Clients.listeClients.put(raisonSociale, this);
        compteurIdentifiant++;
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
        if (chiffreAffaire == 0) {
            throw new MandatoryDataException("chiffre d'affaire");
        }
        if(chiffreAffaire < 200){
            throw new donneeException("le chiffre d'affaire doit être supérieur à 200");
        }

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
        if (nombreEmployes == 0) {
            throw new MandatoryDataException("nombre d'employés");
        }
        this.nombreEmployes = nombreEmployes;
    }


}
