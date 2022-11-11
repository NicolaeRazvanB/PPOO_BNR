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
            if (clientActiv == null) {
                System.out.println("Bine ati venit la Banca Transilvenia");
                System.out.println(
                        "1.Client nou\n" +
                                "2.Autentificare\n" +
                                "3.Inchidere aplicatie\n");
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
                if (optiune.equals("3")) {
                    optiuni.SalvareDateFisier(listaClienti);
                    menuSwitch = false;
                }
            } else {
                System.out.println("Salut " + clientActiv.getNume() + "\n");
                System.out.println(
                        "1.Verifica sold carduri\n" +
                                "2.Extrage/Depune Bani\n" +
                                "3.Descarca extras conturi\n" +
                                "4.Iesire cont\n" +
                                "5.Adaugare card nou\n" +
                                "6.Modificare PIN\n" +
                                "7.Inchidere aplicatie\n");
                optiune = scan.nextLine().trim();

                if (optiune.equals("1")) {
                    optiuni.ArataSolduri(clientActiv, listaClienti);

                }
                if (optiune.equals("2")) {
                    optiuni.Tranzactioneaza(clientActiv, scan, listaClienti);

                }

                if (optiune.equals("3")) {
                    optiuni.DescarcaExtrasFisier(clientActiv, listaClienti);

                }
                if (optiune.equals("4")) {
                    clientActiv = null;

                }
                if (optiune.equals("5")) {
                    optiuni.CreeazaCard(clientActiv, listaClienti);
                }
                if (optiune.equals("6")) {
                    optiuni.ModificaPIN(clientActiv, scan, listaClienti);
                }

                if (optiune.equals("7")) {
                    optiuni.SalvareDateFisier(listaClienti);
                    menuSwitch = false;
                }

            }

        }

    }
}
