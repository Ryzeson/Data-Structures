/**
 * HashEntry.java
 * 10-9-2019
 * Ryzeson Maravich
 * 
 * Implements a HashMap using the simple quadratic probing sequence (+1, +4, +9, ..) as its collision handling method.
 */
package hashmap;

public class HashMap<K, V> {

	private final HashEntry<K, V> E = new HashEntry<K, V>();
	private final HashEntry<K, V> D = new HashEntry<K, V>();
	private final static int INIT_SIZE = 7;
	private final static double MAX_LF = .5;

	private double maxLoadFactor;
	private int count;
	private HashEntry<K, V>[] table;

	/**
	 * creates an empty internal table (array) for HashEntry of Keys and Values, and sets the maximum load factor to be 0.5 
	 */
	public HashMap() {
		this(INIT_SIZE, MAX_LF);
	}

	/**
	 * creates an empty internal table (array) for HashEntry of Keys and Values, and sets the maximum load factor to be lf
	 * @param lf load factor
	 */
	public HashMap(double lf) {
		this(INIT_SIZE, lf);
	}

	/**
	 * creates an empty internal table (array) for HashEntry of Keys and Values with initial capacity cap, and sets the maximum load factor to be 0.5
	 * @param cap capacity
	 */
	public HashMap(int cap) {
		this(cap, MAX_LF);
	}

	/**
	 * creates an empty internal table (array) for HashEntry of Keys and Values with initial capacity cap, and sets the maximum load factor to be lf.
	 * @param cap capacity
	 * @param lf load factor
	 */
	public HashMap(int cap, double lf) {
		table = (HashEntry<K, V>[]) new HashEntry[cap];
		maxLoadFactor = lf;

		for(int i = 0; i < cap; i++) {
			table[i] = E;
		}
	}

	/**
	 * returns a hash index for the specified key
	 * @param key
	 * @return a hash index for the specified key
	 */
	public int hash(K key) {
		//For easy testing, use a simple hash method
		//return (key.toString().length() % table.length);

		int hashCode = key.hashCode();

		if (hashCode < 0) {
			hashCode *= -1;
		}

		return hashCode % table.length;
	}

	/**
	 * places the value with the associated key in the map 
	 * @param key key
	 * @param value value
	 * @return the value originally associated with the key if the table already had one, otherwise returns null
	 */
	public V put(K key, V value) {
		//is the current load factor > maxLoad factor? -> rehash
		//was there a deleted index discovered before this? -> create a new hashentry at deleted index
		//was there no deleted index? -> resize() and call put(K, V) recursively

		int hI = hash(key);		//primary hash index for key

		//start quadratic probing
		int k = 1;	//base of exponent
		int index = hI;
		int deletedIndex = -1;		//first deleted index while probing is stored here
		double currentLF;

		//while the sequence is not exhausted
		while (k < table.length) {
			//is the index empty
			if (table[index] == E) {
				//was there a deleted index discovered before this? -> create a new hashentry at deleted index
				if (deletedIndex != -1) {
					table[deletedIndex] = new HashEntry<>(key, value);
				}
				//was there no deleted index? -> create a new hashentry at empty index
				else {
					table[index] = new HashEntry<>(key, value);
				}
				++count;
				currentLF = (count * 1.0) / (table.length * 1.0);
				if (currentLF >= maxLoadFactor) { 		//checks to see if a resize is needed 
					rehash();
				}
				return null;
			}
			//is this the first deleted index discovered? -> store this index and continue probing
			else if (table[index] == D && deletedIndex == -1) {
				deletedIndex = index;
			}
			//is the index occupied (is there a collision?)
			else {
				//is the key the same? update value and return old value
				if (table[index].key == key) {
					V oldValue = table[index].value;
					table[index].value = value;
					return oldValue;
				}
			}

			index =  hI + k * k;
			index = index % table.length; 
			++k;
			table[index] = table[index];
		}

		//at this point, sequence is exhausted
		//if there was a deleted index found, place it here
		if (deletedIndex != -1) {
			table[deletedIndex] = new HashEntry<>(key, value);
			++ count;
			currentLF = (count * 1.0) / (table.length * 1.0);
			if (currentLF >= maxLoadFactor) {
				rehash();
			}
			return null;
		}
		//otherwise, rehash and try again
		else {
			rehash();
			return put(key, value);
		}
	}

