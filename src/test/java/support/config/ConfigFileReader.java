package support.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;
    private final String filePath;

    public ConfigFileReader(String filePath){
        this.filePath = filePath;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found at " + filePath);
        }
    }

    public String getProperty(String key) {
        String property = properties.getProperty(key);
        if(property!= null) return property;
        else throw new RuntimeException("[" + key + "] not specified in the " + filePath + " file.");
    }
}
