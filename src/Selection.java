//package week2;

import java.util.Comparator;

public class Selection {
	
	// This class should not be instantiated.
	private Selection() {}
	
	/**
	 * Rearranges the array in ascending order, using the natual order.
	 * @param a
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		for(int i=0; i<n; i++) {
			int min = i;
			for(int j=i+1; j<n; j++) {
				if(less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}
	
	public static void sort(Object[] a, Comparator comparator) {
		int n = a.length;
		for(int i=0; i<n; i++) {
			int min = i;
			for(int j=i+1; j<n; j++) {
				if(less(comparator, a[j], a[min])) min = j;
			}
			exch(a, i, min);
			assert isSorted(a, comparator, 0, i);
		}
		assert isSorted(a, comparator);
	}
	
/**********************************************
 * Helper sorting function
 **********************************************/
	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	// is v < w ?
	private static boolean less(Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0;
	}
	
	// exchange a[i] and a[j]
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
/***************************************************************************
*  Check if array is sorted - useful for debugging.
***************************************************************************/
	// Is the array a[] sorted?
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length-1);
	}
	
	// Is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for(int i=lo+1; i<=hi; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}

	// Is tha array[] a sorted?
	private static boolean isSorted(Object[] a, Comparator comparator) {
		return isSorted(a, comparator, 0, a.length-1);
	}
	
	// Is the array[] a sorted?
	private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
		for(int i=lo+1; i<=hi; i++) {
			if(less(comparator, a[i], a[i-1])) return false;
		}
		return true;
	}
	
	//print array to standard output
	private static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			StdOut.println(a[i]);
		}
	}
	
	//http://www.cnblogs.com/lyf22/p/5405294.html
	//在common选项里有一个Standard input and output,把Input File选好路径点击run就行了。
	//但是run了之后要在控制台中按ctrl + Z程序才能运行完成。
	/**
	 * Reads in a sequence of strings from standard input;
	 * selection sorts them;
	 * and prints them to standard output in ascending order.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Selection.sort(a);
		show(a);
	}
}
