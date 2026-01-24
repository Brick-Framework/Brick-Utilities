package com.brick.utilities;

import com.brick.utilities.exception.KeyNotFound;
import java.util.*;

/*
    Description: Wrapper Class of Map tp Handle Type Conversion while Getting Data
 */
public class BrickMap implements Iterable<Map.Entry<String, Object>> {
    private Map<String,Object> map;

    public BrickMap(Map<String, Object> yaml) {
        this.map = yaml;
    }

    public BrickMap(Object yaml){
        this.map = (Map<String, Object>)yaml;
    }

    public BrickMap getBrickMap(String key) throws KeyNotFound {
        if( !map.containsKey(key) ){
            throw new KeyNotFound(key);
        }

        return new BrickMap( map.get(key) );
    }

    public Optional<BrickMap> getOptionalBrickMap(String key){
        if( map.containsKey(key) ) {
            return Optional.of(new BrickMap(map.get(key)) );
        }

        return Optional.empty();
    }

    public String getString(String key) throws KeyNotFound{
        if( !map.containsKey(key) ){
            throw new KeyNotFound(key);
        }

        return (String) map.get(key);
    }

    public Optional<Integer> getOptionalInteger(String key){
        if( map.containsKey(key) ){
            return Optional.of( (Integer) map.get(key) );
        }

        return Optional.empty();
    }

    public Optional<Double> getOptionalDouble(String key){
        if( map.containsKey(key) ){
        	Object value = map.get(key);
        	if (value instanceof Number) {
                return Optional.of(((Number) value).doubleValue());
            }else {
            	ClassCastException classCastException = new ClassCastException("Cannot cast : "+value+" to Double");
            	throw classCastException;
            }
        }

        return Optional.empty();
    }

    public Optional<String> getOptionalString(String key){
        if(map.containsKey(key) ){
            return Optional.of( (String) map.get(key) );
        }

        return Optional.empty();
    }

    public Optional<List<Map<String,Object>>> getOptionalListOfMap(String key){
        if( map.containsKey(key) ){
            return Optional.of( (List) map.get(key) );
        }

        return Optional.empty();
    }

    public List<Map<String,Object>> getListOfMap(String key) throws KeyNotFound {
        if( !map.containsKey(key) ){
            throw new KeyNotFound(key);
        }

        return (List<Map<String,Object>>) map.get(key) ;
    }
    
    public List<String> getListOfString(String key) throws KeyNotFound {
    	if( !map.containsKey(key) ) {
    		throw new KeyNotFound(key);
    	}
    	
    	return (List<String>) map.get(key);
    }
    
    public List<Object> getListOfObject(String key) throws KeyNotFound {
    	if( !map.containsKey(key) ) {
    		throw new KeyNotFound(key);
    	}
    	
    	return (List<Object>) map.get(key);
    }

    public Optional<List<String>> getOptionalListOfString(String key){
        if( map.containsKey(key) ){
            return Optional.of( (List<String>) map.get(key) );
        }

        return Optional.empty();
    }

    public Optional<List<Integer>> getOptionalListOfInteger(String key){
        if( map.containsKey(key) ){
            return Optional.of( (List<Integer>) map.get(key) );
        }

        return Optional.empty();
    }

    public Optional<List<Double>> getOptionalListOfDouble(String key){
        if( map.containsKey(key) ){
            return Optional.of( (List<Double>) map.get(key) );
        }

        return Optional.empty();
    }

    public Optional<Boolean> getOptionalBoolean(String key){
        if( map.containsKey(key) ){
            return Optional.of( (Boolean) map.get(key) );
        }

        return Optional.empty();
    }

    public boolean getBoolean(String key) throws KeyNotFound {
        if( !map.containsKey(key) ){
            throw new KeyNotFound(key);
        }

        return (boolean) map.get(key);
    }

    public Object getObject(String key) throws KeyNotFound {
        if( !map.containsKey(key) ){
            throw new KeyNotFound(key);
        }

        return map.get(key);
    }

    public boolean contains(String key){
        return map.containsKey(key);
    }

    public boolean isEmpty(){
        if( this.map == null ){
            return true;
        }
        return this.map.isEmpty();
    }


    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BrickMap)) return false;
        BrickMap brickMap = (BrickMap) o;
        return Objects.equals(map, brickMap.map);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(map);
    }
}

