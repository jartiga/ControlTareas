package sv.com.task.utils;

import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

    Properties properties = null;
    
    /** Configuration file name */
    public final static String CONFIG_FILE_NAME = "SystemMessageResource.properties";
 
    private SystemProperties() {
        this.properties = new Properties();
        try {
            properties.load(SystemProperties.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//Configuration
 
    /**
     * Implementando Singleton
     *
     * @return
     */
    public static SystemProperties getInstance() {
        return SystemPropertiesHolder.INSTANCE;
    }
 
    private static class SystemPropertiesHolder {
 
        private static final SystemProperties INSTANCE = new SystemProperties();
    }
 
    /**
     * Retorna la propiedad de configuración solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }//getProperty
}
