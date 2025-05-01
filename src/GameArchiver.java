import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameArchiver {

    private final Logger logger;

    GameArchiver(Logger logger) {
        this.logger = logger;
    }


    public void zipFile(String zipPath, String filePath) {
        File file = new File(filePath);
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
             FileInputStream fis = new FileInputStream(file)) {

            zos.putNextEntry(new ZipEntry(file.getName()));
            fis.transferTo(zos);
            logger.logSuccess("Добавлен в архив: " + file.getName());

            zos.closeEntry();

        } catch (IOException exception) {
            logger.logError("Ошибка архивации: " + file.getName(), exception);
        }

    }


    public void zipFiles(String zipPath, List<String> filePaths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String path : filePaths) {
                File file = new File(path);

                // Пропускаем, если файл не существует или это директория
                if (!file.exists()) {
                    logger.logFail("Файл не найден или это папка: " + file.getPath());
                    continue;
                }
                try(FileInputStream fis = new FileInputStream(file)) {
                    zos.putNextEntry(new ZipEntry(file.getName()));

                    fis.transferTo(zos);
                    zos.closeEntry();

                    logger.logSuccess("Добавлен в архив: " + file.getName());
                }
            }
            logger.logSuccess("Архив создан: " + zipPath);

        } catch (IOException exception) {
            logger.logError("Ошибка при создании архива: " + zipPath, exception);
        }

    }









}
