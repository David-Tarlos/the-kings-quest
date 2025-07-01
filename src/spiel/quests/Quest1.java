package spiel.quests;

import spiel.InteractiveObject.*;

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
    private int x, y;

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

        setupInteractiveObjects();

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            description();
            directions();
            playing = decision(scanner);
            System.out.println();
        }

        System.out.println("Das Spiel ist beendet. Danke fürs Spielen!");
        scanner.close();
    }

    public void description() {
        int currentRoom = gameField[y][x];

        switch (currentRoom) {
            case ENTRANCE:
                System.out.println("Description: Du stehst am Eingang des Schlosses. Es ist still und dunkel. Die Landschaft sagt mir dass alles in Ordnung ist, aber wenn ich dieses Schloss ansehe voller Spinnweben, es ist schon so, als wäre hier kein Mensch. Ich höre ein Plötzliches weinen. Es war eine Stimme eines Fraues. Es müsste die Prinzessin sein, dann bin ich doch nicht falsch");
                break;
            case ROOM1:
                System.out.println("Description: Es ist sehr spuklich. Es ist ein langer Gang vor dir und siehst das andere Ende nicht. Links gerade neben der Eingangstür ist aber eine Tür, die so schreint als wäre es offen");
                break;
            case ROOM2:
                System.out.println("Description: Dies ist ein kleiner Raum, mit einem Bett, Tisch und einem Schrank.");
                break;
            case ROOM3:
                System.out.println("Description: Ein langer, düsterer Korridor. Hier scheint es zu spuken.");
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
            } else if (above == ENTRANCE) {
                System.out.println("Direction (Norden): Die Ausgangstür befindet sich Richtung Norden");
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


    public boolean decision(Scanner scanner) {
        int currentRoom = gameField[y][x];
        List<InteractiveObjects> objectsHere = objectsInRooms.get(currentRoom);

        if (objectsHere != null && !objectsHere.isEmpty()) {
            System.out.println("Was möchtest du untersuchen?");
            for (int i = 0; i < objectsHere.size(); i++) {
                System.out.println((i + 1) + ". " + objectsHere.get(i).getName());
            }
            System.out.println("0. Nichts untersuchen");

            int choice = scanner.nextInt();
            if (choice > 0 && choice <= objectsHere.size()) {
                interactWithObject(objectsHere.get(choice - 1), scanner);
                return true;
            } else if (choice != 0) {
                System.out.println("Ungültige Eingabe!");
                return true;
            }
        }

        System.out.println("Was machst du?");
        List<String> options = new ArrayList<>();

        if (canGoNorth) options.add("NORDEN");
        if (canGoSouth) options.add("SÜDEN");
        if (canGoWest) options.add("WESTEN");
        if (canGoEast) options.add("OSTEN");

        if (options.isEmpty()) {
            System.out.println("Keine verfügbaren Richtungen!");
            return true;
        }

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". Nach " + options.get(i) + " gehen");
        }

        int input = scanner.nextInt();
        if (input < 1 || input > options.size()) {
            System.out.println("Ungültige Eingabe!");
            return true;
        }

        switch (options.get(input - 1)) {
            case "NORDEN" -> y--;
            case "SÜDEN" -> y++;
            case "WESTEN" -> x--;
            case "OSTEN" -> x++;
        }

        return true;
    }

    public void interactWithObject(InteractiveObjects obj, Scanner scanner) {
        System.out.println("Du untersuchst: " + obj.getName());
        obj.setIstGeoeffnet(true);
        System.out.println(obj.getBeschreibung());
        int lautstaerke =obj.getLautstaerke();
        if (lautstaerke != 0) {
            System.out.println("Nein!! es war zu laut, ich muss aufpassen, nicht dass ich bemerkt werde und von Soldaten angegriffen werden");
        }
        System.out.println("Einen Gegenstand in der Kiste gefunden");


    }
    

    public void setupCharacterObject(){

    }

    public void setupInteractiveObjects() {
        objectsInRooms.put(ROOM1, List.of(
                new Chest("c1", "Alte Truhe", "Eine alte Holztruhe mit Eisenbeschlägen.", 3, false)
        ));
        objectsInRooms.put(ROOM2, List.of(
                new Wardrobe("w1", "Kleiderschrank", "Ein grosser Kleiderschrank aus Eiche.", 2, false),
                new Box("b1", "Holzkiste", "Eine kleine Holzkiste.", 1, false)
        ));
        objectsInRooms.put(ROOM3, List.of(
                new Backpack("bp1", "Rucksack", "Ein abgenutzter Lederrucksack.", 1, false)
        ));
        objectsInRooms.put(ROOM4, List.of(
                new Chest("c2", "Goldtruhe", "Eine mit Gold verzierte Truhe.", 4, false)
        ));
        objectsInRooms.put(ROOM5, List.of(
                new Wardrobe("w2", "Schmuckschrank", "Ein Schrank voller Schmuckkästchen.", 3, false)
        ));
        objectsInRooms.put(ROOM6, List.of(
                new Box("b2", "Fass", "Ein altes Weinfass.", 2, false)
        ));
        objectsInRooms.put(ROOM7, List.of(
                new Chest("c3", "Büchertruhe", "Eine Truhe voller alter Bücher.", 3, false)
        ));
        objectsInRooms.put(ROOM8, List.of(
                new Backpack("bp2", "Reiserucksack", "Ein großer Reiserucksack.", 2, false)
        ));
        objectsInRooms.put(ROOM9, List.of(
                new Box("b3", "Schatzkiste", "Eine kleine Schatzkiste mit wertvollem Inhalt.", 3, false)
        ));
        objectsInRooms.put(ROOM10, List.of(
                new Wardrobe("w3", "Magischer Schrank", "Ein Schrank, der geheimnisvoll leuchtet.", 4, false)
        ));
        objectsInRooms.put(ROOM11, List.of(
                new Chest("c4", "Throntruhe", "Eine große Truhe neben dem Thron.", 5, false)
        ));
    }
}

