package fr.cda.java.vue;

import fr.cda.java.model.util.TypeAction;
import fr.cda.java.model.util.TypeSociete;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class FormulaireSociete extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JPanel paneauProspect;
    private JPanel paneauClient;
    private JPanel erreur;
    private JTextField a06121984TextField;
    private JComboBox interet;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField2;
    private JTextField textField11;
    private JButton voirLesContratsButton;
    TypeSociete typeSociete;
    TypeAction typeAction;


    public FormulaireSociete(TypeSociete typeSociete, TypeAction typeAction) {
        this.typeSociete = typeSociete;
        this.typeAction = typeAction;
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
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void onOK() {

        // La date doit être renseignée et avoir le format «
        //        jj/mm/aaaa ».
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
