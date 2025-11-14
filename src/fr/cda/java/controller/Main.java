import fr.cda.java.model.gestion.Client;
import fr.cda.java.model.liste.Clients;
import fr.cda.java.model.util.Adresse;
import org.json.JSONObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        Adresse adresse = new Adresse(
        Client client = new Client(1, "nono",, "0633710842");
        JSONObject son = new JSONObject(Clients.listeClients);

        System.out.println(son);
    }
}
