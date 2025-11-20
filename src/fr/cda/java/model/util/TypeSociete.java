package fr.cda.java.model.util;

import fr.cda.java.model.liste.Clients;
import fr.cda.java.model.liste.Prospects;
import java.util.TreeMap;

public enum TypeSociete {
    // Les différentes valeurs possibles
    CLIENT("client", Clients.getListeClients()),
    PROSPECT("prospect", Prospects.getListeProspect());


    // Un champ pour stocker le nom "propre"
    private final String affichage;
    // Un champ pour stocker la liste de sociétés
    private final TreeMap listeSocietes;

    // Le constructeur (toujours privé pour une enum)
    TypeSociete(String affichage, TreeMap listeSocietes1) {
        this.affichage = affichage;
        this.listeSocietes = listeSocietes1;
    }

    /**
     * @return listeSocietes description
     */
    public TreeMap getListeSocietes() {
        return listeSocietes;
    }

    /**
     * @return listeSocietes description
     */
    public TreeMap ListeSocietes() {
        return listeSocietes;
    }

    /**
     * @return affichage description
     */
    public String getAffichage() {
        return affichage;
    }

    // Elle sera appelée automatiquement pour afficher le texte.
    @Override
    public String toString() {
        return this.affichage;
    }
}
