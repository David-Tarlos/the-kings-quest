package spiel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class TextManager {
    private static Map<String, String> texte;

    public static void ladeTexte(String pfad) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(pfad);
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            texte = gson.fromJson(reader, type);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return texte.getOrDefault(key, "[Text fehlt: " + key + "]");
    }

    public static void schreibeLangsam(String text, int delayMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void getStory1() {
        String story = get("background_story1");
        schreibeLangsam(Farben.GRUEN +story, 55);
    }
    public static void getStory2() {
        String story = get("background_story2");
        schreibeLangsam(Farben.GRUEN +story, 55);
    }

    public static void getStory3() {
        String story = get("background_story3");
        schreibeLangsam(Farben.ROT +story, 65);
    }
    public static void getStory4() {
        String story = get("background_story4");
        schreibeLangsam(Farben.GRUEN +story, 55);
    }
    public static void getStory5() {
        String story = get("background_story5");
        schreibeLangsam(Farben.ROT +story, 65);
    }
    public static void getStory6() {
        String story = get("background_story6");
        schreibeLangsam(Farben.GRUEN +story, 55);
    }

    public static void getStory7() {
        String story = get("background_story7");
        schreibeLangsam(Farben.ROT +story, 65);
    }
    public static void getStory8() {
        String story = get("background_story8");
        schreibeLangsam(Farben.GRUEN +story, 55);
    }
    public static void getStory9() {
        String story = get("background_story9");
        schreibeLangsam(Farben.ROT +story, 65);
    }


    public static void get1Room(){
        String Room1 = get("Room1");
        schreibeLangsam(Farben.ROT, 40);
    }
}