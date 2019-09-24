/**
 * hw1
 * DListTest
 * Wed Sept 11-2019
 * 
 * @author Ryzeson Maravich
 * 
 * used to test DList
 */

import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

public class DListTest {
	
	private DList<Character> empty;
	private DList<Character> single;
	private DList<Character> multi;
	private DList<Character> multi2;
	private DList<Character> multi3;
	private DList<Character> multiRepeats;
	
	//with comparator
	private DList<Character> multiComp1;
	private DList<String> multiComp2;
	
	private class CharCmp implements Comparator<Character>{
		public int compare(Character c1, Character c2) {
			return c1.compareTo(c2);
		}
	}
	
	private class StrCmp implements Comparator<String>{
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}

	@Before
	public void setUp() throws Exception {
		empty = new DList<>();
		single = new DList<>();
		multi = new DList<>();
		multi2 = new DList<>();
		multi3 = new DList<>();
		multiRepeats = new DList<>();
		
		multiComp1 = new DList<>(new CharCmp());
		multiComp2 = new DList<>(new StrCmp());
		
		single.add('X');
		
		multi.add('A');
		multi.add('B');
		multi.add('C');
		
		multi2.add('B');
		multi2.add('D');
		multi2.add('A');
		multi2.add('C');
		//"[ B D A C ]"
		
		multi3.add('R');
		multi3.add('Y');
		multi3.add('Z');
		multi3.add('E');
		multi3.add('S');
		multi3.add('O');
		multi3.add('N');
		multi3.add('M');
		multi3.add('A');
		multi3.add('R');
		multi3.add('A');
		multi3.add('V');
		multi3.add('I');
		multi3.add('C');
		multi3.add('H');
		// [ R Y Z E S O N M A R A V I C H ]
		
		//repeated elements
		multiRepeats.add('A');
		multiRepeats.add('B');
		multiRepeats.add('C');
		multiRepeats.add('A');
		multiRepeats.add('C');
		multiRepeats.add('B');
		multiRepeats.add('B');
		//"[ A B C A C B B ]"
		
		multiComp1.add('A');
		multiComp1.add('B');
		multiComp1.add('C');
		multiComp1.add('D');
		//"[ A B C D ]"
		
		multiComp2.add("Jello");
		multiComp2.add("Lemon");
		multiComp2.add("Nachos");
		// "[ Jello Lemon Nachos ]"
		
	}

	@Test
	public void testAddE() {
		//tests for unsorted (inserting in the middle)
		assertEquals("[ ]", empty.toString()); //works
		assertEquals("[ ]", empty.toStringBwd());
		assertEquals(0, empty.size());
		
		assertEquals("[ X ]", single.toString());
		assertEquals("[ X ]", single.toStringBwd());
		assertEquals(1, single.size());
		
		assertEquals("[ A B C ]", multi.toString());
		assertEquals("[ C B A ]", multi.toStringBwd());
		assertEquals(3, multi.size());
		
		assertEquals("[ A B C A C B B ]", multiRepeats.toString());
		assertEquals("[ B B C A C B A ]", multiRepeats.toStringBwd());
		assertEquals(7, multiRepeats.size());
	
		
		//tests for insertion into sorted list properly
		assertEquals("[ A B C D ]", multiComp1.toString());
		assertEquals("[ D C B A ]", multiComp1.toStringBwd());
		assertEquals(4, multiComp1.size());
		
		multiComp1.add('A');
		assertEquals("[ A A B C D ]", multiComp1.toString());
		assertEquals("[ D C B A A ]", multiComp1.toStringBwd());
		assertEquals(5, multiComp1.size());
		
		multiComp1.add('B');
		assertEquals("[ A A B B C D ]", multiComp1.toString());
		assertEquals("[ D C B B A A ]", multiComp1.toStringBwd());
		assertEquals(6, multiComp1.size());
		
		multiComp1.add('C');
		assertEquals("[ A A B B C C D ]", multiComp1.toString());
		assertEquals("[ D C C B B A A ]", multiComp1.toStringBwd());
		assertEquals(7, multiComp1.size());
		
		multiComp1.add('D');
		assertEquals("[ A A B B C C D D ]", multiComp1.toString());
		assertEquals("[ D D C C B B A A ]", multiComp1.toStringBwd());
		assertEquals(8, multiComp1.size());
		
		
		assertEquals("[ Jello Lemon Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos Lemon Jello ]", multiComp2.toStringBwd());
		
		//adding items between each item
		multiComp2.add("Onion");
		assertEquals("[ Jello Lemon Nachos Onion ]", multiComp2.toString());
		assertEquals("[ Onion Nachos Lemon Jello ]", multiComp2.toStringBwd());
		
		
		multiComp2.add("Meat");
		assertEquals("[ Jello Lemon Meat Nachos Onion ]", multiComp2.toString());
		assertEquals("[ Onion Nachos Meat Lemon Jello ]", multiComp2.toStringBwd());
		
		multiComp2.add("Kale");
		assertEquals("[ Jello Kale Lemon Meat Nachos Onion ]", multiComp2.toString());
		assertEquals("[ Onion Nachos Meat Lemon Kale Jello ]", multiComp2.toStringBwd());
		
		multiComp2.add("Icing");
		assertEquals("[ Icing Jello Kale Lemon Meat Nachos Onion ]", multiComp2.toString());
		assertEquals("[ Onion Nachos Meat Lemon Kale Jello Icing ]", multiComp2.toStringBwd());
	}

