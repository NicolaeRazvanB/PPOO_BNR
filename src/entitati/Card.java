package entitati;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Card {
    Map<LocalDateTime, Float> extrasCont = new HashMap<>();
    private String IBAN;
    private Float sold;
    private String PIN;

    public Card() {
        PIN = "0000";
        this.IBAN = genereazaIBAN();
        this.sold = (float) 0;
    }

    public Card(String IBAN, Float sold, HashMap<LocalDateTime, Float> extrasCont, String pin) {
        this.IBAN = IBAN;
        this.sold = sold;
        this.PIN = pin;
        this.extrasCont.putAll(extrasCont);
    }

    public Card(Card card) {
        this.IBAN = card.getIBAN();
        this.sold = card.getSold();
        this.PIN = card.getPIN();
        this.extrasCont.putAll(card.extrasCont);
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Float getSold() {
        return sold;
    }

    public void setSold(Float sold) {
        this.sold = sold;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public void realizeazaTranzactie(Float suma) throws InterruptedException {
        setSold(this.getSold() + suma);
        this.extrasCont.put(java.time.LocalDateTime.now(), suma);
        Thread.sleep(200);
    }

    public String genereazaIBAN() {
        Random random = new Random();
        StringBuilder IBAN = new StringBuilder("RO" + random.nextInt(10, 99) + "BTRL");
        for (int i = 0; i < 16; i++) IBAN.append(random.nextInt(9));
        return IBAN.toString();
    }


    @Override
    public String toString() {
        return "IBAN:" + IBAN + '\n' +
                "Sold:" + sold + '\n' +
                "PIN:" + getPIN() + '\n' +
                "extrasCont:" + '\n' + extrasCont.toString() + '\n';
    }
}
