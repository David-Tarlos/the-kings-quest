package spiel.inventory;

public abstract class Item {
    private String name;
    private String beschreibung;

    public Item(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public abstract void benutzen(Character spieler);
}

