package spiel.quests;

import java.util.Scanner;

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


    int x;
    int y;

    public Quest1(int[][] gameField) {
        this.gameField = gameField;
    }

    public void startQuest() {


        int[][] initial = {
                {NO_ROOM, ROOM4, NO_ROOM, ROOM5, ROOM6, ROOM7},
                {NO_ROOM, NO_ROOM, ROOM3, ROOM4, NO_ROOM, ROOM8},
                {NO_ROOM, ROOM2, ROOM1, NO_ROOM, ROOM10, ROOM9},
                {NO_ROOM, NO_ROOM, ENTRANCE, NO_ROOM, ROOM11, NO_ROOM}
        };

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                gameField[i][j] = initial[i][j];
            }
        }

        x = 2;
        y = 3;

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
                System.out.println("Description: Dies ist ein kleiner, gemütlicher Raum mit einem Kamin.");
                break;
            case ROOM3:
                System.out.println("Description: Ein langer, düsterer Korridor. Hier scheint es zu spuken.");
                break;
            case ROOM4:
                System.out.println("Description: Ein heller Raum mit großen Fenstern.");
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


        if (y > 0) {
            int above = gameField[y - 1][x];

            if (above == ROOM3) {
                System.out.println("Direction (Norden): Es gibt einen sehr langen Gang, der so aussieht als würde der ins nichts führen");
            }

            else if (above == ENTRANCE) {
                System.out.println("Direction (Norden): Die Ausgangstür befindet sich Richtung Norden");
            }

            else if (above >= ROOM1 && above <= ROOM11) {
                System.out.println("Direction (Norden): Es gibt eine Tür Richtung Norden.");
                canGoNorth = true;
            }
        }

        // Süden prüfen
        if (y < gameField.length - 1) {
            int below = gameField[y + 1][x];

            if (below >= ROOM1 && below <= ROOM11 || below == ENTRANCE) {
                System.out.println("Direction (Süden): Es gibt eine Tür Richtung Süden.");
                canGoSouth = true;
            }
        }

        // Westen prüfen
        if (x > 0) {
            int left = gameField[y][x - 1];

            if (left >= ROOM1 && left <= ROOM11 || left == ENTRANCE) {
                System.out.println("Direction (Westen): Es gibt eine Tür Richtung Westen.");
                canGoWest = true;
            }
        }

        // Osten prüfen
        if (x < gameField[y].length - 1) {
            int right = gameField[y][x + 1];

            if (right >= ROOM1 && right <= ROOM11 || right == ENTRANCE) {
                System.out.println("Direction (Osten): Es gibt eine Tür Richtung Osten.");
                canGoEast = true;
            }
        }
    }

    public boolean decision(Scanner scanner) {
        System.out.println("Was machst du?");

        int optionNumber = 1;
        java.util.Map<Integer, String> options = new java.util.HashMap<>();

        if (canGoNorth) {
            System.out.println(optionNumber + ". Nach Norden gehen");
            options.put(optionNumber, "NORDEN");
            optionNumber++;
        }
        if (canGoSouth) {
            System.out.println(optionNumber + ". Nach Süden gehen");
            options.put(optionNumber, "SÜDEN");
            optionNumber++;
        }
        if (canGoWest) {
            System.out.println(optionNumber + ". Nach Westen gehen");
            options.put(optionNumber, "WESTEN");
            optionNumber++;
        }
        if (canGoEast) {
            System.out.println(optionNumber + ". Nach Osten gehen");
            options.put(optionNumber, "OSTEN");
            optionNumber++;
        }
        System.out.println(optionNumber + ". Spiel beenden");
        options.put(optionNumber, "BEENDEN");

        int input = scanner.nextInt();

        if (!options.containsKey(input)) {
            System.out.println("Ungültige Eingabe!");
            return true;
        }

        String richtung = options.get(input);

        switch (richtung) {
            case "NORDEN":
                y = y - 1;
                break;
            case "SÜDEN":
                y = y + 1;
                break;
            case "WESTEN":
                x = x - 1;
                break;
            case "OSTEN":
                x = x + 1;
                break;
            case "BEENDEN":
                return false;
        }

        return true;
    }
}
