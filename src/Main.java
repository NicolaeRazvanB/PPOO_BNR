import entitati.ClientCollection;
import entitati.ExtractorDateText;


public class Main {

    public static void main(String[] args) throws Exception {
        ClientCollection listaClienti = new ExtractorDateText().extrageDate();
        System.out.println(listaClienti);

    }
}
