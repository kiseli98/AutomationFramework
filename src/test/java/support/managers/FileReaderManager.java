package support.managers;

import support.config.ConfigReader;
import support.config.JsonDataReader;

public class FileReaderManager {
    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigReader configReader;
    private static JsonDataReader jsonDataReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigReader getConfigReader() {
        return (configReader == null) ? new ConfigReader() : configReader;
    }

    public JsonDataReader getJsonReader() {
        return (jsonDataReader == null) ? new JsonDataReader() : jsonDataReader;
    }
}
