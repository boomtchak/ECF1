package fr.cda.java.model.liste;

import fr.cda.java.model.gestion.Client;
import java.util.ArrayList;
import java.util.List;
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

    public static TreeMap<String, Client> listeClients = new TreeMap<String, Client>();

    public static List<String> toCsv() {
        List<String> listeRetour = new ArrayList<>();
        for (Client client : listeClients.values()) {
            StringBuilder stb = new StringBuilder();

        }
    }