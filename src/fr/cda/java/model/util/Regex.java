package fr.cda.java.model.util;

import java.util.regex.Pattern;

/**
 * Enum centralisant les expressions régulières utilisées dans l'application.
 */
public enum Regex {

    /**
     * Validation générique d'email (format 99%).
     */
    EMAIL("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),

    /**
     * Code Postal Français (Métropole, 5 chiffres stricts).
     */
    POSTAL_CODE_FR_SIMPLE("^\\d{5}$"),

    /**
     * Code Postal Français (Étendu : inclut 2A, 2B, DOM-TOM).
     */
    POSTAL_CODE_FR_EXTENDED("^((0[1-9])|([1-8]\\d)|(9[0-5])|(9[78]\\d)|(2[ABab]))\\d{3}$"),

    /**
     * Téléphone Français (10 chiffres, accepte 01-07/09, avec séparateurs optionnels).
     */
    PHONE_FR_SIMPLE("^0[1-79]([\\s.-]?\\d{2}){4}$"),

    /**
     * Format E.164 international simplifié (ex: +33123456789).
     */
    PHONE_INTERNATIONAL_E164("^\\+\\d{1,15}$"),

    /**
     * évite les codes postaux fantaisistes (ex: 00000, 99999).
     * vérifie que le département existe (de 01 à 98, incluant Monaco et DOM-TOM).
     */
    CODE_POSTAL_EXISTANT("/^(?:(?:0[1-9]|[1-8]\\d|9[0-5])\\d{3}|97[1-8]\\d{2}|98\\d{2})$/\n");


    // --- Logique de l'Enum ---

    private final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @return La chaîne de caractères (String) du pattern regex.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @return Le Pattern Java pré-compilé pour la performance.
     */
    public Pattern getCompiledPattern() {
        return Pattern.compile(pattern);
    }
}