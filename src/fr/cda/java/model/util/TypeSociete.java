package fr.cda.java.model.util;

public enum TypeSociete {
    // Les différentes valeurs possibles
    CLIENT("client"),
    PROSPECT("prospect");


    // Un champ pour stocker le nom "propre"
    private final String affichage;

    // Le constructeur (toujours privé pour une enum)
    TypeSociete(String affichage) {
        this.affichage = affichage;
    }

    // Elle sera appelée automatiquement pour afficher le texte.
    @Override
    public String toString() {
        return this.affichage;
    }
}
