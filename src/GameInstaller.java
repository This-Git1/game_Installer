import java.io.File;
import java.io.IOException;
import java.util.List;


public class GameInstaller {
    private final String basePath;

    private final Logger logger;

    private final List<String> folders = List.of(
            "src",
            "src/main",
            "src/test",
            "res",
            "res/drawables",
            "res/vectors",
            "res/icons",
            "save_games",
            "temp"
    );

    private final List<String> files = List.of(
            "src/main/Main.java",
            "src/main/Utils.java",
            "temp/temp.txt"
    );



    public GameInstaller(String basePath, Logger logger) {
        this.basePath = basePath;
        this.logger = logger;
    }

    // Основной метод установки
    public void install() {
        createDirectories(folders);
        createFiles(files);
    }

    // Создает каталог
    private void createOneDirectory(String path) {
        File dir = new File(basePath, path);
        if (dir.mkdir()) {
            logger.logSuccess("Каталог создан: " + dir.getPath());
        } else {
            logger.logFail("Каталог уже существует или не удалось создать " + dir.getPath());
        }
    }

    // Создает каталоги
    private void createDirectories(List<String> paths) {
        for (String path : paths) {
            createOneDirectory(path);
        }
    }

    // Создает файл
    private void createOneFile(String fileName) {
        File file = new File(basePath, fileName);
        try {
            if (file.createNewFile()) {
                logger.logSuccess("Файл создан: " + file.getPath());
            } else {
                logger.logFail("Файл уже существует или не удалось создать: " + file.getPath());
            }
        } catch (IOException exception) {
            logger.logError("Ошибка при создании файла " + file.getPath(), exception);
        }
    }

    // Создает файлы
    private void createFiles(List<String> paths) {
        for (String path : paths) {
            createOneFile(path);
        }
    }




}
