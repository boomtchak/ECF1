package fr.cda.java.vue;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.RegexException;
import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.gestion.Contrat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GestionContrats extends JDialog {

    private JPanel contentPane;
    private JPanel blocFormulaire;
    private JPanel erreur;
    private JTable tableauContrats;
    private JTextField idTextField;
    private JTextField raisonSocialeTextField;
    private JTextField nomCtTextField;
    private JTextField montantCtTextField;
    ArrayList<JComponent> listeChampsFormulaire = new ArrayList<JComponent>();
    private JButton afficherButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JButton buttonCancel;
    private JButton sauvegarderButton;
    private JLabel erreurLabel;
    private Client client;
    private Contrat selectedContrat;

    public GestionContrats(Client client) {
        this(client, false);

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Contrat contrat : client.getListeContrats()) {
                            if (contrat.getIdentifiant() == getSelectedContrat().getIdentifiant()) {
                                client.getListeContrats().remove(client);
                            }
                        }
                    }
                });
            }
        });
    }


    public GestionContrats(Client client, boolean isAffichage) {
        this.client = client;
        this.setTitle("Gestion des contrats");
        blocFormulaire.setVisible(!isAffichage);
        sauvegarderButton.setVisible(!isAffichage);
        remplissageJTable();
        setContentPane(contentPane);
        setModal(true);
        tableauContrats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (isAffichage) {

            disableFormulaire(true);
        }

        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                afficherErreur("");
                try {
                    Contrat contrat = new Contrat(client.getIdentifiant(),
                            nomCtTextField.getText(), montantCtTextField.getText());
                    client.getListeContrats().add(contrat);
                } catch (MandatoryDataException ex) {
                    afficherErreur(ex.getMessage());
                } catch (RegexException ex) {
                    afficherErreur(ex.getMessage());
                }
            }
        });
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bindingSetters(selectedContrat);
                disableFormulaire(true);
                sauvegarderButton.setEnabled(false);
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        tableauContrats.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {

                // 2. On vérifie si une ligne est bien sélectionnée
                if (tableauContrats.getSelectedRow() != -1) {
                    selectedContrat = getSelectedContrat();
                    disableButtons(false);
                }
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bindingSetters(selectedContrat);
                disableFormulaire(true);
                sauvegarderButton.setEnabled(false);
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private Contrat getSelectedContrat() {
        int viewRow = tableauContrats.getSelectedRow();
        Contrat contrat = client.getListeContrats().get(viewRow);
        return contrat;
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

    private void creerListeComposants() {
        listeChampsFormulaire.add(idTextField);
        listeChampsFormulaire.add(raisonSocialeTextField);
        listeChampsFormulaire.add(nomCtTextField);
        listeChampsFormulaire.add(montantCtTextField);
    }

    /**
     * permet de binder le contenu de mon objet avec les textfields (synchronizeViewToModel)
     */
    private void bindingSetters(Contrat contrat) {

        idTextField.setText(String.valueOf(Contrat.compteurIdentifiant));
        raisonSocialeTextField.setText(client.getRaisonSociale());
        nomCtTextField.setText(contrat.getNomContrat());
        montantCtTextField.setText(contrat.getMontantContrat());
    }

    /**
     *
     */
    private void disableFormulaire(boolean desactive) {
        for (JComponent component : listeChampsFormulaire) {
            component.setEnabled(desactive);

        }
    }

    private void disableButtons(boolean desactive) {
        afficherButton.setEnabled(desactive);
        supprimerButton.setEnabled(desactive);
        modifierButton.setEnabled(desactive);
        sauvegarderButton.setEnabled(desactive);
    }

    void afficherErreur(String message) {
        erreur.setVisible(!message.isEmpty());
        erreurLabel.setText(message);
    }


}
