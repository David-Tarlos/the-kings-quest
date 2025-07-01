package spiel;

import spiel.TextManager;
import spiel.quests.Quest1;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    public void startGame() {
        initializeData();
        welcomeText();
        mainMenu();
        startBackgroundStory();
    }

    public void initializeData() {
        TextManager.ladeTexte("src/ressources/Story.json");
    }

    public void startBackgroundStory() {
        System.out.println(Farben.ROT + "Zum Starten des Spiels die Enter Taste drücken" + Farben.WEISS);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        //TextManager.getStory1();
        //TextManager.getStory2();
        //TextManager.getStory3();
        int choice = -1;

        while (true) {
            System.out.println("Akzeptierst du dieses Angebot?");
            System.out.println(Farben.GRUEN + "1. Ja");
            System.out.println(Farben.ROT + "2. Nein" + Farben.WEISS);

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Du hast angenommen.");
                        int[][] map = new int[4][6];
                        Quest1 quest1 = new Quest1(map);
                        quest1.startQuest();
                        return;
                    case 2:
                        System.out.println("Du hast abgelehnt.");
                        //Todo
                        //System.out.println("");
                        return;
                    default:
                        System.out.println("Bitte wähle 1 oder 2.");
                }
            } else {
                scanner.next();
                System.out.println("Bitte gib eine Zahl ein.");
            }
        }
    }



    public void mainMenu() {

        String art =
                "\n" +
                        " ███▄ ▄███▓ ▄▄▄       ██▓ ███▄    █    \n" +
                        "▓██▒▀█▀ ██▒▒████▄    ▓██▒ ██ ▀█   █    \n" +
                        "▓██    ▓██░▒██  ▀█▄  ▒██▒▓██  ▀█ ██▒   \n" +
                        "▒██    ▒██ ░██▄▄▄▄██ ░██░▓██▒  ▐▌██▒   \n" +
                        "▒██▒   ░██▒ ▓█   ▓██▒░██░▒██░   ▓██░   \n" +
                        "░ ▒░   ░  ░ ▒▒   ▓▒█░░▓  ░ ▒░   ▒ ▒    \n" +
                        "░  ░      ░  ▒   ▒▒ ░ ▒ ░░ ░░   ░ ▒░   \n" +
                        "░      ░     ░   ▒    ▒ ░   ░   ░ ░    \n" +
                        "       ░         ░  ░ ░           ░    \n" +
                        "                                       \n";

        StringBuilder coloredArt = new StringBuilder();

        for (char c : art.toCharArray()) {
            if (c == '█' || c == '▄' || c == '▀') {
                coloredArt.append(Farben.WEISS).append(c);
            } else {
                coloredArt.append(Farben.ROT).append(c);
            }
        }
        coloredArt.append(Farben.WEISS);

        System.out.print(coloredArt.toString());


    }

    public void welcomeText() {
        System.out.println();
        String art =
                "▄▄▄█████▓ ██░ ██ ▓█████     ██ ▄█▀ ██▓ ███▄    █   ▄████   ██████      █████   █    ██ ▓█████   ██████ ▄▄▄█████▓\n" +
                        "▓  ██▒ ▓▒▓██░ ██▒▓█   ▀     ██▄█▒ ▓██▒ ██ ▀█   █  ██▒ ▀█▒▒██    ▒    ▒██▓  ██▒ ██  ▓██▒▓█   ▀ ▒██    ▒ ▓  ██▒ ▓▒\n" +
                        "▒ ▓██░ ▒░▒██▀▀██░▒███      ▓███▄░ ▒██▒▓██  ▀█ ██▒▒██░▄▄▄░░ ▓██▄      ▒██▒  ██░▓██  ▒██░▒███   ░ ▓██▄   ▒ ▓██░ ▒░\n" +
                        "░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ▓██ █▄ ░██░▓██▒  ▐▌██▒░▓█  ██▓  ▒   ██▒   ░██  █▀ ░▓▓█  ░██░▒▓█  ▄   ▒   ██▒░ ▓██▓ ░ \n" +
                        "  ▒██▒ ░ ░▓█▒░██▓░▒████▒   ▒██▒ █▄░██░▒██░   ▓██░░▒▓███▀▒▒██████▒▒   ░▒███▒█▄ ▒▒█████▓ ░▒████▒▒██████▒▒  ▒██▒ ░ \n" +
                        "  ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░   ▒ ▒▒ ▓▒░▓  ░ ▒░   ▒ ▒  ░▒   ▒ ▒ ▒▓▒ ▒ ░   ░░ ▒▒░ ▒ ░▒▓▒ ▒ ▒ ░░ ▒░ ░▒ ▒▓▒ ▒ ░  ▒ ░░   \n" +
                        "    ░     ▒ ░▒░ ░ ░ ░  ░   ░ ░▒ ▒░ ▒ ░░ ░░   ░ ▒░  ░   ░ ░ ░▒  ░ ░    ░ ▒░  ░ ░░▒░ ░ ░  ░ ░  ░░ ░▒  ░ ░    ░    \n" +
                        "  ░       ░  ░░ ░   ░      ░ ░░ ░  ▒ ░   ░   ░ ░ ░ ░   ░ ░  ░  ░        ░   ░  ░░░ ░ ░    ░   ░  ░  ░    ░      \n" +
                        "          ░  ░  ░   ░  ░   ░  ░    ░           ░       ░       ░         ░       ░        ░  ░      ░           \n";

        StringBuilder coloredArt = new StringBuilder();

        for (char c : art.toCharArray()) {
            if (c == '█' || c == '▄' || c == '▀') {
                coloredArt.append(Farben.WEISS).append(c);
            } else {
                coloredArt.append(Farben.ROT).append(c);
            }
        }
        coloredArt.append(Farben.WEISS);

        System.out.println(coloredArt.toString());

        try {
            Thread.sleep(900);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The Ultimate spiel.Game");
        System.out.print(Farben.BLAU + "Loading");
        try {
            for (int i = 0; i <= 3; i++) {
                Thread.sleep(600);
                System.out.print(".");
            }

            System.out.print("\rLoading");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i <= 3; i++) {
                Thread.sleep(600);
                System.out.print(".");
            }

            System.out.print("\rLoading");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearConsole();
    }

    public static void clearConsole() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(Farben.GRAU +
                    "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                    "▓ ███ █ ███ ███ █   █ ███ ███ ███ █ █ ███ ███ ███ █ █ ███ ███ ███ █   █ ███ ███ █ █ ███ ▓▓ ███ █ ███ ███ █   █ ███ ███ ███ █ █ ███ ███ █\n" +
                    "▓ █ █ █ █ █ █ █ ███ █ █ █ █   █ █ █ █ █   █   █ █ █ █ █ █ █   █ █ ███ █ █   █   █ █ █   ▓▓ █ █ █ █ █ █ █ ███ █ █ █ █   █ █ █ █ █   █   █\n" +
                    "▓ ███ █ ███ ███ █ █ █ ███ ███ ███ ███ ███ ███ ███ ███ ███ ███ ███ █ █ █ ███ ███ ███ ███ ▓▓ ███ █ ███ ███ █ █ █ ███ ███ ███ ███ ███ ███ █\n" +
                    "▓ █   █   █ █ █ █ █ █   █ █   █   █ █   █ █     █   █   █ █   █   █ █ █   █ █     █   █ ▓▓ █   █   █ █ █ █ █ █   █ █   █   █ █   █ █    \n" +
                    "▓ ███ ███ █ █ █ ███ ███ ███ ███ █ █ ███ █ ███ ███ ███ ███ ███ ███ ███ ███ ███ ███ ███ █ ▓▓ ███ ███ █ █ █ ███ ███ ███ ███ █ █ ███ █ ███ █\n" +
                    "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
                    + Farben.WEISS);
        }
    }
}


