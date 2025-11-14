package fr.cda.java.model.gestion;

import fr.cda.java.model.util.Adresse;
import fr.cda.java.model.util.Interet;
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
    private String interresse;

    public Prospect(int identifiant, String raisonSociale, Adresse adresse, String telephone,
            String adresseMail, String commentaire, LocalDate dateProspection, String interresse) {
        super(identifiant, raisonSociale, adresse, telephone, adresseMail, commentaire);
        this.dateProspection = dateProspection;
        this.interresse = interresse;

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
     * @return interresse description
     */
    public String getInterresse() {
        return interresse;
    }

    /**
     * @param interresse description
     */
    public void setInterresse(String interresse) {

        if (interresse == null) {
            interresse = "";
        }

        this.interresse = interresse;
    }
}
