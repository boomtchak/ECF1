package fr.cda.java.model.util;

public enum Interet {
    // Les différentes valeurs possibles
    OUI("oui"),
    NON("non");


    // Un champ pour stocker le nom "propre"
    private final String affichage;

    // Le constructeur (toujours privé pour une enum)
    Interet(String affichage) {
        this.affichage = affichage;
    }

    // Elle sera appelée automatiquement pour afficher le texte.
    @Override
    public String toString() {
        return this.affichage;
    }


}
