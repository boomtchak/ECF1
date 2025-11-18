package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.model.util.Adresse;

/**
 * Contrat
 *
 * <p>Description : …</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public  class Contrat {
private static int compteurIdentifiant =0;
private int identifiant;
private int identifiantClient;
private String nomContrat;
private String montantContrat;

    public Contrat(int identifiantClient, String nomContrat,
            String montantContrat) {
        this.setIdentifiant( compteurIdentifiant);
        this.setIdentifiantClient(identifiantClient);
        this.setNomContrat(nomContrat);
        this.setMontantContrat(montantContrat);
        compteurIdentifiant ++;
    }

    /**
     * @return identifiant description
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant description
     */
    public void setIdentifiant(int identifiant) {

        this.identifiant = identifiant;
    }

    /**
     * @return identifiantClient description
     */
    public int getIdentifiantClient() {
        return identifiantClient;
    }

    /**
     * @param identifiantClient description
     */
    public void setIdentifiantClient(int identifiantClient) {

        this.identifiantClient = identifiantClient;
    }

    /**
     * @return nomContrat description
     */
    public String getNomContrat() {
        return nomContrat;
    }

    /**
     * @param nomContrat description
     */
    public void setNomContrat(String nomContrat) {

        if (nomContrat == null) {
           throw new MandatoryDataException("nom du contrat");
        }

        this.nomContrat = nomContrat;
    }

    /**
     * @return montantContrat description
     */
    public String getMontantContrat() {
        return montantContrat;
    }

    /**
     * @param montantContrat description
     */
    public void setMontantContrat(String montantContrat) {
        if (montantContrat == null) {
            throw new MandatoryDataException("montant du contrat");
        }


        this.montantContrat = montantContrat;
    }
}
