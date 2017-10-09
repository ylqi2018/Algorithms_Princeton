import java.util.Comparator;

/**
 * It is not suitable for sorting large arbitrary arrays.
 * 
 * The sorting algorithm is stable and uses O(1) extra memory.
 * @author Administrator
 *
 */
public class Insertion {

	// This class should not be instantiated.
	private Insertion() {}
	
	/**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
	public static void sort(Comparable[] a) {
		int n = a.length;
		for(int i=0; i<n; i++) {
			for(int j=i; j>0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

    /**
     * Rearranges the subarray a[lo..hi) in ascending order, using the natural order.
     * @param a the array to be sorted
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a, lo, hi);
    }	

    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1], comparator); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }
    
    /**
     * Rearrange the subarray a[lo...hi) in ascending order, using a comparator
     * @param a
     * @param lo
     * @param hi
     * @param comparator
     */
    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
    	for(int i=lo; i<hi; i++) {
    		for(int j=i; j>lo && less(a[j], a[j-1], comparator); j--) {
    			exch(a, j, j-1);
    		}
    	}
    	assert isSorted(a, lo, hi, comparator);
    }
    
    // Return a permutation that gives the elements in a[] in ascending order
    // do not change the original array a[]
    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a, the array
     * @return a permutation
     */
    public static int[] indexSort(Comparable[] a) {
    	int n = a.length;
    	int[] index = new int[n];
    	for(int i=0; i<n; i++) {
    		index[i] = i;
    	}
    	for(int i=0; i<n; i++) {
    		for(int j=i; j>0 && less(a[index[i]], a[index[j-1]]); j--) {
    			exch(index, j, j-1);
    		}
    	}
    	return index;
    }
    
/****************************************
 * Helper sorting functions
 ****************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	// is v < w ?
	private static boolean less(Object v, Object w, Comparator comparator) {
		return comparator.compare(v, w) < 0;
	}
	
	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	// exchange a[i] and a[j], for indirect sort
	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
/****************************************
 * Check if array is sorted - useful for debugging.
 ****************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length);
	}
	
	// is the array[lo...hi) sorted
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i=lo+1; i<hi; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, 0, a.length, comparator);
	}
	
	// is the array a[lo...hi) sorted?
	private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
		for(int i=lo+1; i<hi; i++) {
			if(less(a[i], a[i-1], comparator)) return false;
		}
		return true;
	}
	
/****************************************
 * print array to standart output
 ****************************************/
	private static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			StdOut.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Insertion.sort(a);
		show(a);
	}
	
}
