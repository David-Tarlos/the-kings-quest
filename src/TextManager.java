import com.google.gson.Gson;
import java.io.FileReader;
import java.util.Map;

public class TextManager {
    private static Map<String, String> texte;

    public static void ladeTexte(String pfad) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(pfad);
            texte = gson.fromJson(reader, Map.class);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String get(String key) {
        return texte.getOrDefault(key, "[Text fehlt: " + key + "]");
    }
}
