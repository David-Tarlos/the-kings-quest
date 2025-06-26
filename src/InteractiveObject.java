public abstract class InteractiveObject {
    private String id;               // z. B. "truhe_1"
    private String name;             // z. B. "Alte Truhe"
    private String beschreibung;     // Beschreibung für den Spieler
    private int lautstaerke;         // Wie laut ist das Öffnen (1–10)
    private boolean istGeoeffnet;
    // Ob bereits geöffnet

    public InteractiveObject(String id, String name, String beschreibung, int lautstaerke, boolean istGeoeffnet) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.lautstaerke = lautstaerke;
        this.istGeoeffnet = istGeoeffnet;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getLautstaerke() {
        return lautstaerke;
    }

    public void setLautstaerke(int lautstaerke) {
        this.lautstaerke = lautstaerke;
    }

    public boolean isIstGeoeffnet() {
        return istGeoeffnet;
    }

    public void setIstGeoeffnet(boolean istGeoeffnet) {
        this.istGeoeffnet = istGeoeffnet;
    }
}


