package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.RegexException;
import fr.cda.java.Exceptions.UniciteException;
import fr.cda.java.model.liste.Clients;
import fr.cda.java.model.liste.Prospects;
import fr.cda.java.model.util.Adresse;
import fr.cda.java.model.util.Regex;
import java.util.GregorianCalendar;

/**
 * Societe
 *
 * <p>Description : …</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Societe {

    private  int identifiant;
    private String raisonSociale;
    private Adresse adresse;
    private String telephone;
    private String adresseMail;
    private String commentaire;

    public Societe(int identifiant, String raisonSociale, Adresse adresse, String telephone,
            String adresseMail, String commentaire) {
        this.setRaisonSociale(raisonSociale);
        this.setAdresse(adresse);
        this.setTelephone(telephone);
        this.setAdresseMail(adresseMail);
        this.setCommentaire(commentaire);
        this.setIdentifiant(identifiant);
    }

    /**
     * @param identifiant description
     */
    public void setIdentifiant(int identifiant) {

        this.identifiant = identifiant;
    }

    /**
     * @return identifiant description
     */
    public int getIdentifiant() {
        return identifiant;
    }



    /**
     * @return raisonSociale description
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * @param raisonSociale description
     */
    public void setRaisonSociale(String raisonSociale) {

        if (raisonSociale == null) {
            throw new MandatoryDataException("raison sociale");
        }
        /**
         * si la raison sociale existe déjà, on s'assure qu'il s'agit pas de l'objet en cours de traitement
         */
        if ((Clients.listeClients.containsKey(raisonSociale)
                && Clients.listeClients.get(raisonSociale).getIdentifiant() != this.identifiant)
                || (Prospects.listeProspects.containsKey(raisonSociale)
                && Prospects.listeProspects.get(raisonSociale).getIdentifiant()
                != this.identifiant)) {
            throw new UniciteException(raisonSociale);
        }
        this.raisonSociale = raisonSociale;
    }

    /**
     * @return adresse description
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * @param adresse description
     */
    public void setAdresse(Adresse adresse) {

        this.adresse = adresse;
    }

    /**
     * @return telephone description
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone description
     */
    public void setTelephone(String telephone) {
        if (telephone == null) {
            throw new MandatoryDataException("téléphone");
        }
        if(!telephone.matches(Regex.PHONE_FR_SIMPLE.getPattern())){
            throw new RegexException("téléphone");
        }

        this.telephone = telephone;
    }

    /**
     * @return adresseMail description
     */
    public String getAdresseMail() {
        return adresseMail;
    }

    /**
     * @param adresseMail description
     */
    public void setAdresseMail(String adresseMail) {

        if (adresseMail == null) {
            throw new MandatoryDataException("adresse mail");
        }
        if(!adresseMail.matches(Regex.EMAIL.getPattern())){
            throw new RegexException("adresse mail");
        }

        this.adresseMail = adresseMail;
    }

    /**
     * @return commentaire description
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire description
     */
    public void setCommentaire(String commentaire) {

        if (commentaire == null) {
            commentaire = "";
        }

        this.commentaire = commentaire;
    }
}
