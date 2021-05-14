package support.config;

import support.enums.DriverType;
import support.enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;
    private String propertyFilePath = "src\\test\\java\\support\\config\\Configuration.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found at " + propertyFilePath);
        }
    }

    public ConfigFileReader(String filePath) {
        this.propertyFilePath = filePath;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found at " + propertyFilePath);
        }
    }

    public String getProperty(String key) {
        String property = properties.getProperty(key);
        if(property!= null) return property;
        else throw new RuntimeException("[" + key + "] not specified in the " + propertyFilePath + " file.");
    }

    public String getDriverDir() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration file.");
    }

    public long getImplicitWait() {
        String implicitWait = properties.getProperty("implicitWait");
        if (implicitWait != null) return Long.parseLong(implicitWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration file.");
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if (browserName.equalsIgnoreCase("android")) return DriverType.ANDROID;
        else if (browserName.equals("ie")) return DriverType.IE;
        else
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public DriverType getBrowserFromParams() {
        String target = String.valueOf(System.getProperty("target"));

        if (target.equalsIgnoreCase("chrome")) return DriverType.CHROME;
        else if (target.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if (target.equalsIgnoreCase("android")) return DriverType.ANDROID;
        else if (target.equalsIgnoreCase("ie")) return DriverType.IE;
        else return null;
    }

    public EnvironmentType getEnvFromParams() {
        String environmentName = String.valueOf(System.getProperty("env"));
        if (environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if (environmentName.equalsIgnoreCase("remote")) return EnvironmentType.REMOTE;
        else return null;
    }

    public String getSeleniumAddressFromParams() {
        return String.valueOf(System.getProperty("seleniumAddress"));
    }

    public String getAppiumAddressFromParams() {
        return String.valueOf(System.getProperty("appiumAddress"));
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if (environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if (environmentName.equalsIgnoreCase("remote")) return EnvironmentType.REMOTE;
        else
            throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }


    public String getTestDataJsonsResourcePath() {
        String testDataJsonsResourcePath = properties.getProperty("testDataJsonsResourcePath");
        if (testDataJsonsResourcePath != null) return testDataJsonsResourcePath;
        else
            throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
    }
}