	@Test
	public void testAddIntE() {
		//tests for sorted
		//should not show up when added because multiComp1 and multiComp2 are sorted (isOrdered)
		multiComp1.add(1, 'Z');
		multiComp1.add(1, 'A');
		assertEquals("[ A B C D ]", multiComp1.toString());
		assertEquals("[ D C B A ]", multiComp1.toStringBwd());
		assertEquals(4, multiComp1.size());
		
		multiComp2.add(3, "Pizza");
		multiComp2.add(3, "Apple");
		assertEquals("[ Jello Lemon Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos Lemon Jello ]", multiComp2.toStringBwd());
		assertEquals(3, multiComp2.size());
		
		//tests for unsorted
		multi.add(2, 'D');
		assertEquals("[ A B D C ]", multi.toString());
		assertEquals("[ C D B A ]", multi.toStringBwd());
		assertEquals(4, multi.size());
		
		multi.add(0, 'E');
		assertEquals("[ E A B D C ]", multi.toString());
		assertEquals("[ C D B A E ]", multi.toStringBwd());
		assertEquals(5, multi.size());
		
		multi.add(5, 'F');
		assertEquals("[ E A B D C F ]", multi.toString());
		assertEquals("[ F C D B A E ]", multi.toStringBwd());
		assertEquals(6, multi.size());
	}

	@Test
	public void testClear() {
		single.clear();
		assertEquals("[ ]", single.toString());
		assertEquals("[ ]", single.toStringBwd());
		assertEquals(0, single.size());
		assertTrue(single.empty());
		
		multi.clear();
		assertEquals("[ ]", multi.toString());
		assertEquals("[ ]", multi.toStringBwd());
		assertEquals(0, multi.size());
		assertTrue(multi.empty());
		
		multiComp1.clear();
		assertEquals("[ ]", multiComp1.toString());
		assertEquals("[ ]", multiComp1.toStringBwd());
		assertEquals(0, multiComp1.size());
		assertTrue(multiComp1.empty());
	}

	@Test
	public void testGet() {
		assertNull(empty.get(0));
		
		assertSame('X', single.get(0));
		assertNull(single.get(1));
		
		assertSame('A', multi.get(0));
		assertSame('B', multi.get(1));
		assertSame('C', multi.get(2));
	}

