package fr.cda.java.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


/**
 * Animaux
 *
 * <p>Description : Gestion des logs dans un fichier et pas dans la console.</p>
 *
 * @author CDA-09
 * @version 1.0
 * @since 30/10/2025
 */
public class AppLogger {

    /**
     *
     * Logger principal de l'application
     */
    public static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());

    /**
     *
     * Gestionnaire de fichier pour le log
     */
    private static FileHandler fileHandler = null;

    // Initialisation du logger
    public static void initFileLogger() {
        try {
            // Création du FileHandler
            fileHandler = new FileHandler("application.log", true);

            // Empêche l'affichage du log dans la console
            LOGGER.setUseParentHandlers(false);

            // Ajoute le gestionnaire de fichier au logger
            LOGGER.addHandler(fileHandler);

            // Attache un formateur personnalisé au gestionnaire
            fileHandler.setFormatter(new AppLogFormatter());

        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation du logger : " + e.getMessage());
        }
    }
}

