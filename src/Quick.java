/**
 * The Quick class provides static methods for sorting an array
 * and select the ith smallest element in an array using quicksort.
 * @author Administrator
 *
 */
public class Quick {
	
/********************************************************************
 * Constructor
 * This class should not be instantiated.
 *******************************************************************/
	private Quick() {}
	
/********************************************************************
 * Rearrange the array in ascending order, using the natural order.
 *******************************************************************/	
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length-1);
		assert isSorted(a);
	}
	
// Quicksort the subarray from a[lo] to a[hi]
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;	// Just one element or no element
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
		assert isSorted(a, lo, hi);
	}
	
// Partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
// and return the index j
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while(true) {
			// Find item on lo to swap
			while(less(a[++i], v)) { 	// The last compare is less(a[hi], v)
				if(i==hi) break;		// Finish comparing
			}
			
			while(less(v, a[--j])) {	// The last compare is less(v, a[lo])
				if(j==lo) break;		// Finish comparing
			}	// redundant since a[lo] acts as sentinel
			
			if(i >= j) break;
			
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

/********************************************************************
 * Rearranges the array so that a[k] contains the kth smallest key;
 * through a[k-1] are less than or equal to a[k];
 * and a[k+1] through a[n-1] are greater than or equal to a[k]
 *******************************************************************/
//	public static Comparable select(Comparable[] a, int k) {
//		if(k<0 || k>=a.length) {
//			throw new IllegalArgumentException("Index is not between 0 and " + a.length + ": " + k);
//		}
//		StdRandom.shuffle(a);
//		int lo = 0;
//		int hi = a.length - 1;
//		while(hi > lo) {
//			int i = partition(a, lo, hi);
//			if(i > k) hi = i - 1;
//			else if(i < k) lo = i + 1;
//			else return a[i];
//		}
//		return a[lo];
//	}

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }
	
/********************************************************************
 * Helper sorting functions.
 *******************************************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	// exchange v and w
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[j];
		a[i] = a[j];
		a[j] = swap;
	}
	
/********************************************************************
 * Check if array is sorted -- useful for debugging.
 *******************************************************************/	
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length-1);
	}
	
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i=lo+1; i<=hi; i++) {
			if(less(a[i], a[i-1])) return false;
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
		Quick.sort(a);
		show(a);
		assert isSorted(a);
System.out.println("Finish isSorted.");		
		// Shuffle
		StdRandom.shuffle(a);
System.out.println("Finish shuffle.");			
		// display results again using select
		StdOut.println();
		for(int i = 0; i<a.length; i++) {
			String ith = (String) Quick.select(a, i);
System.out.println("ith: " + ith);
			StdOut.println(ith);
		}
	}
		
}