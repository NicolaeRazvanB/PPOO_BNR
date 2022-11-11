package entitati;

import com.sun.jdi.IntegerValue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Optiuni {
    public Optiuni() {
    }

    public void Inregistrare(Scanner scan, ClientCollection listaClienti) {
        String nume = "", parola = "";
        System.out.println("Pentru a te inregistra introduceti-va numele si parola");
        System.out.println("Introduceti numele:");
        String input = scan.nextLine();
        if (input.trim().matches("^[a-zA-Z]+$")) {
            nume = input.trim();
            System.out.println("Nume valid.Introduceti parola: ");
        }
        input = scan.nextLine();
        if (input.length() > 5) {
            parola = input;

            System.out.println("Parola valida");
        }

        Client clientNou = new Client(nume, parola);
        listaClienti.getListaClienti().add(clientNou);
        System.out.println("Inregistrarea s-a realizat cu succes");

    }

    public void SalvareDateFisier(ClientCollection listaClienti) throws IOException {
        FileWriter actualizatorDate = new FileWriter("date.txt");
        actualizatorDate.write(listaClienti.toString());
        actualizatorDate.close();
        System.out.println("Fisiere de date actualizate cu succes");
    }

    public Client Autentificare(String nume, String parola, ClientCollection listaClienti) {
        Client match = null;
        for (Client client : listaClienti.getListaClienti()) {
            if (client.getNume().equals(nume) && client.getParola().equals(parola))
                match = new Client(client);
        }
        System.out.println("Autentificare realizata cu succes\n");
        return match;
    }

    public void ArataSolduri(Client client, ClientCollection listaClienti) {
        for (Client clientIterat : listaClienti.getListaClienti()
        ) {
            if (clientIterat.getNume().equals(client.getNume())) {
                for (Card card : clientIterat.getListaCarduri()) {
                    System.out.println("IBAN: " + card.getIBAN());
                    System.out.println("Sold: " + card.getSold() + "\n");
                }
            }
        }

    }

    public void DescarcaExtrasFisier(Client client, ClientCollection listaClienti) throws IOException {
        for (Client clientIterat : listaClienti.getListaClienti()
        ) {
            if (clientIterat.getNume().equals(client.getNume())) {
                FileWriter descarcatorExtrase = new FileWriter("extrase.txt");
                descarcatorExtrase.write(clientIterat.getListaCarduri().toString().substring(1, clientIterat.getListaCarduri().toString().length() - 1));
                descarcatorExtrase.close();
                System.out.println("Extras de cont descarcat cu succes");
            }
        }

    }

    public void CreeazaCard(Client client, ClientCollection listaClienti) {
        Card card = new Card();
        for (Client clientIterat : listaClienti.getListaClienti()
        ) {
            if (clientIterat.getNume().equals(client.getNume())) {
                clientIterat.getListaCarduri().add(card);
                System.out.println("Card adaugat cu succes");
            }
        }

    }

    public void ModificaPIN(Client client, Scanner scan, ClientCollection listaClienti) {
        System.out.println("Alegeti cardul");
        int i = 1;

        for (Client clientIterat : listaClienti.getListaClienti()
        ) {

            if (clientIterat.getNume().equals(client.getNume())) {
                for (Card card : clientIterat.getListaCarduri()) {
                    System.out.println(i + " .IBAN: " + card.getIBAN() + "Sold: " + card.getSold() + "\n");
                    i++;

                }
                int opt = Integer.parseInt(scan.nextLine().trim());
                System.out.println("Introduceti noul PIN");
                String PIN = scan.nextLine();
                clientIterat.getListaCarduri().get(opt - 1).setPIN(PIN);
                System.out.println("PIN schimbat cu succes");

            }
        }
    }

    public void Tranzactioneaza(Client client, Scanner scan, ClientCollection listaClienti) throws InterruptedException {
        System.out.println("Alegeti cardul");
        int i = 1;

        for (Client clientIterat : listaClienti.getListaClienti()
        ) {

            if (clientIterat.getNume().equals(client.getNume())) {
                for (Card card : clientIterat.getListaCarduri()) {
                    System.out.println(i + " .IBAN: " + card.getIBAN() + "Sold: " + card.getSold() + "\n");
                    i++;

                }
                int opt = Integer.parseInt(scan.nextLine().trim());
                System.out.println("Introduceti valoarea");
                String valoare = scan.nextLine();
                clientIterat.getListaCarduri().get(opt - 1).realizeazaTranzactie(Float.parseFloat(valoare));
                System.out.println(clientIterat);
                System.out.println("Tranzactie realizata cu succes");

            }
        }

    }
}


