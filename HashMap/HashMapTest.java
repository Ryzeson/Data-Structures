/**
 * HashMapTest.java
 * 10-9-2019
 * Ryzeson Maravich
 * 
 * Test for HashMap
 */

package hashmap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
	
	private HashMap<String, Integer> hashMap1;
	private HashMap<Character, Integer> hashMap2;

	@Before
	public void setUp() throws Exception {
		hashMap1 = new HashMap<>(5, .5);
		
		//SAMPLE EXAMPLE
		//putting data into an empty index will return null
		assertNull(hashMap1.put("Maddie", 1));
		
		assertNull(hashMap1.put("Erika", 2));
		
		assertNull(hashMap1.put("Jeb", 3));
		//RESIZED
		
		assertNull(hashMap1.put("Han", 4));
		
		assertNull(hashMap1.put("Jake", 5));
		
		assertNull(hashMap1.put("Nitka", 6));
		//RESIZED
		
		assertNull(hashMap1.put("Tullah", 7));
		
		assertNull(hashMap1.put("Riley", 8));
		
		assertNull(hashMap1.put("Ryzeson", 9));
		
		assertNull(hashMap1.put("Alex", 10));
		
		assertNull(hashMap1.put("Amanda", 11));
		
		assertNull(hashMap1.put("Angel", 12));
		//RESIZED
		
		assertNull(hashMap1.put("Daegen", 13));
		
		
		// hashMap2
		hashMap2 = new HashMap<>();
		
		assertNull(hashMap2.put('A', 1));
		assertNull(hashMap2.put('B', 2));
		assertNull(hashMap2.put('C', 3));
		assertNull(	hashMap2.put('{', 4));
		assertNull(hashMap2.put('+', 22));
		assertNull(hashMap2.put('}', 5));
		
		//returns the old data, which is 22
		assertSame(hashMap2.put('+', 33), 22);
	}

	@Test
	public void testPut() {
		//used the put method in setup
		System.out.println(hashMap1.toString());
		System.out.println(hashMap2.toString());
	}

	@Test
	public void testContainsKey() {
		assertTrue(hashMap1.containsKey("Jeb"));
		assertTrue(hashMap1.containsKey("Han"));
		assertTrue(hashMap1.containsKey("Erika"));
		assertTrue(hashMap1.containsKey("Maddie"));
		assertTrue(hashMap1.containsKey("Tullah"));
		assertTrue(hashMap1.containsKey("Jake"));
		assertTrue(hashMap1.containsKey("Nitka"));
		assertTrue(hashMap1.containsKey("Amanda"));
		assertTrue(hashMap1.containsKey("Ryzeson"));
		assertTrue(hashMap1.containsKey("Alex"));
		assertTrue(hashMap1.containsKey("Riley"));
		assertTrue(hashMap1.containsKey("Daegen"));
		assertTrue(hashMap1.containsKey("Angel"));
		
		assertFalse(hashMap1.containsKey("Sunny"));
	}

	@Test
	public void testContainsValue() {
		assertTrue(hashMap1.containsValue(1));
		assertTrue(hashMap1.containsValue(2));
		assertTrue(hashMap1.containsValue(3));
		assertTrue(hashMap1.containsValue(4));
		assertTrue(hashMap1.containsValue(5));
		assertTrue(hashMap1.containsValue(6));
		assertTrue(hashMap1.containsValue(7));
		assertTrue(hashMap1.containsValue(8));
		assertTrue(hashMap1.containsValue(9));
		assertTrue(hashMap1.containsValue(10));
		assertTrue(hashMap1.containsValue(11));
		assertTrue(hashMap1.containsValue(12));
		assertTrue(hashMap1.containsValue(13));
		
		assertFalse(hashMap1.containsValue(0));
		assertFalse(hashMap1.containsValue(14));
	}

	@Test
	public void testGet() {
		assertSame(hashMap1.get("Jeb"), 3);
		assertSame(hashMap1.get("Han"), 4);
		assertSame(hashMap1.get("Erika"), 2);
		assertSame(hashMap1.get("Maddie"), 1);
		assertSame(hashMap1.get("Tullah"), 7);
		assertSame(hashMap1.get("Jake"), 5);
		assertSame(hashMap1.get("Nitka"), 6);
		assertSame(hashMap1.get("Amanda"), 11);
		assertSame(hashMap1.get("Ryzeson"), 9);
		assertSame(hashMap1.get("Alex"), 10);
		assertSame(hashMap1.get("Riley"), 8);
		assertSame(hashMap1.get("Daegen"), 13);
		assertSame(hashMap1.get("Angel"), 12);
		
		assertSame(hashMap1.get("Sunny"), null);
		
		assertNotSame(hashMap1.get("Ryzeson"), 99);
	}

	@Test
	public void testRemove() {
		assertSame(hashMap1.remove("Maddie"), 1);
		assertSame(hashMap1.remove("Erika"), 2);
		assertSame(hashMap1.remove("Jeb"), 3);
		assertSame(hashMap1.remove("Han"), 4);
		assertSame(hashMap1.remove("Jake"), 5);
		assertSame(hashMap1.remove("Nitka"), 6);
		assertSame(hashMap1.remove("Tullah"), 7);
		assertSame(hashMap1.remove("Riley"), 8);
		assertSame(hashMap1.remove("Ryzeson"), 9);
		assertSame(hashMap1.remove("Alex"), 10);
		assertSame(hashMap1.remove("Amanda"), 11);
		assertSame(hashMap1.remove("Angel"), 12);
		assertSame(hashMap1.remove("Daegen"), 13);
		System.out.println(hashMap1.toString());
		
//		//PUTTING AFTER REMOVING (checks to see if deleted spots work properly)
		
		hashMap1.put("Hi01", 1);	
		hashMap1.put("Hi02", 2);	
		hashMap1.put("Hi03", 3);
		hashMap1.put("Hi04", 4);
		hashMap1.put("Hi05", 5);
		hashMap1.put("Hi06", 6);
		hashMap1.put("Hi07", 7);
		hashMap1.put("Hi08", 8);
		hashMap1.put("Hi09", 9);
		hashMap1.put("Hi10", 10);
		System.out.println(hashMap1.toString());
		
		//testing hashMap2
		assertSame(hashMap2.remove('A'), 1);
		
		assertFalse(hashMap2.containsKey('A'));
		assertFalse(hashMap2.containsValue(1));
		
		assertNull(hashMap2.put('A', 1));
		
		assertTrue(hashMap2.containsKey('A'));
		assertTrue(hashMap2.containsValue(1));
		
		assertSame(hashMap2.remove('+'), 33);
		
		//second time removing should return null, because it is now empty
		assertNull(hashMap2.remove('+'));
		
		//no D included
		assertNull(hashMap2.remove('D'));
	}

}
