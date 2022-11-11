import entitati.Client;
import entitati.ClientCollection;
import entitati.ExtractorDateText;
import entitati.Optiuni;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        ClientCollection listaClienti = new ExtractorDateText().extrageDate();
        Scanner scan = new Scanner(System.in);
        Optiuni optiuni = new Optiuni();
        boolean menuSwitch = true;
        String optiune;
        Client clientActiv = null;
        while (menuSwitch) {
            System.out.println("Bine ati venit la Banca Transilvenia");
            optiune = scan.nextLine().trim();
            if (optiune.equals("1")) {
                optiuni.Inregistrare(scan, listaClienti);
            }
            if (optiune.equals("2")) {
                System.out.println("Meniu autentificare:\n");
                String nume, parola = "";
                System.out.println("Introduceti nume");
                nume = scan.nextLine();
                System.out.println("Introduceti parola");
                parola = scan.nextLine();
                clientActiv = new Client(optiuni.Autentificare(nume, parola, listaClienti));
            }
            if (optiune.equals("0")) {
                optiuni.SalvareDateFisier(listaClienti);
                menuSwitch = false;
            }


        }

    }
}
