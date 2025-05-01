import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final StringBuilder log = new StringBuilder();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Формирует строку времени
    private String timestamp() {
        return "[" + LocalDateTime.now().format(formatter) + "]";
    }

    // Успех
    public void logSuccess(String message) {
        log.append(timestamp()).append(" [SUCCESS] ").append(message).append("\n");
    }

    // Неудача
    public void logFail(String message) {
        log.append(timestamp()).append("[FAIL] ").append(message).append("\n");
    }

    // Использовать в случае ошибки
    public void logError(String message, Exception e) {
        log.append(timestamp()).append("[ERROR] ").append(message)
                .append(" — ").append(e.getMessage()).append("\n");
    }

    public void clearLog() {
        log.setLength(0);
    }


    //  Записывает лог в файл
    public void saveToFileAndClearLog(String relativePath) {
        File logFile = new File(relativePath);
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в " + logFile.getPath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога: " + e.getMessage());
        }

        clearLog();
    }
}