package entitati;

import Exceptii.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ExtractorDateText {
    public ExtractorDateText() {
    }

    public ClientCollection extrageDate() throws Exception {
        ClientCollection containerClienti = ClientCollection.getInstance();
        try {
            File fisierIntrare = new File("date.txt");
            Scanner myReader = new Scanner(fisierIntrare);
            boolean checkNewClient = false;
            String dataBackUp = "";
            while (myReader.hasNextLine()) {
                String data;
                if (!checkNewClient)
                    data = myReader.nextLine();
                else
                    data = dataBackUp;
                String nume, parola, IBAN, sold, extras, pereche, pin;
                String[] perechi, cheieValoare;
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime dateTime;
                HashMap<LocalDateTime, Float> myMap = new HashMap<>();
                ArrayList<Card> listaCarduri = new ArrayList<>();
                Client client;

                if (data.startsWith("Nume:"))
                    nume = data.substring(5).trim();
                else throw new ExceptieFormatFisierNume();
                if (!nume.isEmpty())
                    data = myReader.nextLine();

                if (data.startsWith("Parola:"))
                    parola = data.substring(7).trim();
                else throw new ExceptieFormatFisierParola();
                if (!parola.isEmpty())
                    data = myReader.nextLine();

                if (data.startsWith("Carduri:"))
                    data = myReader.nextLine();
                else throw new ExceptieFormatFisierGenerica();
                while ((data.startsWith("IBAN") ||
                        data.startsWith("Sold") ||
                        data.startsWith("extrasCont") ||
                        data.startsWith("{") ||
                        data.startsWith("PIN"))
                        && myReader.hasNextLine()) {
                    if (data.startsWith("IBAN:"))
                        IBAN = data.substring(5).trim();
                    else throw new ExceptieFormatFisierIBAN();
                    if (!IBAN.isEmpty())
                        data = myReader.nextLine();

                    if (data.startsWith("Sold:"))
                        sold = data.substring(5).trim();
                    else throw new ExceptieFormatFisierSold();
                    if (!sold.isEmpty())
                        data = myReader.nextLine();

                    if (data.startsWith("PIN:"))
                        pin = data.substring(4).trim();
                    else throw new ExceptieFormatFisierPIN();
                    if (!pin.isEmpty())
                        data = myReader.nextLine();

                    if (data.startsWith("extrasCont:"))
                        data = myReader.nextLine();
                    else throw new ExceptieFormatFisierExtras();
                    if (data.startsWith("{")) {
                        extras = data.substring(1, data.length() - 1);
                        perechi = extras.split(",");
                        for (String s : perechi) {
                            pereche = s;
                            cheieValoare = pereche.split("=");
                            dateTime = LocalDateTime.parse(cheieValoare[0].trim(), formatter);
                            myMap.put(dateTime, Float.valueOf(cheieValoare[1].trim()));
                        }
                        if (myReader.hasNextLine()) {
                            data = myReader.nextLine();
                            if (data.startsWith("Nume")) {
                                checkNewClient = true;
                                dataBackUp = data;
                            } else
                                checkNewClient = false;
                        }
                    } else throw new ExceptieFormatFisierExtras();
                    Card temporar = new Card(IBAN, Float.valueOf(sold), myMap, pin);
                    listaCarduri.add(temporar);


                }
                client = new Client(nume, parola, listaCarduri);
                containerClienti.AdaugaClient(client);

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fisierul cu date de intrare nu a fost gasit!");
            e.printStackTrace();
        }

        return containerClienti;
    }
}