	@Test
	public void testSet() {
		//unordered list -works
		assertSame('D', multi2.set(1, 'E'));
		assertEquals("[ B E A C ]", multi2.toString());
		assertEquals("[ C A E B ]", multi2.toStringBwd());
		
		assertSame('A', multi2.set(2, 'F'));
		assertEquals("[ B E F C ]", multi2.toString());
		assertEquals("[ C F E B ]", multi2.toStringBwd());
		
		assertSame('B', multi2.set(0, 'G'));
		assertEquals("[ G E F C ]", multi2.toString());
		assertEquals("[ C F E G ]", multi2.toStringBwd());
		
		assertSame('C', multi2.set(3, 'H'));
		assertEquals("[ G E F H ]", multi2.toString());
		assertEquals("[ H F E G ]", multi2.toStringBwd());
		
		assertNull(multi2.set(5, 'I'));

		
		assertSame('C', multi.set(2, 'F'));
		assertEquals("[ A B F ]", multi.toString());
		assertEquals("[ F B A ]", multi.toStringBwd());
		
		assertSame('B', multi.set(1, 'E'));
		assertEquals("[ A E F ]", multi.toString());
		assertEquals("[ F E A ]", multi.toStringBwd());
		
		assertSame('A', multi.set(0, 'D'));
		assertEquals("[ D E F ]", multi.toString());
		assertEquals("[ F E D ]", multi.toStringBwd());
		
		//tests for sorted
		
		// "[ A B C D ]"
		//inserting in the wrong place so should return null
		assertNull(multiComp1.set(0, 'E'));
		assertNull(multiComp1.set(1, 'F'));
		assertNull(multiComp1.set(2, 'G'));
		assertNull(multiComp1.set(3, 'A'));
		
		//originally "[ Jello Lemon Nachos ]"
		assertEquals("Jello", multiComp2.set(0, "Apple"));
		assertEquals("[ Apple Lemon Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos Lemon Apple ]", multiComp2.toStringBwd());
		
		assertEquals("Lemon", multiComp2.set(1, "Banana"));
		assertEquals("[ Apple Banana Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos Banana Apple ]", multiComp2.toStringBwd());
		
		assertEquals("Nachos", multiComp2.set(2, "Cookie"));
		assertEquals("[ Apple Banana Cookie ]", multiComp2.toString());
		assertEquals("[ Cookie Banana Apple ]", multiComp2.toStringBwd());
		
		//index out of bounds
		assertNull(multi.set(4, 'H'));
		assertNull(multi.set(-1, 'H'));
		assertNull(multiComp1.set(4,  'H'));
		assertNull(multiComp1.set(-1,  'H'));
	}

	@Test
	public void testContains() {
		assertTrue(single.contains('X'));
		
		assertTrue(multi.contains('A'));
		assertTrue(multi.contains('B'));
		assertTrue(multi.contains('C'));
		
		assertTrue(multiComp2.contains("Jello"));
		assertTrue(multiComp2.contains("Lemon"));
		assertTrue(multiComp2.contains("Nachos"));
		
		assertFalse(multi.contains('Y'));
		assertFalse(single.contains('Y'));
		assertFalse(multiComp2.contains("Pizza"));
	}

	@Test
	public void testIndexOf() {
		assertSame(-1, empty.indexOf('A'));
		
		assertSame(0, single.indexOf('X'));
		assertSame(-1, single.indexOf('Y'));
		
		assertSame(0, multi.indexOf('A'));
		assertSame(1, multi.indexOf('B'));
		assertSame(2, multi.indexOf('C'));
		
		assertSame(0, multiRepeats.indexOf('A'));
		assertSame(1, multiRepeats.indexOf('B'));
		assertSame(2, multiRepeats.indexOf('C'));
		assertSame(-1, multiRepeats.indexOf('D'));
	}

	@Test
	public void testLastIndexOf() {
		assertSame(-1, empty.lastIndexOf('A'));
		
		assertSame(0, single.lastIndexOf('X'));
		assertSame(-1, single.lastIndexOf('Y'));
		
		assertSame(0, multi.lastIndexOf('A'));
		assertSame(1, multi.lastIndexOf('B'));
		assertSame(2, multi.lastIndexOf('C'));
		
		
		assertSame(3, multiRepeats.lastIndexOf('A'));
		assertSame(6, multiRepeats.lastIndexOf('B'));
		assertSame(4, multiRepeats.lastIndexOf('C'));
		assertSame(-1, multiRepeats.lastIndexOf('D'));
	}

	@Test
	public void testSize() {
		assertSame(0, empty.size());
		
		assertSame(1, single.size());
		
		assertSame(3, multi.size());
		
		assertNotSame(1, empty.size());
	}

	@Test
	public void testIsOrdered() {
		assertFalse(empty.isOrdered());
		assertFalse(single.isOrdered());
		assertFalse(multi.isOrdered());
		
		assertTrue(multiComp1.isOrdered());
		assertTrue(multiComp2.isOrdered());
	}

	@Test
	public void testEmpty() {
		assertTrue(empty.empty());
		assertFalse(single.empty());
		assertFalse(multi.empty());
	}
	
