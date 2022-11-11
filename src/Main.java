import entitati.ClientCollection;
import entitati.ExtractorDateText;

import java.io.FileWriter;


public class Main {

    public static void main(String[] args) throws Exception {
        ClientCollection listaClienti = new ExtractorDateText().extrageDate();


        //Salvare date la inchidere
        FileWriter actualizatorDate = new FileWriter("date.txt");
        actualizatorDate.write(listaClienti.toString());
        actualizatorDate.close();
        System.out.println("Fisiere de date actualizate cu succes");
    }
}
