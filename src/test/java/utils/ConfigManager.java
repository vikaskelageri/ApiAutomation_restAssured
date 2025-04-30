package utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties config;

    private ConfigManager() {
        // Private constructor so no one can create object of this class
    }

    public static Properties getConfig() {
        if (config == null) {
            config = new Properties();
            try {
                config.load(ConfigManager.class.getClassLoader().getResourceAsStream("Config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading Config.properties");
            }
        }
        return config;
    }
}
