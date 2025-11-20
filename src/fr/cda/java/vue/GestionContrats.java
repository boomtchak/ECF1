package fr.cda.java.vue;

import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.gestion.Contrat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class GestionContrats extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTable tableauContrats;
    private JButton afficherButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton sauvegarderButton;
    private JPanel blocFormulaire;
    private JButton nouveauButton;
    private Client client;

    public GestionContrats(Client client) {
        this(client, false);
    }

    public GestionContrats(Client client, boolean isAffichage) {
        this.client = client;
        this.setTitle("Gestion des contrats");
        blocFormulaire.setVisible(!isAffichage);
        sauvegarderButton.setVisible(!isAffichage);
            remplissageJTable();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    private void remplissageJTable() {
        DefaultTableModel modelTable = null;
        // 2. Définir les en-têtes
        List<String> entetes = new ArrayList<>();
        entetes.add("Identifiant");
        entetes.add("Client");
        entetes.add("Nom");
        entetes.add("Montant");

        // 3. Initialiser le DefaultTableModel (avec 0 ligne au départ)
        modelTable = new DefaultTableModel(new Object[][]{}, entetes.toArray());

        // 4. Parcourir la liste de clients et ajouter chaque ligne
        for (Contrat contrat : client.getListeContrats()) {
            Object[] dataRow = new Object[]{
                    contrat.getIdentifiant(),
                    client.getRaisonSociale(),
                    contrat.getNomContrat(),
                    contrat.getMontantContrat()
            };
            modelTable.addRow(dataRow);
        }

        // 5. Appliquer le modèle à la table
        tableauContrats.setModel(modelTable);
    }


}
