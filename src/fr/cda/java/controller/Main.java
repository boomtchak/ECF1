import fr.cda.java.Logger.AppLogger;
import fr.cda.java.dao.Dao;
import fr.cda.java.vue.Acceuil;
import java.awt.Dimension;
import java.util.logging.Level;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * sert de sandbox
 */
void main() {
    AppLogger.initFileLogger();

    try {
        Dao.charger();

    } catch (Exception e) {
        AppLogger.LOGGER.severe("le chargement a rencontré un problème non anticipé");
    }

    try {

        Acceuil dialog = new Acceuil();
        Dimension perfectSize = dialog.getSize();

        // 2. On force la fenêtre à ne JAMAIS être plus petite que cette taille
        dialog.setMinimumSize(perfectSize);

        // 3. (Optionnel) Vous pouvez maintenant cacher votre panneau par défaut
        // La fenêtre ne rétrécira pas.
        dialog.pack();

        dialog.getPaneauSociete().setVisible(false);
        dialog.setVisible(true);
        Dao.sauvegarder();
        System.exit(0);
    } catch (Exception e) {

    } finally {
        try {
            Dao.sauvegarder();

        } catch (IOException e) {
            AppLogger.LOGGER.severe("la sauvegarde a rencontré un problème non anticipé");
        }
    }
}
