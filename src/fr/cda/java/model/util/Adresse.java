package fr.cda.java.model.util;

/**
 * Adresse
 *
 * <p>Description : classe 1 1 avec les societes, se gere sans fichier ni liste.</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Adresse {

    int identifiant;
    String numeroDeRue;
    String nomDeRue;
    String codePostal;
    String ville;

    public Adresse(int identifiant, String numeroDeRue, String nomDeRue, String codePostal,
            String ville) {
        this.identifiant = identifiant;
        this.numeroDeRue = numeroDeRue;
        this.nomDeRue = nomDeRue;
        this.codePostal = codePostal;
        this.ville = ville;
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
     * @return numeroDeRue description
     */
    public String getNumeroDeRue() {
        return numeroDeRue;
    }

    /**
     * @param numeroDeRue description
     */
    public void setNumeroDeRue(String numeroDeRue) {

        if (numeroDeRue == null) {
            numeroDeRue = "";
        }

        this.numeroDeRue = numeroDeRue;
    }

    /**
     * @return nomDeRue description
     */
    public String getNomDeRue() {
        return nomDeRue;
    }

    /**
     * @param nomDeRue description
     */
    public void setNomDeRue(String nomDeRue) {

        if (nomDeRue == null) {
            nomDeRue = "";
        }

        this.nomDeRue = nomDeRue;
    }

    /**
     * @return codePostal description
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * @param codePostal description
     */
    public void setCodePostal(String codePostal) {

        if (codePostal == null) {
            codePostal = "";
        }

        this.codePostal = codePostal;
    }

    /**
     * @return ville description
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville description
     */
    public void setVille(String ville) {

        if (ville == null) {
            ville = "";
        }

        this.ville = ville;
    }
}
