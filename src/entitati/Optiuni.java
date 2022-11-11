package entitati;

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
        //Salvare date la inchidere
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

}
