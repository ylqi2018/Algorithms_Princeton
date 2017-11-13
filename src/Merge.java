/**********************************************************
 * Sorts a sequence of strings from standard input using mergesort.
 * @author Xiaohan
 *
**********************************************************/

/**
 * This class provides static methods for sorting an array using mergesort.
 * @author Xiaohan
 *
 */
public class Merge {
	private Merge() {}
	
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// Precondition: a[lo...mid] and a[mid+1...hi] are sorted subarrays.
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);
		
		// Copyt to aux[]
		for(int k=lo; k<=hi; k++) {
			aux[k] = a[k];
		}
		
		// merge back to a[]
		int i = lo;
		int j = mid+1;
		for(int k=lo; k<=hi; k++) {
			if(i > mid) {
				a[k] = aux[j++];
			} else if(j > hi) {
				a[k] = aux[i++];
			} else if(less(a[i], a[j])) {
				a[k] = a[i++];
			} else {
				a[k] = a[j++];
			}
		}
		// pose condition: a[lo...hi] is sorted
		assert isSorted(a, lo, hi);
	}
	
	// mergesort a[lo...hi] using auxiliary array aux[lo...hi]
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length-1);
		isSorted(a);
	}
	
/**********************************************************
 * Check if array is sorted -- useful for debugging
 *********************************************************/
	// Comparable: This interface imposes a total ordering on the objects 
	// of each class that implements it.
	private static boolean isSorted(Comparable[] a) {
		// 0 is the first index of the subarray
		// a.length-1 is the last index of the subarray
		return isSorted(a, 0, a.length-1);
	}
	
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i=lo+1; i<=hi; i++) {
			if(less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}

/**********************************************************
 * Helper sorting function.
 *********************************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
/**********************************************************
 * Index mergesort.
 *********************************************************/	
	// stably merge a[lo...mid] with a[mid+1...hi] using aux[lo...hi]
	private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
		// copy to aux[]
		for(int k=lo; k<=hi; k++) {
			aux[k] = index[k];
		}
		
		// merge back to a[]
		int i = lo;
		int j = mid+1;
		for(int k=lo; k<=hi; k++) {
			if(i > mid) {
				index[k] = aux[j++];
			} else if(j > hi) {
				index[k] = aux[i++];
			} else if(less(a[aux[j]], a[aux[i]])) {
				index[k] = aux[j++];
			} else {
				index[k] = aux[i++];
			}
		}
	}
	
	public static int[] indexSort(Comparable[] a) {
		int n = a.length;
		int[] index = new int[n];
		for(int i=0; i<n; i++) {
			index[i] = i;
		}
		int[] aux = new int[n];
		sort(a, index, aux, 0, n-1);
		return index;
	}
	
	private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi-lo)/2;
		sort(a, index, aux, lo, mid);
		sort(a, index, aux, mid+1, hi);
		merge(a, index, aux, lo, mid, hi);
	}
	
	private static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			StdOut.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Merge.sort(a);
		show(a);
	}
		
}
