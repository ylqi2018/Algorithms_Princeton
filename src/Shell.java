/**
 * Uses increment sequence proposed by Sedgewick and Incerpi.
 * The nth element of the sequence is the smallest integer >= 2.5^n
 * that is relatively prime to all previous terms in the sequence.
 * For example, incs[4] is 41 because 2.5^4=39.0625 and 41 is the next integer 
 * that is relatively prime to 3, 7, and 16
 * @author Administrator
 * The class provides static methods for sorting an array using Shellsort
 * with Knuth's increment sequence (1, 4, 13, 40, ...)
 * 
 */
public class Shell {
	// This class should not be instantiated.
	private Shell() {};
	
	/**
	 * Rearranges the array in ascending order, using the natual order
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		
		//3x+1 increment sequence: 1, 4, 13, 40, 121, 363, 1093, ...
		int h = 1;
		while(h < n/3) {
			h = 3*h + 1;
		}
		
		while(h >= 1) {
			// h-sort the array
			for(int i=h; i<n; i++) {
				for(int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			assert isHsorted(a, h);
			h /= 3;
		}
		assert isSorted(a);
	}
	
/**************************************************
 * Helper sorting fucntions.
 **************************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

/**************************************************
 * Check if array is sorted -- useful for debugging.
 **************************************************/
	private static boolean isSorted(Comparable[] a) {
		for(int i=1; i<a.length; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	// is the array h-sorted?
	private static boolean isHsorted(Comparable[] a, int h) {
		for(int i=h; i<a.length; i++) {
			if(less(a[i], a[i-h])) return false;
		}
		return true;
	}
	
	private static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			StdOut.println(a[i]);
		}
	}
	
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Shell.sort(a);
		show(a);
	}
	
}
