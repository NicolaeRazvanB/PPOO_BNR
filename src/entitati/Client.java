package entitati;

import java.util.ArrayList;

public class Client {
    private String nume;
    private String parola;
    private ArrayList<Card> listaCarduri = new ArrayList<>();

    public Client(String nume, String parola, ArrayList<Card> listaCarduri) {
        this.nume = nume;
        this.parola = parola;
        this.listaCarduri = new ArrayList<>(listaCarduri.size());
        for (Card card : listaCarduri) {
            this.listaCarduri.add(new Card(card));
        }
    }

    public Client(Client client) {
        this.nume = client.getNume();
        this.parola = client.getParola();
        this.listaCarduri = new ArrayList<>(client.getListaCarduri().size());
        for (Card card : client.getListaCarduri()) {
            this.listaCarduri.add(new Card(card));
        }
    }

    public Client(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public ArrayList<Card> getListaCarduri() {
        return listaCarduri;
    }

    public void setListaCarduri(ArrayList<Card> listaCarduri) {
        this.listaCarduri = new ArrayList<>(listaCarduri.size());
        for (Card card : listaCarduri) {
            this.listaCarduri.add(new Card(card));
        }
    }

    @Override
    public String toString() {
        StringBuilder carduri = new StringBuilder();
        for (Card card : this.getListaCarduri()) {
            carduri.append(card.toString());
        }
        return "Nume:" + nume + '\n' +
                "Parola:" + parola + '\n' +
                "Carduri:" + '\n' + carduri
                ;
    }

    public void AdaugaCard(Card card) {
        this.getListaCarduri().add(card);
    }
}
