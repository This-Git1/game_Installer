import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private final StringBuilder log = new StringBuilder();

    // Использовать в случае успеха
    public void logSuccess(String message) {
        log.append("[SUCCESS] ").append(message).append("\n");
    }

    // Использовать в случае неудачи
    public void logFail(String message) {
        log.append("[FAIL] ").append(message).append("\n");
    }

    // Использовать в случае ошибки
    public void logError(String message, Exception e) {
        log.append("[ERROR] ").append(message)
                .append(" — ").append(e.getMessage()).append("\n");
    }


    //  Записывает лог в файл
    public void saveToFile(String relativePath) {
        File logFile = new File(relativePath);
        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в " + logFile.getPath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога: " + e.getMessage());
        }
    }
}