package fr.cda.java.model.gestion;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.UniciteException;
import fr.cda.java.Exceptions.donneeException;
import fr.cda.java.model.liste.Clients;
import fr.cda.java.model.liste.Prospects;
import fr.cda.java.model.util.Adresse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

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

    private static int compteurIdentifiant = 1;
    long chiffreAffaire;
    int nombreEmployes;
    List<Contrat> listeContrats = new ArrayList<>();

    public Client(String raisonSociale, Adresse adresse, String telephone, String adresseMail,
            String commentaire, long chiffreAffaire, int nombreEmployes) {

        super(compteurIdentifiant, adresse, telephone, adresseMail, commentaire);
        this.setChiffreAffaire(chiffreAffaire);
        this.setNombreEmployes(nombreEmployes);
        this.setRaisonSociale(raisonSociale);
        Clients.listeClients.put(raisonSociale, this);
        compteurIdentifiant++;
    }

    /**
     * C'est plus safe pour anticiper les données corrompues en json de passer par ici et utiliser
     * les setter.
     *
     * @param json
     */
    public Client(JSONObject json) {

        super(json.getInt("identifiant"),
                new Adresse(json.getJSONObject("adresse")),
                json.getString("telephone"),
                json.getString("adresseMail"),
                json.getString("commentaire")
        );
        this.setChiffreAffaire(json.getLong("chiffreAffaire"));
        this.setRaisonSociale(json.getString("raisonSociale"));
        this.setNombreEmployes(json.getInt("nombreEmployes"));
        Clients.listeClients.put(json.getString("raisonSociale"), this);
        compteurIdentifiant++;
    }

    /**
     * @return listeContrats description
     */
    public List<Contrat> getListeContrats() {
        return listeContrats;
    }

    /**
     * @param listeContrats description
     */
    public void setListeContrats(List<Contrat> listeContrats) {

        this.listeContrats = listeContrats;
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
        if (chiffreAffaire < 200) {
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

    @Override
    public void setRaisonSociale(String raisonSociale) {
        /**
         * si la raison sociale existe déjà, on s'assure qu'il s'agit pas de l'objet en cours de traitement
         */
        if (Prospects.listeProspects.containsKey(raisonSociale)
                || (Clients.listeClients.containsKey(raisonSociale)
                && Clients.listeClients.get(raisonSociale).getIdentifiant()
                != this.getIdentifiant())) {
            throw new UniciteException(raisonSociale);
        }
        super.setRaisonSociale(raisonSociale);
    }

}