	@Test
	public void testRemoveInt() {
		//out of bounds error
//		assertThrows(IndexOutOfBoundsException, multi.remove(-1));
//		assertThrows(IndexOutOfBoundsException, multi.remove(9));
		
		//[ B D A C ]
		assertSame('A', multi2.remove(2));
		assertEquals("[ B D C ]", multi2.toString());
		assertEquals("[ C D B ]", multi2.toStringBwd());
		assertEquals(3, multi2.size());
		
		assertSame('D', multi2.remove(1));
		assertEquals("[ B C ]", multi2.toString());
		assertEquals("[ C B ]", multi2.toStringBwd());
		assertEquals(2, multi2.size());
		
		assertSame('C', multi2.remove(1));
		assertEquals("[ B ]", multi2.toString());
		assertEquals("[ B ]", multi2.toStringBwd());
		assertEquals(1, multi2.size());
		
		assertSame('B', multi2.remove(0));
		assertEquals("[ ]", multi2.toString());
		assertEquals("[ ]", multi2.toStringBwd()); 
		assertEquals(0, multi2.size());
		
		//[ A B C A C B B ]
		assertSame('A', multiRepeats.remove(0));
		assertEquals("[ B C A C B B ]", multiRepeats.toString());
		assertEquals("[ B B C A C B ]", multiRepeats.toStringBwd()); 
		assertEquals(6, multiRepeats.size());
		
		assertSame('B', multiRepeats.remove(5));
		assertEquals("[ B C A C B ]", multiRepeats.toString());
		assertEquals("[ B C A C B ]", multiRepeats.toStringBwd()); 
		assertEquals(5, multiRepeats.size());
		
		assertSame('A', multiRepeats.remove(2));
		assertEquals("[ B C C B ]", multiRepeats.toString());
		assertEquals("[ B C C B ]", multiRepeats.toStringBwd()); 
		assertEquals(4, multiRepeats.size());
		
		//[ Jello Lemon Nachos ]
		assertSame("Lemon", multiComp2.remove(1));
		assertEquals("[ Jello Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos Jello ]", multiComp2.toStringBwd());
		
		assertSame("Jello", multiComp2.remove(0));
		assertEquals("[ Nachos ]", multiComp2.toString());
		assertEquals("[ Nachos ]", multiComp2.toStringBwd());
		
		assertSame("Nachos", multiComp2.remove(0));
		assertEquals("[ ]", multiComp2.toString());
		assertEquals("[ ]", multiComp2.toStringBwd());
		
	}
	
