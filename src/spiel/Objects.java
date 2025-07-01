package spiel;

public class Objects {
    private String id;
    private String name;
    private String typ;          // z. B. "Waffe", "Schlüssel", "Loot"
    private String beschreibung;
    private int schaden;         // nur sinnvoll bei Waffen
    private int lautstaerke;     // z. B. Geräusch beim Benutzen
    private boolean aufhebbar;
    private boolean benutzbar;


    public Objects(String id, String name, String typ, String beschreibung, int schaden, int lautstaerke, boolean aufhebbar, boolean benutzbar) {
        this.id = id;
        this.name = name;
        this.typ = typ;
        this.beschreibung = beschreibung;
        this.schaden = schaden;
        this.lautstaerke = lautstaerke;
        this.aufhebbar = aufhebbar;
        this.benutzbar = benutzbar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getSchaden() {
        return schaden;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    public int getLautstaerke() {
        return lautstaerke;
    }

    public void setLautstaerke(int lautstaerke) {
        this.lautstaerke = lautstaerke;
    }

    public boolean isAufhebbar() {
        return aufhebbar;
    }

    public void setAufhebbar(boolean aufhebbar) {
        this.aufhebbar = aufhebbar;
    }

    public boolean isBenutzbar() {
        return benutzbar;
    }

    public void setBenutzbar(boolean benutzbar) {
        this.benutzbar = benutzbar;
    }
}


