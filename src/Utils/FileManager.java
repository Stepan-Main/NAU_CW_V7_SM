package Utils;

// додамо у project structure Google Json бібліотеку з Maven
import com.google.gson.Gson;
import org.jetbrains.annotations.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    // Метод запису даних об'екту бібліотеки у файл
    public static void saveFile(String fileName, Object data) {
        // У блоці try з ресурсами створимо об'ект FileWriter і передамо до него ім'я файлу
        // try з ресурсами контролюе выдкривання та закривання файлу
        try (FileWriter writer = new FileWriter(fileName)) {
            // Створемо об'ект Gson для работи з даннами типу Json
            Gson gson = new Gson();
            // На об'екті Gson викличемо метод toJson для запису даних бібліотеки у файл за допомогою FileWriter
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод считування даних об'екту бібліотеки з файлу
    @Nullable
    @Contract(pure = true)
    public static <T> T loadFromFile(String fileName, Class<T> clazz) {
        // У блоці try з ресурсами створимо об'ект FileReader і передамо до него ім'я файлу
        // try з ресурсами контролюе выдкривання та закривання файлу
        try (FileReader reader = new FileReader(fileName)) {
            // Створемо об'ект Gson для работи з даннами типу Json
            Gson gson = new Gson();
            // На об'екті Gson викличемо метод fromJson для чинання даних нашої бібліотеки з файлу
            // за допомогою FileReader та повернемо об'ект нашої бібліотеки у програму
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Інакше повернемо null
        return null;
    }
}
