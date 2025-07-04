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
        System.out.println(Farben.ROT + "Press Enter to begin the game" + Farben.WEISS);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        TextManager.getStory1();
        TextManager.getStory2();
        TextManager.getStory3();
        TextManager.getStory4();
        TextManager.getStory5();
        TextManager.getStory6();
        TextManager.getStory7();
        TextManager.getStory8();
        TextManager.getStory9();

        int choice = -1;

        while (true) {
            System.out.println("Do you accept this offer?");
            System.out.println(Farben.GRUEN + "1. Yes");
            System.out.println(Farben.ROT + "2. No" + Farben.WEISS);

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("You have accepted.");
                        int[][] map = new int[4][6];
                        Quest1 quest1 = new Quest1(map);
                        quest1.startQuest();
                        return;
                    case 2:
                        System.out.println("You have refused.");
                        System.out.println("You were beheaded.");
                        System.out.println(Farben.ROT + "\n\n--- GAME OVER ---\nYou died a painful death...\n\n" + Farben.WEISS);
                        return;
                    default:
                        System.out.println("Please choose 1 or 2.");
                }
            } else {
                scanner.next();
                System.out.println("Please enter a number.");
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
        System.out.println("Game of the Year");
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