	@Test
	public void testRemoveE() {
		//"[ A B C A C B B ]"
		assertTrue(multiRepeats.remove(new Character('A')));
		assertEquals("[ B C A C B B ]", multiRepeats.toString());
		assertEquals("[ B B C A C B ]", multiRepeats.toStringBwd());
		assertEquals(6, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('B')));
		assertEquals("[ C A C B B ]", multiRepeats.toString());
		assertEquals("[ B B C A C ]", multiRepeats.toStringBwd());
		assertEquals(5, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('C')));
		assertEquals("[ A C B B ]", multiRepeats.toString());
		assertEquals("[ B B C A ]", multiRepeats.toStringBwd());
		assertEquals(4, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('C')));
		assertEquals("[ A B B ]", multiRepeats.toString());
		assertEquals("[ B B A ]", multiRepeats.toStringBwd());
		assertEquals(3, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('B')));
		assertEquals("[ A B ]", multiRepeats.toString());
		assertEquals("[ B A ]", multiRepeats.toStringBwd());
		assertEquals(2, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('B')));
		assertEquals("[ A ]", multiRepeats.toString());
		assertEquals("[ A ]", multiRepeats.toStringBwd());
		assertEquals(1, multiRepeats.size());
		
		assertTrue(multiRepeats.remove(new Character('A')));
		assertEquals("[ ]", multiRepeats.toString());
		assertEquals("[ ]", multiRepeats.toStringBwd());
		assertEquals(0, multiRepeats.size());
		
		//not in the list
		assertFalse(multiRepeats.remove(new Character('A')));
		assertFalse(multiRepeats.remove(new Character('C')));
		assertFalse(multiRepeats.remove(new Character('D')));
		assertEquals("[ ]", multiRepeats.toString());
		assertEquals("[ ]", multiRepeats.toStringBwd());
		assertEquals(0, multiRepeats.size());
		
		
		//testing with string
		multiComp2.remove("Nachos");
		assertEquals("[ Jello Lemon ]", multiComp2.toString());
		assertEquals("[ Lemon Jello ]", multiComp2.toStringBwd());
		assertEquals(2, multiComp2.size());
		
		//should stay the same
		multiComp2.remove("Nachos");
		assertEquals("[ Jello Lemon ]", multiComp2.toString());
		assertEquals("[ Lemon Jello ]", multiComp2.toStringBwd());
		assertEquals(2, multiComp2.size());
		
		multiComp2.remove("Lemon");
		assertEquals("[ Jello ]", multiComp2.toString());
		assertEquals("[ Jello ]", multiComp2.toStringBwd());
		assertEquals(1, multiComp2.size());
		
		multiComp2.remove("Jello");
		assertEquals("[ ]", multiComp2.toString());
		assertEquals("[ ]", multiComp2.toStringBwd());
		assertEquals(0, multiComp2.size());
	}
	
	@Test
	public void testRemoveRange() {
//		assertThrows(IllegalArgumentException, multi.removeRange(1, 0));
		
		//should be IndexOutOfBoundsException regardless of indices
//		assertThrows(IndexOutOfBoundsException, empty.removeRange(1, 0));
//		assertThrows(IndexOutOfBoundsException, empty.removeRange(0, 1));
		
		// [ R Y Z E S O N M A R A V I C H ]
		multi3.removeRange(0, 2);
		assertEquals("[ Z E S O N M A R A V I C H ]", multi3.toString());
		assertEquals("[ H C I V A R A M N O S E Z ]", multi3.toStringBwd());
		assertEquals(13, multi3.size());
		
		multi3.removeRange(-5, 2);
		assertEquals("[ S O N M A R A V I C H ]", multi3.toString());
		assertEquals("[ H C I V A R A M N O S ]", multi3.toStringBwd());
		assertEquals(11, multi3.size());
		
		multi3.removeRange(2, 4);
		assertEquals("[ S O A R A V I C H ]", multi3.toString());
		assertEquals("[ H C I V A R A O S ]", multi3.toStringBwd());
		assertEquals(9, multi3.size());
		
		multi3.removeRange(7, 9);
		assertEquals("[ S O A R A V I ]", multi3.toString());
		assertEquals("[ I V A R A O S ]", multi3.toStringBwd());
		assertEquals(7, multi3.size());
		
		multi3.removeRange(4, 15);
		assertEquals("[ S O A R ]", multi3.toString());
		assertEquals("[ R A O S ]", multi3.toStringBwd());
		assertEquals(4, multi3.size());
		
		multi3.removeRange(0, 4);
		assertEquals("[ ]", multi3.toString());
		assertEquals("[ ]", multi3.toStringBwd());
		assertEquals(0, multi3.size());
	}
	
	@Test
	public void testAddAll() {
//		assertThrows(IndexOutOfBoundsException, multi.addAll(-1, single));
//		assertThrows(IndexOutOfBoundsException, multi.addAll(10, single));
//		
//		assertThrows(NullPointerException, multi.addAll(0, empty));
		
		//[ A B C D ], should not change because it is ordered
		multiComp1.addAll(1, multiComp1);
		assertEquals("[ A B C D ]", multiComp1.toString());
		assertEquals("[ D C B A ]", multiComp1.toStringBwd());
		assertEquals(4, multiComp1.size());
		
		//non ordered
		multi2.addAll(0, multi);
		assertEquals("[ A B C B D A C ]", multi2.toString());
		assertEquals("[ C A D B C B A ]", multi2.toStringBwd());
		assertEquals(7, multi2.size());
		
		multi2.addAll(1, single);
		assertEquals("[ A X B C B D A C ]", multi2.toString());
		assertEquals("[ C A D B C B X A ]", multi2.toStringBwd());
		assertEquals(8, multi2.size());
		
		multi2.addAll(8, multiComp1);
		assertEquals("[ A X B C B D A C A B C D ]", multi2.toString());
		assertEquals("[ D C B A C A D B C B X A ]", multi2.toStringBwd());
		assertEquals(12, multi2.size());
	}

}
