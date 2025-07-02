package spiel.quests;

import spiel.Character;
import spiel.InteractiveObject.*;
import spiel.inventory.*;

import java.lang.reflect.Array;
import java.util.*;

public class Quest1 {

    private boolean isPrincesSaved = false;
    private final boolean[] wasAlreadyInRoom = new boolean[12];
    private boolean canGoNorth = false;
    private boolean canGoSouth = false;
    private boolean canGoWest = false;
    private boolean canGoEast = false;

    private final int[][] gameField;

    private static final int ENTRANCE = 0;
    private static final int NO_ROOM = -1;

    private boolean wasAlreadyInRoom1;
    private boolean wasAlreadyInRoom2;
    private boolean wasAlreadyInRoom3;
    private boolean wasAlreadyInRoom4;
    private boolean wasAlreadyInRoom5;
    private boolean wasAlreadyInRoom6;
    private boolean wasAlreadyInRoom7;
    private boolean wasAlreadyInRoom8;
    private boolean wasAlreadyInRoom9;
    private boolean wasAlreadyInRoom10;
    private boolean wasAlreadyInRoom11;


    private static final int ROOM1 = 1;
    private static final int ROOM2 = 2;
    private static final int ROOM3 = 3;
    private static final int ROOM4 = 4;
    private static final int ROOM5 = 5;
    private static final int ROOM6 = 6;
    private static final int ROOM7 = 7;
    private static final int ROOM8 = 8;
    private static final int ROOM9 = 9;
    private static final int ROOM10 = 10;
    private static final int ROOM11 = 11;

    private final Map<Integer, List<InteractiveObjects>> objectsInRooms = new HashMap<>();
    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private final ArrayList<Ruestung> ruestung = new ArrayList<>();
    private final ArrayList<Key> key = new ArrayList<>();
    private int x, y;
    private boolean justInteracted = false;
    public ArrayList<String> inventory = new ArrayList<>();

    public Quest1(int[][] gameField) {
        this.gameField = gameField;
    }

    public void startQuest() {
        int[][] initial = {
                {NO_ROOM, ROOM4,   NO_ROOM, ROOM5,  ROOM6, ROOM7},
                {NO_ROOM, NO_ROOM, ROOM3,   ROOM4,  NO_ROOM, ROOM8},
                {NO_ROOM, ROOM2,   ROOM1,   NO_ROOM, ROOM10, ROOM9},
                {NO_ROOM, NO_ROOM, ENTRANCE, NO_ROOM, ROOM11, NO_ROOM}
        };

        for (int i = 0; i < gameField.length; i++) {
            System.arraycopy(initial[i], 0, gameField[i], 0, gameField[i].length);
        }

        x = 2;
        y = 3;

        Character character = new Character(0, 10);

        initializeInteractiveObjects();
        initializeItems();

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            enemyDecision(character);
            if (!justInteracted) {
                description();
            }
            justInteracted = false;
            directions();
            playing = decision(scanner, character);


            System.out.println();
        }

