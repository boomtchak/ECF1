package fr.cda.java.model.liste;

import java.util.TreeMap;

public interface Lister<T> {

    TreeMap listeClients = new TreeMap();
    // c'est le point central pour la "persistance"
    int compteurIdentifiant = 1;
    int idInutilise = 0;



    void ajouter(T entite);

    void replace(String index,T entite);

    void supprimer(T entite);


    TreeMap<String, T> getListeSocietes();


    int getCompteurIdentifiant();

}
