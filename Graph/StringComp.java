/**
 * Ryzeson Maravich
 * StringComp.java
 * String Comparator
 */
import java.util.Comparator;

public class StringComp implements Comparator<String> {

	@Override
	/**
	 * compares to strings
	 * @return negative if o1 < o2, zero if o1 == o2, and positive if o1 > o2
	 */
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
