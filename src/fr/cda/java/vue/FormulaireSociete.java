package fr.cda.java.vue;

import fr.cda.java.Exceptions.MandatoryDataException;
import fr.cda.java.Exceptions.RegexException;
import fr.cda.java.Exceptions.UniciteException;
import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.gestion.Prospect;
import fr.cda.java.model.gestion.Societe;
import fr.cda.java.model.liste.Clients;
import fr.cda.java.model.util.Adresse;
import fr.cda.java.model.util.Interet;
import fr.cda.java.model.util.TypeAction;
import fr.cda.java.model.util.TypeSociete;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormulaireSociete extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea comTextField;
    private JPanel paneauProspect;
    private JPanel paneauClient;
    private JPanel erreur;
    private JComboBox interetCombo;
    private JTextField numRueTextField;
    private JTextField nomRueTextField;
    private JTextField cpTextField;
    private JTextField villeTextField;
    private JTextField telTextField;
    private JTextField mailTextField;
    private JTextField nbEmployeTextField;
    private JTextField chiffreAffaireTextField;
    private JTextField raisonSocialeTextField;
    private JTextField idTextField;
    private JButton voirLesContratsButton;
    private JPanel blocFormulaireComplet;
    private JTextField dateProspecTextField;
    private JLabel erreurLabel;
    TypeSociete typeSociete;
    TypeAction typeAction;
    Societe societe;

    public FormulaireSociete(TypeSociete typeSociete, TypeAction typeAction) {
        this(typeSociete, typeAction, null);
    }

    public FormulaireSociete(TypeSociete typeSociete, TypeAction typeAction, Societe client) {
        this.typeSociete = typeSociete;
        this.typeAction = typeAction;
        this.societe = client;
        this.setTitle(new StringBuilder(typeAction.toString()).append(typeSociete.getAffichage())
                .toString());
        voirLesContratsButton.setVisible(!typeAction.equals(TypeAction.CREATE));
        if (typeSociete.equals(TypeSociete.PROSPECT)) {
            voirLesContratsButton.setVisible(false);
            for (Interet interet : Interet.values()) {
                interetCombo.addItem(interet);
            }
        }
        disableFormulaire();
        if (typeAction.equals(TypeAction.UPDATE) || typeAction.equals(TypeAction.AFFICHER)) {
            bindingSetters();
        } else {
            interetCombo.setSelectedItem(Interet.INCONNU);
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setTitle(
                new StringBuilder(typeAction.toString()).append(typeSociete.toString()).toString());
        paneauProspect.setVisible(typeSociete.equals(TypeSociete.PROSPECT));
        paneauClient.setVisible(typeSociete.equals(TypeSociete.CLIENT));

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

        voirLesContratsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionContrats gestionContrats = new GestionContrats((Client) societe,
                        typeAction.equals(TypeAction.AFFICHER));
                gestionContrats.pack();
                gestionContrats.setVisible(true);

            }
        });
    }

    /**
     * sauvegarde. peut être amélioré.
     */
    private void onOK() {
        String erreurDetectee = "";
        LocalDate date = null;
        Long chiffreAffaire;
        int nbEmploye;
        // ce sont des regles d'affichage, pas des regles métier.
        try {
            chiffreAffaire = Long.parseLong(chiffreAffaireTextField.getText());
        } catch (NumberFormatException e) {
            nbEmploye = Integer.parseInt(nbEmployeTextField.getText());
            erreurDetectee = "Le nombre d'employés doit être un chiffre";
        }
        try {
        } catch (NumberFormatException e) {
            erreurDetectee = "Le chiffre d'affaire doit être un chiffre";
        }
        try {
            date = LocalDate.parse(dateProspecTextField.getText(),
                    DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        } catch (DateTimeParseException e) {
            erreurDetectee = "La date affichee ne respecte pas les standards (JJ/MM/AAAA)";
        }

        if (erreurDetectee.isEmpty()) {
            try {
                if (typeSociete.equals(TypeSociete.PROSPECT)) {
                    Prospect prospect = (Prospect) getSociete();

                    prospect.setRaisonSociale(raisonSocialeTextField.getText());
                    prospect.setDateProspection(date);
                    prospect.setInteret((Interet) interetCombo.getSelectedItem());
                    if (typeAction.equals(TypeAction.UPDATE)) {

                        typeSociete.getListeSocietes()
                                .replace(societe.getRaisonSociale(), prospect);
                    } else {
                        prospect.setIdentifiant(societe.getIdentifiant());
                        typeSociete.getListeSocietes().put(societe.getRaisonSociale(), prospect);
                    }
                } else {

                    Client client = (Client) getSociete();
                    client.setRaisonSociale(raisonSocialeTextField.getText());
                    client.setNombreEmployes(Integer.parseInt(nbEmployeTextField.getText()));
                    client.setChiffreAffaire(Long.parseLong(chiffreAffaireTextField.getText()));
                    // les listes gerent toutes seul l'auto incrément uniquement en create.
                    if (!typeAction.equals(TypeAction.UPDATE)) {
                        client.setIdentifiant(societe.getIdentifiant());
                    } else {
                        typeSociete.getListeSocietes().replace(societe.getRaisonSociale(), client);
                    }


                }
            } catch (
                    DateTimeParseException e) {
                afficherErreur(e.getMessage());
            } catch (
                    MandatoryDataException e) {
                afficherErreur(e.getMessage());
            } catch (
                    RegexException e) {
                afficherErreur(e.getMessage());
            } catch (
                    UniciteException e) {
                afficherErreur(e.getMessage());
            }
        } else {
            afficherErreur(erreurDetectee);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * permet de binder le contenu de mon objet avec les textfields (synchronizeViewToModel)
     */
    private void bindingSetters() {
        if (typeSociete.equals(TypeSociete.CLIENT)) {
            Client client = (Client) societe;
            nbEmployeTextField.setText(String.valueOf(client.getNombreEmployes()));
            chiffreAffaireTextField.setText(String.valueOf(client.getChiffreAffaire()));
        } else if (typeSociete.equals(TypeSociete.PROSPECT)) {
            Prospect prospect = (Prospect) societe;
            dateProspecTextField.setText(prospect.getDateProspection()
                    .format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
            interetCombo.setSelectedItem(prospect.getInteret());

        }
        telTextField.setText(societe.getTelephone());
        numRueTextField.setText(societe.getAdresse().getNumeroDeRue());
        nomRueTextField.setText(societe.getAdresse().getNomDeRue());
        cpTextField.setText(societe.getAdresse().getCodePostal());
        villeTextField.setText(societe.getAdresse().getVille());
        mailTextField.setText(societe.getAdresseMail());
        raisonSocialeTextField.setText(societe.getRaisonSociale());
        idTextField.setText(String.valueOf(societe.getIdentifiant()));
        comTextField.setText(societe.getCommentaire());
    }


    /**
     *
     * @return
     * @throws MandatoryDataException
     * @throws RegexException
     * @throws UniciteException
     */
    private Societe getSociete()  throws MandatoryDataException,RegexException, UniciteException{
        Adresse adresse = new Adresse(numRueTextField.getText(), nomRueTextField.getText(),
                cpTextField.getText(), villeTextField.getText());
        Societe retour = new Societe(adresse, mailTextField.getText(), telTextField.getText(),

                comTextField.getText());
        return retour;
    }

    /**
     * TODO : mettre une liste de composant boucler dessus.
     */
    private void disableFormulaire() {
        dateProspecTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        interetCombo.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        numRueTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        nomRueTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        cpTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        villeTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        telTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        mailTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        nbEmployeTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        chiffreAffaireTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        raisonSocialeTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        idTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        idTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
        comTextField.setEnabled(!typeAction.equals(TypeAction.AFFICHER));
    }

    void afficherErreur(String message) {
        erreur.setVisible(!message.isEmpty());
        erreurLabel.setText(message);
    }

}
