/**
 * HashEntry.java
 * 10-9-2019
 * Ryzeson Maravich
 * 
 * Represents a hash table entry with key (of K type) and value (of V type) associated with the key
 */

package hashmap;

//generic?
public class HashEntry<K, V> {

	K key;
	V value;

	public HashEntry() {

	}

	public HashEntry(K k, V v) {
		key = k;
		value = v;
	}


	public String toString() {
		return (key.toString() + "," + value.toString());
	}



}
