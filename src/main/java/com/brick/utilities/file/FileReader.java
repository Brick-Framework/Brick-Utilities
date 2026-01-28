package com.brick.utilities.file;

import com.brick.utilities.BrickMap;
import com.brick.utilities.exception.InvalidData;

import java.io.FileNotFoundException;

/*
    Description: Abstract File Reader Class
 */
public abstract class FileReader {
    protected String fileName;

    protected FileReader(String fileName) {
        this.fileName = fileName;
    }

    public abstract BrickMap getMap() throws FileNotFoundException, InvalidData;
    
    public static String getFileExtension(String fileName) {
    	String[] nameParts = fileName.split("\\.");
    	return nameParts[nameParts.length-1];
    }
}
