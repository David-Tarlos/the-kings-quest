package spiel.inventory;

public class Key extends Item{
    private boolean isKey;

    public Key(String name, String beschreibung, boolean isequiped) {
        super(name, beschreibung, isequiped);
    }

    @Override
    public void benutzen(Character spieler) {

    }
}
