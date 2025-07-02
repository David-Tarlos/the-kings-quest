package spiel.inventory;

public abstract class Item {
    private String name;
    private String beschreibung;
    private boolean isequiped;


    public Item(String name, String beschreibung, boolean isequiped) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.isequiped = isequiped;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isIsequiped() {
        return isequiped;
    }

    public void setIsequiped(boolean isequiped) {
        this.isequiped = isequiped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public abstract void benutzen(Character spieler);
}



