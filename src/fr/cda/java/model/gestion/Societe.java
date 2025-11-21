package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.RegexException;
import fr.cda.java.model.util.Adresse;
import fr.cda.java.model.util.Regex;
import org.junit.platform.commons.util.StringUtils;

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


    private int identifiant;
    private String raisonSociale;
    private Adresse adresse;
    private String telephone;
    private String adresseMail;
    private String commentaire;

    public Societe(Adresse adresse, String telephone,
            String adresseMail, String commentaire) {
        this.setAdresse(adresse);
        this.setTelephone(telephone);
        this.setAdresseMail(adresseMail);
        this.setCommentaire(commentaire);
    }

    public Societe(Societe societe) {
        this(societe.getAdresse(),
                societe.getTelephone(),
                societe.getAdresseMail(),
                societe.getCommentaire());
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

        if (StringUtils.isBlank(raisonSociale)) {
            throw new MandatoryDataException("raison sociale");
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
        if (StringUtils.isBlank(telephone)) {
            throw new MandatoryDataException("téléphone");
        }
        if (!telephone.matches(Regex.PHONE_FR_SIMPLE.getPattern())) {
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

        if (StringUtils.isBlank(adresseMail)) {
            throw new MandatoryDataException("adresse mail");
        }
        if (!adresseMail.matches(Regex.EMAIL.getPattern())) {
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

        if (StringUtils.isBlank(commentaire)) {
            commentaire = "";
        }

        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return raisonSociale;
    }
}