        nextQuest();
        System.out.println("Wieso gibt du auf mein Freund");
        scanner.close();
    }


    public void nextQuest(){
        //TODO
    }

    public void enemyDecision(Character character) {
        int noise = character.getNoiseLevel();
        if (noise >= 5) {
            enemyInteraction();
        }
    }

    public void enemyInteraction() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Me: Ich höre jemanden... Die Schritte kommen näher. Wahrscheinlich ein Soldat!");
        System.out.println("Was machst du?");
        System.out.println("1. Verstecken");
        System.out.println("2. Ruhig bleiben und nichts tun");

        int choice = 0;
        while (choice != 1 && choice != 2) {
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte 1 oder 2 eingeben.");
                scanner.next();
            }
        }

        int chance = random.nextInt(100);

        if (choice == 1) {

            if (chance < 70) {
                System.out.println("Du hast dich erfolgreich versteckt. Der Soldat hat dich nicht bemerkt.");
            } else {
                System.out.println("Du wurdest entdeckt! Der Soldat greift dich an.");
                // TODO: Kampf starten
            }
        } else {

            if (chance < 40) {
                System.out.println("Du bleibst ruhig – und der Soldat geht an dir vorbei, ohne dich zu sehen.");
            } else {
                System.out.println("Der Soldat bemerkt dich sofort! Er greift an.");
                // TODO: Kampf starten
            }
        }
    }


    public void description() {
        int currentRoom = gameField[y][x];


        if (currentRoom >= 0 && currentRoom < wasAlreadyInRoom.length && wasAlreadyInRoom[currentRoom]) {
            return;
        }

        // Markieren dass der Spieler jetzt in diesem Raum war
        if (currentRoom >= 0 && currentRoom < wasAlreadyInRoom.length) {
            wasAlreadyInRoom[currentRoom] = true;
        }

        switch (currentRoom) {
            case ENTRANCE:
                System.out.println("Description: Du stehst am Eingang des Schlosses. Es ist still und dunkel. Die Landschaft sagt mir dass alles in Ordnung ist, aber wenn ich dieses Schloss ansehe voller Spinnweben, es ist schon so, als wäre hier kein Mensch. Ich höre ein Plötzliches weinen. Es war eine Stimme eines Fraues. Es müsste die Prinzessin sein, dann bin ich doch nicht falsch");
                break;
            case ROOM1:
                System.out.println("Description: Es ist sehr spuklich. Es ist ein langer Gang vor dir und siehst das andere Ende nicht. Rechts hinter der Tür eine versteckte alte Truhe.  Links gerade neben der Eingangstür ist aber eine Tür, die so schreint als wäre es offen. ");
                break;
            case ROOM2:
                System.out.println("Description: Dies ist ein kleiner Raum, mit einem Bett, Tisch und einem Schrank.");
                break;
            case ROOM3:
                System.out.println("Description: Ein langer, düsterer Korridor. Hier scheint es zu spuken. Rechts ist ein mittelgrosser Raum. ");
                break;
            case ROOM5:
                System.out.println("Description: Ein Raum voller alter Gemälde.");
                break;
            case ROOM6:
                System.out.println("Description: Hier hört man das leise Tropfen von Wasser.");
                break;
            case ROOM7:
                System.out.println("Description: Ein Raum, der nach alten Büchern riecht.");
                break;
            case ROOM8:
                System.out.println("Description: Ein verborgener Raum hinter einer Geheimtür.");
                break;
            case ROOM9:
                System.out.println("Description: Der Raum ist mit Staub bedeckt und wirkt verlassen.");
                break;
            case ROOM10:
                System.out.println("Description: Ein Raum voller mystischer Symbole an den Wänden.");
                break;
            case ROOM11:
                System.out.println("Description: Dies ist der Thronsaal, prachtvoll und imposant.");
                break;
            default:
                System.out.println("Du befindest dich an einem unbekannten Ort.");
                break;
        }
    }

    public void directions() {
        canGoNorth = false;
        canGoSouth = false;
        canGoWest = false;
        canGoEast = false;

        //Norden
        if (y > 0) {
            int above = gameField[y - 1][x];

            if (above == ROOM3) {
                System.out.println("Direction (Norden): Es gibt einen sehr langen Gang, der so aussieht als würde der ins nichts führen");
                canGoNorth = true;
            } else if (above == ENTRANCE) {
                System.out.println("Direction (Norden): Die Ausgangstür befindet sich Richtung Norden");
                canGoNorth = true;
            } else if (above >= ROOM1 && above <= ROOM11) {
                System.out.println("Direction (Norden): Es gibt eine Tür Richtung Norden.");
                canGoNorth = true;
            }
        }

        //Süden
        if (y < gameField.length - 1) {
            int below = gameField[y + 1][x];

            if (below >= ROOM1 && below <= ROOM11 || below == ENTRANCE) {
                System.out.println("Direction (Süden): Es gibt eine Tür Richtung Süden.");
                canGoSouth = true;
            }
        }

        // Westen
        if (x > 0) {
            int left = gameField[y][x - 1];

            if (left >= ROOM1 && left <= ROOM11 || left == ENTRANCE) {
                System.out.println("Direction (Westen): Es gibt eine Tür Richtung Westen.");
                canGoWest = true;
            }
        }

        // Osten
        if (x < gameField[y].length - 1) {
            int right = gameField[y][x + 1];

            if (right >= ROOM1 && right <= ROOM11 || right == ENTRANCE) {
                System.out.println("Direction (Osten): Es gibt eine Tür Richtung Osten.");
                canGoEast = true;
            }
        }
    }


    public boolean decision(Scanner scanner, Character character) {
        int currentRoom = gameField[y][x];
        List<InteractiveObjects> objectsHere = objectsInRooms.get(currentRoom);

        List<String> actions = new ArrayList<>();
        List<InteractiveObjects> availableObjects = new ArrayList<>();
        
        if (objectsHere != null && !objectsHere.isEmpty()) {
            for (InteractiveObjects obj : objectsHere) {
                if (!obj.isIstGeoeffnet()) {
                    actions.add("Untersuche " + obj.getName());
                    availableObjects.add(obj);
                }
            }
        }

        // Richtungen hinzufügen
        if (canGoNorth) actions.add("Gehe nach Norden");
        if (canGoSouth) actions.add("Gehe nach Süden");
        if (canGoWest)  actions.add("Gehe nach Westen");
        if (canGoEast)  actions.add("Gehe nach Osten");

        if (actions.isEmpty()) {
            System.out.println("Keine verfügbaren Aktionen!");
            return true;
        }

        System.out.println("Was möchtest du tun?");
        for (int i = 0; i < actions.size(); i++) {
            System.out.print((i + 1) + ". " + actions.get(i) + "   ");
        }
        System.out.println();

        int input = scanner.nextInt();
        if (input < 1 || input > actions.size()) {
            System.out.println("Ungültige Eingabe!");
            return true;
        }

        String selectedAction = actions.get(input - 1);

        if (selectedAction.startsWith("Untersuche")) {
            String objectName = selectedAction.substring("Untersuche ".length());
            InteractiveObjects targetObject = null;

            for (InteractiveObjects obj : availableObjects) {
                if (obj.getName().equals(objectName)) {
                    targetObject = obj;
                    break;
                }
            }

            if (targetObject != null) {
                interactWithObject(targetObject, scanner, character);
                justInteracted = true;
            }
        } else if (selectedAction.contains("Norden")) {
            y--;
        } else if (selectedAction.contains("Süden")) {
            y++;
        } else if (selectedAction.contains("Westen")) {
            x--;
        } else if (selectedAction.contains("Osten")) {
            x++;
        }
        return true;
    }

    public void interactWithObject(InteractiveObjects obj, Scanner scanner, Character character) {
        System.out.println("Du untersuchst: " + obj.getName());
        obj.setIstGeoeffnet(true);
        System.out.println(obj.getBeschreibung());

        int lautstaerke = obj.getLautstaerke();
        if (lautstaerke == 0 || lautstaerke == 2 || lautstaerke == 4 || lautstaerke == 5 || lautstaerke == 6 || lautstaerke == 8) {
            System.out.println("Nein!! es war zu laut, ich muss aufpassen, nicht dass ich bemerkt werde und von Soldaten angegriffen werde");
            character.setNoiseLevel(character.getNoiseLevel() + 1);
        }

        System.out.println("Einen Gegenstand in der Kiste gefunden");

        Random random = new Random();
        int chance = random.nextInt(100);

        Object item = null;


        if (chance < 60 && !key.isEmpty()) {
            int itemIndex = random.nextInt(key.size());
            item = key.get(itemIndex);
            key.remove(itemIndex);
        } else if (chance < 80 && !weapons.isEmpty()) {
            int itemIndex = random.nextInt(weapons.size());
            item = weapons.get(itemIndex);
            weapons.remove(itemIndex);
        } else if (!ruestung.isEmpty()) {
            int itemIndex = random.nextInt(ruestung.size());
            item = ruestung.get(itemIndex);
            ruestung.remove(itemIndex);
        } else if (!weapons.isEmpty()) {
            int itemIndex = random.nextInt(weapons.size());
            item = weapons.get(itemIndex);
            weapons.remove(itemIndex);
        }

        if (item != null) {
            System.out.println("Gefundener Gegenstand: " + item.toString());
            inventory.add(item.toString());  // toString() für korrekte Darstellung
            System.out.println("Zum Inventar hinzugefügt!");

            // Debug-Information
            System.out.println("Aktuelles Inventar: " + inventory);
            System.out.println("Geräuschpegel: " + character.getNoiseLevel());
        } else {
            System.out.println("Die Kiste ist leer!");
        }
    }

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

    public void initializeInteractiveObjects() {
        objectsInRooms.put(ROOM1, List.of(
                new Chest("c1", "Alte Truhe", "Eine alte Holztruhe mit Eisenbeschlägen.", 3, false)
        ));
        objectsInRooms.put(ROOM2, List.of(
                new Wardrobe("w1", "Kleiderschrank", "Ein grosser Kleiderschrank aus Eiche.", 0, false),
                new Box("b1", "Holzkiste", "Eine kleine Holzkiste.", 1, false)
        ));
        objectsInRooms.put(ROOM3, List.of(
                new Backpack("bp1", "Rucksack", "Ein abgenutzter Lederrucksack.", 1, false)
        ));
        objectsInRooms.put(ROOM4, List.of(
                new Chest("c2", "Goldtruhe", "Eine mit Gold verzierte Truhe.", 4, false)
        ));
        objectsInRooms.put(ROOM5, List.of(
                new Wardrobe("w2", "Schmuckschrank", "Ein Schrank voller Schmuckkästchen.", 0, false)
        ));
        objectsInRooms.put(ROOM6, List.of(
                new Box("b2", "Fass", "Ein altes Weinfass.", 0, false)
        ));
        objectsInRooms.put(ROOM7, List.of(
                new Chest("c3", "Büchertruhe", "Eine Truhe voller alter Bücher.", 0, false)
        ));
        objectsInRooms.put(ROOM8, List.of(
                new Backpack("bp2", "Reiserucksack", "Ein großer Reiserucksack.", 0, false)
        ));
        objectsInRooms.put(ROOM9, List.of(
                new Box("b3", "Schatzkiste", "Eine kleine Schatzkiste mit wertvollem Inhalt.", 3, false)
        ));
        objectsInRooms.put(ROOM10, List.of(
                new Wardrobe("w3", "Magischer Schrank", "Ein Schrank, der geheimnisvoll leuchtet.", 5, false)
        ));
        objectsInRooms.put(ROOM11, List.of(
                new Chest("c4", "Throntruhe", "Eine große Truhe neben dem Thron.", 5, false)
        ));
    }
}