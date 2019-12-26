/**
 * BTreeTest.java
 * Ryzeson Maravich
 * 10-25-2019
 * 
 * Test for BTree and BTreeNode
 */
import java.util.Comparator;

public class BTreeTest {

	private static class IntComparator implements Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	}

	public static void main(String[] args) {
		BTree<Integer> btree = new BTree<>(new IntComparator(), 3);
		
		System.out.println("BTree degree 3");
		
		System.out.println("Count of btree: " + btree.count);
		
		System.out.println("After adding 5");
		btree.add(5);
		System.out.println(btree.toString());
		
		System.out.println("After adding 10");
		btree.add(10);
		System.out.println(btree.toString());
		
		System.out.println("After adding 7");
		btree.add(7);
		System.out.println(btree.toString());
		
		System.out.println("After adding 20");
		btree.add(20);
		System.out.println(btree.toString());
		
		System.out.println("After adding 25");
		btree.add(25);
		System.out.println(btree.toString());
		
		System.out.println("After adding 3");
		btree.add(3);
		System.out.println(btree.toString());
		
		System.out.println("After adding 1");
		btree.add(1);
		System.out.println(btree.toString());
		
		System.out.println("After adding 100");
		btree.add(100);
		System.out.println(btree.toString());
		
		System.out.println("After adding 101");
		btree.add(101);
		System.out.println(btree.toString());
		
		System.out.println("After adding 30");
		btree.add(30);
		System.out.println(btree.toString());
		
		System.out.println("After adding 22");
		btree.add(22);
		System.out.println(btree.toString());
		
		System.out.println("After adding 17");
		btree.add(17);
		System.out.println(btree.toString());
		
		System.out.println("After adding 16");
		btree.add(16);
		System.out.println(btree.toString());
		
		System.out.println("After adding 14");
		btree.add(14);
		System.out.println(btree.toString());
		
		System.out.println("After adding 9");
		btree.add(9);
		System.out.println(btree.toString());
		
		System.out.println("After adding 50");
		btree.add(50);
		System.out.println(btree.toString());
		
		System.out.println("After adding 40");
		btree.add(40);
		System.out.println(btree.toString());
		
		System.out.println("After adding 60");
		btree.add(60);
		System.out.println(btree.toString());
		
		System.out.println("After adding 35");
		btree.add(35);
		System.out.println(btree.toString());
		
		System.out.println("After adding 110");
		btree.add(110);
		System.out.println(btree.toString());
		
		System.out.println("Count of btree: " + btree.count);
		
		//TEST BTREE REMOVE
		System.out.println("After removing 50");
		btree.remove(50);
		System.out.println(btree.toString());
		
		//rotate right
		System.out.println("After removing 60");
		btree.remove(60);
		System.out.println(btree.toString());
		
		//rotate left
		System.out.println("After removing 40");
		btree.remove(40);
		System.out.println(btree.toString());
		
		//merge
		System.out.println("After removing 100");
		btree.remove(100);
		System.out.println(btree.toString());
		
		//rotate left
		System.out.println("After removing 30");
		btree.remove(30);
		System.out.println(btree.toString());

		System.out.println("After removing 3");
		btree.remove(3);
		System.out.println(btree.toString());
		
		System.out.println("After removing 16");
		btree.remove(16);
		System.out.println(btree.toString());
		
		System.out.println("After removing 25");
		btree.remove(25);
		System.out.println(btree.toString());
		
		System.out.println("After removing 101");
		btree.remove(101);
		System.out.println(btree.toString());
		
		System.out.println("After removing 14");
		btree.remove(14);
		System.out.println(btree.toString());
		
		System.out.println("After removing 9");
		btree.remove(9);
		System.out.println(btree.toString());
		
		System.out.println("After removing 20");
		btree.remove(20);
		System.out.println(btree.toString());
		
		System.out.println("After removing 35");
		btree.remove(35);
		System.out.println(btree.toString());
		
		System.out.println("After removing 22");
		btree.remove(22);
		System.out.println(btree.toString());
		
		System.out.println("After removing 7");
		btree.remove(7);
		System.out.println(btree.toString());
		
		System.out.println("After removing 5");
		btree.remove(5);
		System.out.println(btree.toString());
		
		System.out.println("After removing 110");
		btree.remove(110);
		System.out.println(btree.toString());
		
		System.out.println("After removing 10");
		btree.remove(10);
		System.out.println(btree.toString());
		
		System.out.println("After removing 17");
		btree.remove(17);
		System.out.println(btree.toString());
		
		System.out.println("Count: " + btree.count);
		
		System.out.println("After removing 1");
		btree.remove(1);
		System.out.println(btree.toString());
		
		System.out.println("Count: " + btree.count);
		
		System.out.println("ADD AFTER REMOVING");
		
		btree.add(10);
		btree.add(20);
		btree.add(30);
		btree.add(40);
		btree.add(80);
		btree.add(70);
		btree.add(60);
		btree.add(50);
		System.out.println(btree.toString());
		System.out.println("Count: " + btree.count);
		
		//degree 4
		BTree<Integer> btree2 = new BTree<>(new IntComparator(), 4);
		
		System.out.println("BTree degree 4");
		
		System.out.println("After adding 5");
		btree2.add(5);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 10");
		btree2.add(10);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 7");
		btree2.add(7);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 20");
		btree2.add(20);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 25");
		btree2.add(25);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 3");
		btree2.add(3);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 1");
		btree2.add(1);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 100");
		btree2.add(100);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 101");
		btree2.add(101);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 30");
		btree2.add(30);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 22");
		btree2.add(22);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 17");
		btree2.add(17);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 16");
		btree2.add(16);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 14");
		btree2.add(14);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 9");
		btree2.add(9);
		System.out.println(btree2.toString());
		
		System.out.println("After adding 50");
		btree2.add(50);
		System.out.println(btree2.toString());
		
		//TEST REMOVE
		System.out.println("After removing 100");
		btree2.remove(100);
		System.out.println(btree2.toString());
		
		//rotate right (with children)
		System.out.println("After removing 30");
		btree2.remove(30);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 22");
		btree2.remove(22);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 3");
		btree2.remove(3);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 9");
		btree2.remove(9);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 16");
		btree2.remove(16);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 17");
		btree2.remove(17);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 5");
		btree2.remove(5);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 10");
		btree2.remove(10);
		System.out.println(btree2.toString());
		
		//merge right (with children)
		System.out.println("After removing 1");
		btree2.remove(1);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 25");
		btree2.remove(25);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 101");
		btree2.remove(101);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 7");
		btree2.remove(7);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 20");
		btree2.remove(20);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 14");
		btree2.remove(14);
		System.out.println(btree2.toString());
		
		System.out.println("After removing 50");
		btree2.remove(50);
		System.out.println(btree2.toString());
		
		System.out.println("Count: " + btree2.count);
		
		System.out.println("ADD AFTER REMOVING");
		
		btree2.add(10);
		btree2.add(20);
		btree2.add(30);
		btree2.add(40);
		btree2.add(80);
		btree2.add(70);
		btree2.add(60);
		btree2.add(50);
		System.out.println(btree2.toString());
		System.out.println("Count: " + btree2.count);

//		degree 5
		BTree<Integer> btree3 = new BTree<>(new IntComparator(), 5);
		
		System.out.println("BTree degree 5");
		
		System.out.println("After adding 5");
		btree3.add(5);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 10");
		btree3.add(10);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 7");
		btree3.add(7);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 20");
		btree3.add(20);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 25");
		btree3.add(25);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 3");
		btree3.add(3);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 1");
		btree3.add(1);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 100");
		btree3.add(100);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 101");
		btree3.add(101);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 30");
		btree3.add(30);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 22");
		btree3.add(22);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 17");
		btree3.add(17);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 16");
		btree3.add(16);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 14");
		btree3.add(14);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 9");
		btree3.add(9);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 40");
		btree3.add(40);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 33");
		btree3.add(33);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 45");
		btree3.add(45);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 60");
		btree3.add(60);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 2");
		btree3.add(2);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 39");
		btree3.add(39);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 49");
		btree3.add(49);
		System.out.println(btree3.toString());
		
		System.out.println("After adding 50");
		btree3.add(50);
		System.out.println(btree3.toString());

		//TEST REMOVE
		//borrow successor (no children)
		//rotate right (no children)
		System.out.println("After removing 10");
		btree3.remove(10);
		System.out.println(btree3.toString());
		
		//borrow successor (no children)
		//rotate left (no children)
		System.out.println("After removing 9");
		btree3.remove(9);
		System.out.println(btree3.toString());
		
		//removing root
		//borrow successor (with children)
		//merge left (no children)
		//rotate left (with children) (at very top of tree)
		System.out.println("After removing 20");
		btree3.remove(20);
		System.out.println(btree3.toString());
		
		//removing root
		//borrow successor (with children)
		//rotate right (no children)
		System.out.println("After removing 30");
		btree3.remove(30);
		System.out.println(btree3.toString());
		
		//borrow successor (no children)
		System.out.println("After removing 45");
		btree3.remove(45);
		System.out.println(btree3.toString());
		
		//merge right
		//merge left (with children) (on top)
		//setting root correctly (?)
		System.out.println("After removing 39");
		btree3.remove(39);
		System.out.println(btree3.toString());
		
		//rotate left (no children)
		System.out.println("After removing 22");
		btree3.remove(22);
		System.out.println(btree3.toString());
		
		//rotate right (no children) (tie)
		System.out.println("After removing 17");
		btree3.remove(17);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 1");
		btree3.remove(1);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 33");
		btree3.remove(33);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 16");
		btree3.remove(16);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 50");
		btree3.remove(50);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 50");
		btree3.remove(50);
		System.out.println(btree3.toString());
		
		System.out.println("Count: " + btree3.count);
		
		System.out.println("Removing values not in tree");
		
		System.out.println("After removing 500");
		btree3.remove(500);
		System.out.println(btree3.toString());
		
		System.out.println("After removing -5");
		btree3.remove(-5);
		System.out.println(btree3.toString());
		
		System.out.println("Count: " + btree3.count);
		
		System.out.println("Removing values in tree");
		
		System.out.println("After removing 2");
		btree3.remove(2);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 3");
		btree3.remove(3);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 5");
		btree3.remove(5);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 7");
		btree3.remove(7);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 14");
		btree3.remove(14);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 25");
		btree3.remove(25);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 49");
		btree3.remove(49);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 60");
		btree3.remove(60);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 100");
		btree3.remove(100);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 101");
		btree3.remove(101);
		System.out.println(btree3.toString());
		
		System.out.println("After removing 40");
		btree3.remove(40);
		System.out.println(btree3.toString());
		
		System.out.println("Count: " + btree3.count);
		
		System.out.println("After removing 40");
		btree3.remove(40);
		System.out.println(btree3.toString());
		
		System.out.println("ADD AFTER REMOVING");
		
		btree3.add(10);
		btree3.add(20);
		btree3.add(30);
		btree3.add(40);
		btree3.add(80);
		btree3.add(70);
		btree3.add(60);
		btree3.add(50);
		System.out.println(btree3.toString());
		System.out.println("Count: " + btree3.count);

		

//		BTreeNode<Integer> testNode = new BTreeNode<>(null, new IntComparator(), 10);
//		
//		testNode.add(5);
//		testNode.add(7);
//		testNode.add(9);
//		testNode.add(1);
//		testNode.add(3);
//		
//		//testing indexOf
//		System.out.println("\nTesting indexOf and contains");
//		System.out.println(testNode);
//		System.out.println("5 is in index " +testNode.indexOf(5));
//		System.out.println("9 is in index " +testNode.indexOf(9));
//		
//		testNode.add(-1);
//		testNode.add(11);
//		System.out.println(testNode);
//		System.out.println("5 is in index " +testNode.indexOf(5));
//		System.out.println("9 is in index " +testNode.indexOf(9));
//		
//		System.out.println("100 is in index " +testNode.indexOf(100));
//		System.out.println("-100 is in index " +testNode.indexOf(-100));
//		
//		//testing contains
//		System.out.println(testNode);
//		System.out.println("Contains -1? " + testNode.contains(-1));
//		System.out.println("Contains 5? " + testNode.contains(5));
//		System.out.println("Contains 11? " + testNode.contains(11));
//		System.out.println("Contains -11? " + testNode.contains(-11));
//		System.out.println("Contains 6? " + testNode.contains(6));
//		System.out.println("Contains 100? " + testNode.contains(100));
	}

}
