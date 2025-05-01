import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Logger logger = new Logger();

        // Путь к корневой директории игры (укажи свой путь на диске)
        Path gameRootPath = Paths.get("/Users/isre/Desktop/games");
        Path saveDir = gameRootPath.resolve("save_games");
        Path logFilePath = gameRootPath.resolve("temp/temp.txt");
        Path zipFilePath = saveDir.resolve("save_games.zip");

        // --- Создание списка прогрессов игры ---
        List<GameProgress> gameProgressList = List.of(
                new GameProgress(100, 3, 15, 254.32),
                new GameProgress(75, 5, 10, 170.5),
                new GameProgress(55, 2, 8, 95.7)
        );

        // Пути к файлам сохранений
        List<String> savePaths = Arrays.asList(
                saveDir.resolve("save1.dat").toString(),
                saveDir.resolve("save2.dat").toString(),
                saveDir.resolve("save3.dat").toString()
        );

        // --- Установка структуры каталогов и файлов игры ---
        GameInstaller gameInstaller = new GameInstaller(gameRootPath.toString(), logger);
        gameInstaller.install();

        // --- Сохранение прогрессов игры в файлы ---
        SaveGameManager sgm = new SaveGameManager(logger);
        sgm.loadGamesProgressInFile(saveDir.toString(), gameProgressList);

        // --- Архивация всех файлов сохранений в один zip ---
        GameArchiver archiver = new GameArchiver(logger);
        archiver.zipFiles(zipFilePath.toString(), savePaths);

        // --- Удаление оригинальных файлов сохранений после архивации ---
        sgm.deleteFile(savePaths.get(0));
        sgm.deleteFile(savePaths.get(1));
        sgm.deleteFile(savePaths.get(2));

        // --- Запись накопленного лога в файл ---
        logger.saveToFileAndClearLog(logFilePath.toString());
    }
}