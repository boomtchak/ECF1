package fr.cda.java.model.liste;

import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.gestion.Prospect;
import java.util.TreeMap;

/**
 * Prospects
 *
 * <p>Description : …</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Prospects {
    private static int compteurIdentifiant = 1;
    private static TreeMap<String, Prospect> listeProspects = new TreeMap<String, Prospect>();

    public static void ajouter(Prospect prospect) {
        prospect.setIdentifiant(compteurIdentifiant);
        listeProspects.put(prospect.getRaisonSociale(), prospect);
        compteurIdentifiant++;
    }

    public static void replace(String index, Prospect prospect) {

        listeProspects.replace(prospect.getRaisonSociale(), prospect);
    }

    /**
     * @return listeClients description
     */
    public static TreeMap<String, Prospect> getListeProspect() {
        return listeProspects;
    }

    /**
     * @return compteurIdentifiant description
     */
    public static int getCompteurIdentifiant() {
        return compteurIdentifiant;
    }
}