package fr.cda.java.dao;

import fr.cda.java.model.gestion.Contrat;
import fr.cda.java.model.liste.Clients;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.json.*;

/**
 * ProspectDao
 *
 * <p>Description : une simple classe bouchon,
 * qui permet de charger les données à l'ouverture de l'appli (voir init) depuis les fichiers
 * et de les sauvegarder à la fermeture de l'application dans les fichiers.
 * Pas de sauvegarde en temps reel.</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 07/11/2025
 */
public class Dao {

    /**
     * ca va sauvegarder tous les clients, avec tous leur contrats, et tous les prospects ensuite.
     * @throws IOException
     */
    public void Sauvegarder() throws IOException {
        JSONObject son = new JSONObject(Clients.listeClients);

        System.out.println(son);
    }

    /**
     * Externalisation pour la lecture. ca prends la liste de chaques clients et ca la sauvegarde
     * dans un fichier à part, on reconstruira en utilisant l'indexation.
     * Si on a le temps on testera les temps de chargement voir si c'est pas trop lourd.
     * @param listeContrats
     */
    private void SauvegarderContrats(List<Contrat> listeContrats){

    }
    /**
     * ca charge une liste de contrat que ca index par id client. ensuite, quand on chargera les
     * clients, on aura plus qu'à setter leur liste contrats.
     */
    public void charger(){


    }

}
