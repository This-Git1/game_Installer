import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class SaveGameManager {

    private final Logger logger;

    SaveGameManager(Logger logger) {
        this.logger = logger;
    }


    public void loadGameProgressInFile(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);
            logger.logSuccess("Сохранение прогресса выполнено: " + path);

        } catch (Exception exception) {
            logger.logError("Ошибка при сохранении файла: " + path, exception);
        }
    }


    //TODO доработать!
    private int counter = 1;

    public void loadGamesProgressInFile(String path, List<GameProgress> gameProgress) {
        for (GameProgress progress : gameProgress) {
            String fileName = path + "/save" + counter + ".dat";
            counter++;
            loadGameProgressInFile(fileName, progress);
        }
    }





    public void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            logger.logSuccess("Файл " + file.getPath() + " удален");
        } else {
            logger.logFail("Файл " + file.getPath() + " не удалось удалить");
        }
    }



}
