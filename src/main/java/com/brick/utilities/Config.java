package com.brick.utilities;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.brick.utilities.exception.ConfigException;
import com.brick.utilities.exception.InvalidData;
import com.brick.utilities.exception.KeyNotFound;
import com.brick.utilities.file.FileReader;
import com.brick.utilities.file.YamlFileReader;

/*
    Description: Helps Read and get Config Details from Config file
 */
public class Config {
    public static final String SEPERATOR = ".";
    private static final String SEPERATOR_REGEX = "\\.";
    private static final String CONFIG_FILE = "/application.yaml";


    
    private static volatile Config instance;

    private BrickMap configMap;
    private Config() {
        FileReader fileReader = new YamlFileReader(CONFIG_FILE);
        try {
			configMap = fileReader.getMap();
		} catch (FileNotFoundException | InvalidData e) {
			configMap = new BrickMap( new HashMap<String,Object>() ); // Creating with Empty Map
		}
    }
    
    /*
    Singleton Pattern to Ensure Config File is read only once
     */
    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    /*
        Description: Returns Data from Config File based on key
     */
    public static Object getConfigObject(String key) throws ConfigException {
        String[] nestedKeys = key.split(SEPERATOR_REGEX);
        
        try {
        	BrickMap currentMap = getInstance().configMap;
            for (int i = 0; i < nestedKeys.length; i++) {
                if( i == nestedKeys.length - 1){
                    return currentMap.getObject(nestedKeys[i]);
                }

                currentMap = currentMap.getBrickMap(nestedKeys[i]);
            }
        }catch(KeyNotFound e){
            throw new ConfigException(e.getMessage());
        }

        return null; // Dead Code Execution will never reach here 
    }

    /*
        Description: Returns Boolean Data from Config File
     */
    public static Boolean getConfigBoolean(String key) throws ConfigException {
        try{
            return (Boolean)getConfigObject(key);
        }catch(ClassCastException e){
            throw new ConfigException(e.getMessage());
        }
    }

	/*
        Description: Returns Integer Data from Config File
     */
	public static Integer getConfigInteger(String key) throws ConfigException {
		try{
			return (Integer)getConfigObject(key);
		}catch(ClassCastException e){
			throw new ConfigException(e.getMessage());
		}
	}
	
	/*
	 	Description: Returns Integer Data from Config File if Present else returns Default Value
	 */
	public static int getConfigIntegerOrDefault(String key, int defaultValue){
		try {
			return (Integer)getConfigObject(key);
		}catch(Exception e) {
			return defaultValue;
		}
	}
}

