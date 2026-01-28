package com.brick.utilities;

import com.brick.utilities.exception.KeyNotFound;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BrickMapTest {
	
	@Test
	public void contains_test() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
		BrickMap brickMap = new BrickMap(map);
		
		assertTrue(brickMap.contains("subMap"));
        assertFalse(brickMap.contains(invalidKey));
	}
	
	@Test
	public void getBrickMap_test() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
        
		BrickMap brickMap = new BrickMap(map);
		
		
        assertEquals(new BrickMap(subMap), brickMap.getBrickMap("subMap"));
        assertThrows(KeyNotFound.class,()->{
            brickMap.getBrickMap(invalidKey);
        });
        
        assertEquals(new BrickMap(subMap), brickMap.getOptionalBrickMap("subMap").get());
        assertFalse(brickMap.getOptionalBrickMap(invalidKey).isPresent());
	}
	
	@Test
	public void getString_test() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		map.put("string","string");
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals("string",brickMap.getString("string"));
        assertThrows(KeyNotFound.class,()->{
            brickMap.getString(invalidKey);
        });

        assertEquals("string",brickMap.getOptionalString("string").get());
        assertFalse(brickMap.getOptionalString(invalidKey).isPresent());
	}
	
	@Test
	public void getInteger_test() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		map.put("integer",2);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(2,brickMap.getOptionalInteger("integer").get());
        assertFalse(brickMap.getOptionalInteger(invalidKey).isPresent());
	}
	
	@Test
	public void getDouble_test() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		map.put("double",3.0);
		map.put("notDouble", "abc");
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(3.0,brickMap.getOptionalDouble("double").get());
        assertFalse(brickMap.getOptionalDouble(invalidKey).isPresent());
        assertThrows(ClassCastException.class, ()->{
        	brickMap.getOptionalDouble("notDouble");
        });
	}
	
	@Test
	public void getListOfMap() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> listOfMap = new ArrayList<>();
        listOfMap.add(new HashMap<>());
        listOfMap.add(new HashMap<>());

        map.put("listOfMap",listOfMap);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(listOfMap, brickMap.getListOfMap("listOfMap"));
        assertThrows(KeyNotFound.class,()->{
            brickMap.getListOfMap(invalidKey);
        });

        assertEquals(listOfMap,brickMap.getOptionalListOfMap("listOfMap").get());
        assertFalse(brickMap.getOptionalListOfMap(invalidKey).isPresent());
	}
	
	@Test
	public void getListOfString() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		List<String> listOfString = new ArrayList<>();
        listOfString.add("a");
        listOfString.add("b");

        map.put("listOfString",listOfString);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(listOfString,brickMap.getOptionalListOfString("listOfString").get());
        assertFalse(brickMap.getOptionalListOfString(invalidKey).isPresent());
        
        assertEquals(listOfString, brickMap.getListOfString("listOfString"));
        assertThrows(KeyNotFound.class, ()->{
        	brickMap.getListOfString(invalidKey);
        });
	}
	
	@Test
	public void getListOfInteger_test() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		List<Integer> listOfInteger = new ArrayList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);

        map.put("listOfInteger", listOfInteger);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(listOfInteger,brickMap.getOptionalListOfInteger("listOfInteger").get());
        assertFalse(brickMap.getOptionalListOfInteger(invalidKey).isPresent());
	}
	
	@Test
	public void getListOfDouble_test() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		List<Double> listOfDouble = new ArrayList<>();
        listOfDouble.add(1.0);
        listOfDouble.add(2.0);

        map.put("listOfDouble",listOfDouble);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(listOfDouble,brickMap.getOptionalListOfDouble("listOfDouble").get());
        assertFalse(brickMap.getOptionalListOfDouble(invalidKey).isPresent());
	}
	
	@Test
	public void getListOfObject_test() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		List<Double> listOfDouble = new ArrayList<>();
        listOfDouble.add(1.0);
        listOfDouble.add(2.0);

        map.put("listOfDouble",listOfDouble);
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(listOfDouble,brickMap.getListOfObject("listOfDouble"));
		assertThrows(KeyNotFound.class, ()->{
			brickMap.getListOfObject(invalidKey);
		});
	}
	
	@Test
	public void getBoolean_test() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		map.put("boolean", true);
		BrickMap brickMap = new BrickMap(map);
		
		assertTrue(brickMap.getBoolean("boolean"));
        assertThrows(KeyNotFound.class,()->{
            brickMap.getBoolean(invalidKey);
        });

        assertTrue(brickMap.getOptionalBoolean("boolean").get());
        assertFalse(brickMap.getOptionalBoolean(invalidKey).isPresent());
	}
	
	@Test
	public void getObject_test() throws KeyNotFound {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
        
		BrickMap brickMap = new BrickMap(map);
		
		assertEquals(subMap,brickMap.getObject("subMap"));
        assertThrows(KeyNotFound.class,()->{
            brickMap.getObject(invalidKey);
        });
	}
	
	@Test
	public void brickMapConstructor() {
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
        
		BrickMap brickMap = new BrickMap(map);
		
		BrickMap newBrickMap = new BrickMap(map);
        assertEquals(newBrickMap, brickMap);
        assertEquals(newBrickMap.hashCode(), brickMap.hashCode());
	}
	
	@Test
	public void equalsAndIsPresent() {
		String invalidKey = "";
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
        
		BrickMap brickMap = new BrickMap(map);
		assertNotEquals(brickMap, invalidKey);
        assertFalse(brickMap.isEmpty());
	}
	
	@Test
	public void forEach_test() throws KeyNotFound {
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> subMap = new HashMap<>();
        subMap.put("abc","abc");
        subMap.put("def","def");
        map.put("subMap",subMap);
        BrickMap brickMap = new BrickMap(map);
        
        for( Map.Entry<String,Object> entry: brickMap ) {
        	assertEquals(entry.getValue(), brickMap.getObject(entry.getKey()) );
        }
	}
    
    @Test
    public void nullMap() {
    	BrickMap brickMap = new BrickMap(null);
    	assertTrue( brickMap.isEmpty() );
    }
}
