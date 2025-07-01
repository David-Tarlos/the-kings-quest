package spiel.inventory;


public class Waffe extends Item {
    private int schaden;

    public Waffe(String name, String beschreibung, int schaden) {
        super(name, beschreibung);
        this.schaden = schaden;
    }

    public int getSchaden() {
        return schaden;
    }

    @Override
    public void benutzen(Character spieler) {

    }
}

