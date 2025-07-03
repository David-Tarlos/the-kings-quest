package spiel;

import spiel.inventory.Key;
import spiel.inventory.Ruestung;
import spiel.inventory.Weapon;

import java.util.ArrayList;

public class Initalizer {
    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private final ArrayList<Ruestung> ruestung = new ArrayList<>();
    private final ArrayList<Key> key = new ArrayList<>();
    public void initializeItems(){
        weapons.add(new Weapon("Schwert", "Ein scharfes Schwert", 2, false));
        weapons.add(new Weapon("Dolch", "Ein schneller, kleiner Dolch", 2, false));
        weapons.add(new Weapon("Streitkolben", "Ein schwerer Streitkolben",3, false));
        weapons.add(new Weapon("Axt", "Eine grobe Kampf-Axt",  5, false));
        weapons.add(new Weapon("Speer", "Ein langer Speer für den Nahkampf", 4, false));
        weapons.add(new Weapon("Hammer", "Ein schwerer Kriegshammer",4, false));

        ruestung.add(new Ruestung("Lederrüstung", "Eine leichte Lederrüstung für Anfänger", 2, false));
        ruestung.add(new Ruestung("Kettenhemd", "Ein robustes Kettenhemd aus Stahl", 1, false));
        ruestung.add(new Ruestung("Plattenrüstung", "Eine schwere Plattenrüstung für Ritter", 3, false));
        ruestung.add(new Ruestung("Schuppenrüstung", "Eine flexible Schuppenrüstung", 2, false));
        ruestung.add(new Ruestung("Magierrobe", "Eine verzauberte Robe mit Schutz", 2, false));

        key.add(new Key("Key", "Glänzender Schlüssel", false));
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Ruestung> getRuestung() {
        return ruestung;
    }

    public ArrayList<Key> getKey() {
        return key;
    }

    public Initalizer() {
    }
}