	/**
	 * returns the array (table) index in which the 'key' is located using quadratic probing, or -1 if not found
	 * @param key key
	 * @return the array (table) index in which the 'key' is located using quadratic probing, or -1 if not found
	 */
	private int indexOf(K key) {
		int hI = hash(key);		//primary hash index for key

		//check if key matches entry key at hI
		if (key.equals(table[hI].key)) {
			return hI;
		}

		//start quadratic probing
		int k = 1;	//base of exponent
		int index = hI;
		HashEntry<K, V> entry = table[index];
		while (k < table.length && entry != E) {

			if (entry != D && key.equals(entry.key)) {
				return index;
			}

			index =  hI + k * k;
			index = index % table.length; 
			++k;
			entry = table[index];
		}

		return -1;
	}

	/**
	 * using the same quadratic probing sequence as in put, determine if there is an entry with matching key
	 * @param key key to check
	 * @return true if the hashmap contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		return (indexOf(key) >= 0);
	}

	/**
	 * returns true if there is an entry with matching value
	 * @param value value to check
	 * @return true if there is an entry with matching value
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < table.length; ++i) {
			if(value.equals(table[i].value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns the value whose associated key matches key
	 * @param key key to check
	 * @return the value whose associated key matches key
	 */
	public V get(K key) {
		int index = indexOf(key);
		if (index >= 0) {
			if(table[index] != D && table[index] != E) {
				return (table[index].value);
			}
		}
		return null;
	}

	/**
	 * removes the key and its associated value from this hashtable
	 * @param key key to remove
	 * @return the removed value, or null if the key does not exist in this hashtable
	 */
	public V remove(K key) {
		int index = indexOf(key);
		if (index >= 0) {
			V returnValue = table[index].value;
			table[index] = D;
			--count;
			return returnValue;
		}
		else {
			return null;
		}
	}

	/**
	 * returns the number of key-value pairs stored in this table
	 * @return the number of key-value pairs stored in this table
	 */
	public int size() {
		return count;
	}

	/**
	 * returns true if the table is empty and false otherwise
	 * @return true if the table is empty and false otherwise
	 */
	public boolean isEmpty() {
		return (count == 0);
	}

	/**
	 * clears the table
	 */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = E;
		}
		count = 0;
	}

	/**
	 *  returns a print-friendly String with entire content of this hashtable
	 *  @return a print-friendly String with entire content of this hashtable
	 */
	public String toString() {
		double currLF = (count * 1.0) / (table.length * 1.0);
		String str = "cur load factor: " + String.format("%.2f", currLF) + "\n" +
				"max load factor: " + String.format("%.2f", maxLoadFactor) + "\n" + 
				"current size   : " + count + "\n";

		for (int i = 0; i < table.length; ++i) {
			if(table[i] != E) {
				if(table[i] == D) {
					str += i + ": DELETED\n";
				}
				else {
					str += i + ": " + table[i].key + ", " + table[i].value + "\n";
				}
			}
		}
		return str;
	}

	/**
	 * returns the smallest prime number greater than 2n
	 * @param n original capacity
	 * @return the smallest prime number greater than 2n
	 */
	private static int nextCapacity(int n) {
		int newSize = n * 2 + 1;

		for (int i = 2; i < newSize; i++)
		{
			if(newSize % i == 0)
			{
				i = 2;
				++newSize;
			}
		}

		return newSize;
	}

	/**
	 * increases the internal storage capacity
	 */
	private void rehash() {
		int newSize = nextCapacity(table.length);
		HashEntry<K, V>[] oldTable = table;
		table = (HashEntry<K, V>[]) new HashEntry[newSize];

		clear();

		for (int i = 0; i < oldTable.length; i++) {
			if(oldTable[i] != E && oldTable[i] != D) {
				put(oldTable[i].key, oldTable[i].value);
			}
		}
	}
}
