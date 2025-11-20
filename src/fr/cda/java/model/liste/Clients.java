package fr.cda.java.model.liste;

import fr.cda.java.model.gestion.Client;
import java.util.TreeMap;

/**
 * Clients
 *
 * <p>Description : liste des clients utilisées pour le chargement de données en masse </p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 05/11/2025
 */
public class Clients {

    private static TreeMap<String, Client> listeClients = new TreeMap<String, Client>();
    // c'est le point central pour la "persistance"
    private static int compteurIdentifiant = 1;

    public static void ajouter(Client client) {
        client.setIdentifiant(compteurIdentifiant);
        listeClients.put(client.getRaisonSociale(), client);
        compteurIdentifiant++;
    }

    public static void replace(String index, Client client) {

        listeClients.replace(client.getRaisonSociale(), client);
    }

    /**
     * @return listeClients description
     */
    public static TreeMap<String, Client> getListeClients() {
        return listeClients;
    }

    /**
     * @return compteurIdentifiant description
     */
    public static int getCompteurIdentifiant() {
        return compteurIdentifiant;
    }
}