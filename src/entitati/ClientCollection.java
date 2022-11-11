package entitati;

import java.util.ArrayList;

public class ClientCollection {
    private static ClientCollection instance = null;

    private ArrayList<Client> listaClienti = new ArrayList<>();

    private ClientCollection() throws Exception {
        if (instance != null)
            throw new Exception("Obiect deja instantiat!");
    }

    public static ClientCollection getInstance() throws Exception {
        if (instance == null) {
            instance = new ClientCollection();
        }
        return instance;
    }

    public void AdaugaClient(Client client) {
        this.listaClienti.add(client);
    }

    public void StergeClient(Client client) {
        this.listaClienti.remove(client);
    }

    public ArrayList<Client> getListaClienti() {
        return listaClienti;
    }

    @Override
    public String toString() {
        StringBuilder clienti = new StringBuilder();
        for (Client client : this.getListaClienti()) {
            clienti.append(client.toString());
        }
        return clienti.toString();
    }
}
