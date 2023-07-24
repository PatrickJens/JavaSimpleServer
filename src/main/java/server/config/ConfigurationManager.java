package server.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import server.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager mConfigurationManager; //singleton
    private static Configuration mCurrentConfiguration;

    private ConfigurationManager(){}

    public static ConfigurationManager getInstance() {
        if( mConfigurationManager == null ){
            mConfigurationManager = new ConfigurationManager();
        }
        return mConfigurationManager;
    }

    /**
     * Used to load a configuration file by the file path
     * Throws exceptions
     * @param filePath
     */
    public void loadConfigurationFile(String filePath)  {
        FileReader fileReader;
        try{
            fileReader = new FileReader(filePath);   //reads a single character at a time
        } catch(FileNotFoundException e){
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();               //like StringBuilder but thread safe
        int i ;
        try{
            while( ( i = fileReader.read() )!= -1){
                sb.append( (char) i);
            }
        } catch( IOException e){
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try{
            conf = Json.parse(sb.toString());
        } catch(IOException e){
            throw new HttpConfigurationException("Error parsing configuration file");
        }

        try{
            mCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch(JsonProcessingException e){
            throw new HttpConfigurationException("Error parsing the config file internally",e);
        }


    }

    public Configuration getCurrentConfiguration(){
        if( mCurrentConfiguration == null){
            throw new HttpConfigurationException("Configuration not set");
        }
        return mCurrentConfiguration;
    }
}
