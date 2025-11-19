package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.RegexException;
import fr.cda.java.model.liste.Prospects;
import fr.cda.java.model.util.Adresse;
import fr.cda.java.model.util.Regex;
import java.time.LocalDate;

/**
 * Prospect
 *
 * <p>Description : …</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Prospect extends Societe {

    private LocalDate dateProspection;
    private Interresse interet;

    public static enum Interresse {
        OUI("est interessé"),
        NON("n'est pas interressé"),
        INCONNU("");

        private final String description;

        Interresse(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Prospect(String raisonSociale, Adresse adresse, String telephone,
            String adresseMail, String commentaire, LocalDate dateProspection, Interresse interresse) {
        super(compteurIdentifiant,raisonSociale, adresse, telephone, adresseMail, commentaire);
        this.setDateProspection(dateProspection);
        this.setInteret(interresse);
        Prospects.listeProspects.put(raisonSociale, this);
        compteurIdentifiant++;

    }

    /**
     * @return dateProspection description
     */
    public LocalDate getDateProspection() {
        return dateProspection;
    }

    /**
     * @param dateProspection description
     */
    public void setDateProspection(LocalDate dateProspection) {
        this.dateProspection = dateProspection;
    }

    /**
     * @return interet description
     */
    public Interresse getInteret() {
        return interet;
    }

    /**
     * @param interet description
     */
    public void setInteret(Interresse interet) {

        if (interet == null) {
            interet= Interresse.INCONNU;
        }

        this.interet = interet;
    }
}
