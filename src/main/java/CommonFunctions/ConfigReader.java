package CommonFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    Properties prop;
    public ConfigReader(String env) throws IOException {
        prop= new Properties();
        if(env.equalsIgnoreCase("qa")) {

            prop.load(new FileReader("config.properties"));
        }
        else if(env.equalsIgnoreCase("uat")){
            prop.load(new FileReader("uat_config.properties"));
            }
    }
    public String readPropertyFile(String key){

      return   prop.getProperty(key);
    }
}
