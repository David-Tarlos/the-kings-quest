package spiel.inventory;


public class Weapon extends Item {
    private int schaden;

    public Weapon(String name, String beschreibung, int schaden, boolean isequiped) {
        super(name, beschreibung, isequiped);
        this.schaden = schaden;
    }


    public int getSchaden() {
        return schaden;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    @Override
    public void benutzen(Character spieler) {
    }


}

