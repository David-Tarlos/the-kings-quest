package spiel.quests;

import com.sun.source.tree.CaseTree;
import spiel.Character;
import spiel.Farben;
import spiel.Gegner.Soldier;
import spiel.Initalizer;
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
    private ArrayList<Weapon> weapons = new ArrayList<>(); // Removed final
    private ArrayList<Ruestung> ruestung = new ArrayList<>(); // Removed final
    private ArrayList<Key> key = new ArrayList<>(); // Removed final
    private int x, y;
    private boolean justInteracted = false;
    public ArrayList<String> inventory = new ArrayList<>();
    Character character = new Character(0, 10);
    boolean playing = true;

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

        Initalizer initalizer = new Initalizer();
        initalizer.initializeItems();

        this.weapons = initalizer.getWeapons();
        this.ruestung = initalizer.getRuestung();
        this.key = initalizer.getKey();

        initializeInteractiveObjects();

        Scanner scanner = new Scanner(System.in);


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
        System.out.println("Du hast das Spiel durchgespielt");
        return;
    }

    public void enemyDecision(Character character) {
        int noise = character.getNoiseLevel();
        Random random = new Random();

        if (noise >= 3) {
            int chance = random.nextInt(100);

            if (noise >= 7 && chance < 70) {
                System.out.println(Farben.ROT + "Du warst extrem laut! Ein Soldat hat dich sicher bemerkt!" + Farben.WEISS);
                character.setNoiseLevel(Math.max(0, noise - 4));
                enemyInteraction();
            } else if (noise >= 5 && chance < 50) {
                System.out.println(Farben.ROT + "Dein Lärm zieht Aufmerksamkeit auf sich..." + Farben.WEISS);
            } else if (noise >= 3 && chance < 20) {
                System.out.println(Farben.ROT + "Irgendetwas bewegt sich in der Nähe, vielleicht hast du jemanden aufgeschreckt." + Farben.WEISS);
            } else {
                character.setNoiseLevel(Math.max(0, noise - 1));
            }
        }
    }

    public void enemyInteraction() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Was machst du?");
        System.out.println(Farben.BLAU + "1. Verstecken");
        System.out.println(Farben.BLAU + "2. Ruhig bleiben und nichts tun");

        int choice = 0;
        while (choice != 1 && choice != 2) {
            try {
                System.out.print("Deine Wahl: ");
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte 1 oder 2 eingeben.");
                scanner.next();
            }
        }

        int chance = random.nextInt(100);

        if (choice == 1) {
            if (chance < 40) {
                System.out.println("Du hast dich erfolgreich versteckt. Der Soldat hat dich nicht bemerkt.");
            } else if (chance >= 40) {
                System.out.println("Der Soldat hat dich entdeckt!");
                enemyAttack();
            }
        } else if (choice == 2) {
            if (chance < 40) {
                System.out.println("Du bleibst ruhig – und der Soldat geht an dir vorbei, ohne dich zu sehen.");
            } else if (chance >= 40) {
                System.out.println("Der Soldat bemerkt dich!");
                enemyAttack();
            }
        }
    }

    public void enemyAttack() {
        Random random = new Random();
        int chance = random.nextInt(5);
        int health = random.nextInt(10) + 1;
        int attackPower = random.nextInt(4) + 1;
        int defense = random.nextInt(2) + 1;
        boolean isAttacking = random.nextBoolean();

        if (chance == 1) {
            Soldier soldier = new Soldier("Soldat", health, attackPower, defense);
            System.out.println(Farben.ROT + "Ein Soldat betritt den Raum und bemerkt dich!");

            if (isAttacking) {
                System.out.println("Er greift dich mit seinem Schwert an. Du weichst aus, wirst aber am Unterbauch getroffen.");
                int schaden = Math.max(0, attackPower - defense);
                character.setHealth(Math.max(0, character.getHealth() - schaden));
                System.out.println("Du hast " + schaden + " Schaden erlitten.");
                System.out.println("Deine verbleibenden HP: " + character.getHealth());
            } else {
                System.out.println("Was möchtest du tun?");
                System.out.println("1. Angreifen");
                System.out.println("2. Flüchten");

                Scanner scanner = new Scanner(System.in);
                int choice = 0;
                while (choice != 1 && choice != 2) {
                    try {
                        System.out.print("Deine Wahl: ");
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Ungültige Eingabe. Bitte 1 oder 2 eingeben.");
                        scanner.next();
                    }
                }

                if (choice == 1) {
                    // Fixed: Check if weapons list is empty
                    if (weapons.isEmpty()) {
                        System.out.println("Du hast keine Waffen! Du kämpfst mit bloßen Händen.");
                        // Default attack with bare hands
                        int schaden = 1;
                        int verbleibendeLeben = Math.max(0, soldier.getHealth() - schaden);
                        soldier.setHealth(verbleibendeLeben);
                        System.out.println("Der Soldat hat jetzt " + soldier.getHealth() + " HP.");
                    } else {
                        System.out.println("Wähle eine Waffe:");
                        int counter = 0;
                        for (Weapon weapon : weapons) {
                            counter++;
                            System.out.println(counter + ". " + weapon.getName());
                        }

                        int weaponChoice = 0;
                        while (weaponChoice < 1 || weaponChoice > weapons.size()) {
                            try {
                                System.out.print("Waffenauswahl (Zahl): ");
                                weaponChoice = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Ungültige Eingabe.");
                                scanner.next();
                            }
                        }

                        Weapon selectedWeapon = weapons.get(weaponChoice - 1);
                        System.out.println("Du greifst mit " + selectedWeapon.getName() + " an!");

                        int schaden = selectedWeapon.getSchaden();
                        int verbleibendeLeben = Math.max(0, soldier.getHealth() - schaden);
                        soldier.setHealth(verbleibendeLeben);
                        System.out.println("Der Soldat hat jetzt " + soldier.getHealth() + " HP.");
                    }

                    if (soldier.getHealth() <= 0) {
                        System.out.println("Der Soldat wurde besiegt!");
                    } else {
                        System.out.println("Der Soldat lebt noch.");
                    }

                } else {
                    System.out.println("Du versuchst zu fliehen...");
                    int fluchtChance = random.nextInt(100);
                    if (fluchtChance < 60) {
                        System.out.println("Du konntest erfolgreich fliehen!");
                    } else {
                        System.out.println("Der Soldat hindert dich an der Flucht und trifft dich!");
                        int schaden = Math.max(0, attackPower - defense);
                        character.setHealth(Math.max(0, character.getHealth() - schaden));
                        System.out.println("Du hast " + schaden + " Schaden erlitten.");
                        System.out.println("Deine verbleibenden HP: " + character.getHealth());
                    }
                }
            }
        }
    }

    public void description() {
        int currentRoom = gameField[y][x];

        if (currentRoom >= 0 && currentRoom < wasAlreadyInRoom.length && wasAlreadyInRoom[currentRoom]) {
            playing = false;
            nextQuest();
        }

        if ((currentRoom == ENTRANCE || currentRoom == ROOM11) && isPrincesSaved) {

            nextQuest();
        }

        if (currentRoom >= 0 && currentRoom < wasAlreadyInRoom.length) {
            wasAlreadyInRoom[currentRoom] = true;
        }

        switch (currentRoom) {
            case ENTRANCE:
                System.out.println(Farben.ROT+"Description: Du stehst am Eingang des Schlosses. Es ist still und dunkel. Die Landschaft sagt mir dass alles in Ordnung ist, aber wenn ich dieses Schloss ansehe voller Spinnweben, es ist schon so, als wäre hier kein Mensch. Ich höre ein Plötzliches weinen. Es war eine Stimme eines Fraues. Es müsste die Prinzessin sein, dann bin ich doch nicht falsch"+Farben.WEISS);
                break;
            case ROOM1:
                System.out.println(Farben.ROT+"Description: Es ist sehr spuklich. Es ist ein langer Gang vor dir und siehst das andere Ende nicht. Rechts hinter der Tür eine versteckte alte Truhe.  Links gerade neben der Eingangstür ist aber eine Tür, die so schreint als wäre es offen. "+Farben.WEISS);
                break;
            case ROOM2:
                System.out.println(Farben.ROT+"Description: Dies ist ein kleiner Raum, mit einem Bett, Tisch und einem Schrank."+Farben.WEISS);
                break;
            case ROOM3:
                System.out.println(Farben.ROT+"Description: Ein langer, düsterer Korridor. Hier scheint es zu spuken. Rechts ist ein mittelgrosser Raum. "+Farben.WEISS);
                break;
            case ROOM5:
                System.out.println(Farben.ROT+"Description: Ein Raum voller alter Gemälde."+Farben.WEISS);
                break;
            case ROOM6:
                System.out.println(Farben.ROT+"Description: Hier hört man das leise Tropfen von Wasser."+Farben.WEISS);
                break;
            case ROOM7:
                System.out.println(Farben.ROT+"Description: Ein Raum, der nach alten Büchern riecht. Dort ist die Prinzessin. "+Farben.WEISS);
                System.out.println(Farben.GELB+"Du nimmst sie mit"+Farben.WEISS);
                System.out.println("Nun muss du dich zu einem Ausgang begeben");
                isPrincesSaved = true;
                break;
            case ROOM8:
                System.out.println(Farben.ROT+"Description: Ein verborgener Raum hinter einer Geheimtür."+Farben.WEISS);
                break;
            case ROOM9:
                System.out.println(Farben.ROT+"Description: Der Raum ist mit Staub bedeckt und wirkt verlassen."+Farben.WEISS);
                break;
            case ROOM10:
                System.out.println(Farben.ROT+"Description: Ein Raum voller mystischer Symbole an den Wänden."+Farben.WEISS);
                break;
            case ROOM11:
                System.out.println(Farben.ROT+"Description: Dies ist der Thronsaal, prachtvoll und imposant."+Farben.WEISS);
                break;
            default:
                System.out.println(Farben.ROT+"Du befindest dich an einem unbekannten Ort."+Farben.WEISS);
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
                System.out.println(Farben.GRUEN+"Direction (Norden): Es gibt einen sehr langen Gang, der so aussieht als würde der ins nichts führen"+ Farben.WEISS);
                canGoNorth = true;
            } else if (above == ENTRANCE) {
                System.out.println(Farben.GRUEN+"Direction (Norden): Die Ausgangstür befindet sich Richtung Norden"+ Farben.WEISS);
                canGoNorth = true;
            } else if (above >= ROOM1 && above <= ROOM11) {
                System.out.println(Farben.GRUEN+"Direction (Norden): Es gibt eine Tür Richtung Norden."+ Farben.WEISS);
                canGoNorth = true;
            }
        }

        //Süden
        if (y < gameField.length - 1) {
            int below = gameField[y + 1][x];

            if (below >= ROOM1 && below <= ROOM11 || below == ENTRANCE) {
                System.out.println(Farben.GRUEN+"Direction (Süden): Es gibt eine Tür Richtung Süden."+ Farben.WEISS);
                canGoSouth = true;
            }
        }

        // Westen
        if (x > 0) {
            int left = gameField[y][x - 1];

            if (left >= ROOM1 && left <= ROOM11 || left == ENTRANCE) {
                System.out.println(Farben.GRUEN+"Direction (Westen): Es gibt eine Tür Richtung Westen."+ Farben.WEISS);
                canGoWest = true;
            }
        }

        // Osten
        if (x < gameField[y].length - 1) {
            int right = gameField[y][x + 1];

            if (right >= ROOM1 && right <= ROOM11 || right == ENTRANCE) {
                System.out.println(Farben.GRUEN+"Direction (Osten): Es gibt eine Tür Richtung Osten."+Farben.WEISS);
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
        if (canGoNorth) actions.add(Farben.BLAU + "Gehe nach Norden");
        if (canGoSouth) actions.add(Farben.BLAU + "Gehe nach Süden");
        if (canGoWest)  actions.add(Farben.BLAU + "Gehe nach Westen");
        if (canGoEast)  actions.add(Farben.BLAU + "Gehe nach Osten");

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

        System.out.print(Farben.BLAU+"");
        String selectedAction = actions.get(input - 1);

        String cleanAction = selectedAction.replace(Farben.BLAU, "").replace(Farben.WEISS, "");
        if (cleanAction.startsWith("Untersuche")) {
            String objectName = cleanAction.substring("Untersuche ".length());
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
        if (lautstaerke == 0 || lautstaerke == 1 || lautstaerke == 3 || lautstaerke == 4 || lautstaerke == 6 || lautstaerke == 7 || lautstaerke == 10) {
            System.out.println("Nein!! es war zu laut, ich muss aufpassen, nicht dass ich bemerkt werde und von Soldaten angegriffen werde");
            character.setNoiseLevel(character.getNoiseLevel() + 3);
        }


        if (key.isEmpty() && weapons.isEmpty() && ruestung.isEmpty()) {
            System.out.println("Die Kiste ist leer!");
            return;
        }

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
            inventory.add(item.toString());
            System.out.println("Zum Inventar hinzugefügt!");

            System.out.println("Aktuelles Inventar: " + inventory);
            System.out.println("Geräuschpegel: " + character.getNoiseLevel());
        } else {
            System.out.println("Die Kiste ist leer!");
        }
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