package fr.cda.java.vue;

import fr.cda.java.dao.Dao;
import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.gestion.Prospect;
import fr.cda.java.model.gestion.Societe;
import fr.cda.java.model.util.TypeAction;
import fr.cda.java.model.util.TypeSociete;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Acceuil extends JDialog {

    private JPanel contentPane;
    private JButton quitterButton;
    private JButton afficherToutButton;
    private JComboBox selectionSociete;
    private JButton créerButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton afficherButton;
    private JButton gestionDesClientsButton;
    private JButton gestionDesProspectsButton;
    private JLabel description;
    private JPanel paneauSociete;
    private JPanel paneauActionsOnSelection;
    private JButton afficherLesContratsButton;
    private TypeSociete typeSociete
            = null;
    private Societe selectedSociete;
    /**
     * permet de sauvegarder charger
     */
    private static Dao dao = new Dao();

    public Acceuil() {

        this.setTitle("Menu principal");
        setContentPane(contentPane);
        setModal(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
                                               public void actionPerformed(ActionEvent e) {
                                                   onCancel();
                                               }
                                           }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        afficherToutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListeSocietes formulaireSociete = new ListeSocietes(typeSociete);
                formulaireSociete.pack();
                formulaireSociete.setVisible(true);
            }
        });
        gestionDesClientsButton.addActionListener(new ActionListener() {
            @Override
            /**
             * Quand on clic, on passe en TypeSociete = Client
             */
            public void actionPerformed(ActionEvent e) {
                //on utilise pas l'enum le gain est minime.
                description.setText("Gestion des clients");
                typeSociete = TypeSociete.CLIENT;
                paneauSociete.setVisible(true);
                afficherToutButton.setText("Afficher tous les clients");
                activerBoutonAction();
            }
        });
        gestionDesProspectsButton.addActionListener(new ActionListener() {

            @Override
            /**
             * Quand on clic, on passe en TypeSociete = Prospect
             */
            public void actionPerformed(ActionEvent e) {
                description.setText("Gestion des prospects");
                typeSociete = TypeSociete.PROSPECT;
                paneauSociete.setVisible(true);
                afficherToutButton.setText("Afficher tous les prospects");
                activerBoutonAction();
            }
        });
        selectionSociete.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean estActif = (null != selectionSociete.getSelectedItem());
                if (estActif) {
                    if (selectionSociete.getSelectedItem() instanceof Client) {
                        selectedSociete = (Client) selectionSociete.getSelectedItem();
                    }
                    if (selectionSociete.getSelectedItem() instanceof Prospect) {
                        selectedSociete = (Prospect) selectionSociete.getSelectedItem();
                    }
                }
                activerBoutonAction();

            }
        });
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        créerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormulaireSociete formulaireSociete = new FormulaireSociete(typeSociete,
                        TypeAction.CREATE);
                formulaireSociete.pack();
                formulaireSociete.setVisible(true);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args)  {
        try{
            dao.charger();

        } catch (Exception e) {
            System.out.println("attention le chargement a fail");
        }

        try{

        Acceuil dialog = new Acceuil();
        Dimension perfectSize = dialog.getSize();

        // 2. On force la fenêtre à ne JAMAIS être plus petite que cette taille
        dialog.setMinimumSize(perfectSize);

        // 3. (Optionnel) Vous pouvez maintenant cacher votre panneau par défaut
        // La fenêtre ne rétrécira pas.
        dialog.pack();
        dialog.paneauSociete.setVisible(false);
        dialog.setVisible(true);
        System.exit(0);
        }catch (Exception e){

        }finally {
            try{
            dao.Sauvegarder();

            } catch (IOException e) {
                System.out.println("attention la sauvegarde a fail");
            }
        }
    }

    private void activerBoutonAction() {

        boolean estActif = (null != selectionSociete.getSelectedItem());
// Boucle sur tous les composants DANS le panneau
        for (Component comp : paneauActionsOnSelection.getComponents()) {
            comp.setEnabled(estActif);
        }
    }
}
