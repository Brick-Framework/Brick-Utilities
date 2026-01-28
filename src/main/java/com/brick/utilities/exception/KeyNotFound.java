package com.brick.utilities.exception;

public class KeyNotFound extends Exception {
	
	private final String key;
	
    public KeyNotFound(String key) {
        super("Cannot Find Key : "+key);
        this.key = key;
    }

	public String getKeyNotFound() {
		return key;
	}
}
