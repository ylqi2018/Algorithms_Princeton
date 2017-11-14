package MergeSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	
	// This class should not be instantiated
	private MergeBU() {}
	
	// stably merge a[lo..mid] with a[mid+1...hi] using aux[lo..hi]
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		// copy to aux[]
		for(int k=lo; k<=hi; k++) {
			aux[k] = a[k];
		}
		// merge back to a[]
		int i = lo;
		int j = mid + 1;
		
		for(int k=lo; k<=hi; k++) {
			if(i > mid) a[k] = aux[j++];
			else if(j > hi) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static void sort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[n];
		for(int len = 1; len < n; len *= 2) {
			for(int lo=0; lo<n-len; lo+=len+len) {
				int mid = lo+len-1;
				int hi = Math.min(lo+len+len-1, n-1);
				merge(a, aux, lo, mid, hi);
			}
		}
		assert isSorted(a);
	}
	
	// Helper sorting functions
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	// Check if array is sorted - useful for debugging
	private static boolean isSorted(Comparable[] a) {
		for(int i=1; i<a.length; i++) {
			if(less(a[i], a[i-1])) return false;
		}
		return true;
	}
	
	// print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    
    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
	
	
}