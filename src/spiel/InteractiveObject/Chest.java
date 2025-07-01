package spiel.InteractiveObject;

public class Chest extends InteractiveObjects{

    boolean locked;

    public Chest(String id, String name, String beschreibung, int lautstaerke, boolean istGeoeffnet) {
        super(id, name, beschreibung, lautstaerke, istGeoeffnet);
    }

}
