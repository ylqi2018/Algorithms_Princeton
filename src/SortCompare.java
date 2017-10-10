/**
 * This program is designed to compare two sorting algorithms with roughly
 * the same order of growth, e.g., insertion sort vs selection sort or mergesort vs. quicksort.
 * Otherwise, various system effects(such as just-in-time compiliation) may have a significant effect.
 * One alternative is to execute with "java -Xint", which forces the JVM
 * to use interpreted execution mode only.
 * @author Administrator
 *
 */

public class SortCompare {
	public static double time(String alg, Double[] a) {
		Stopwatch sw = new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		else if(alg.equals("Selection")) Selection.sort(a);
		else throw new IllegalArgumentException("Invalid algorithm: " + alg);
		return sw.elapsedTime();
	}
	
	// Use alg to sort trials rndom arrays of length n.
	public static double timeRandomInput(String alg, int n, int trials) {
		double total = 0.0;
		Double[] a = new Double[n];
		// Perform one experiment (generate and sort an array)
		for(int t=0; t<trials; t++) {
			for(int i=0; i<n; i++) {
				a[i] = StdRandom.uniform(0.0, 1.0);
			}
			total += time(alg, a);
		}
		return total;
	}
	
	public static double timeSortedInput(String alg, int n, int trials) {
		double total = 0.0;
		Double[] a = new Double[n];
		for(int t=0; t<trials; t++) {
			for(int i=0; i<n; i++) {
				a[i] = 1.0*i;
			}
			total += time(alg, a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = args[0];
		String alg2 = args[1];
		int n = Integer.parseInt(args[2]);
		int trials = Integer.parseInt(args[3]);
		double time1; 
		double time2;
		if(args.length == 5 && args[4].equals("sorted")) {
			time1 = timeSortedInput(alg1, n, trials);
			time2 = timeSortedInput(alg2, n, trials);
		} else {
			time1 = timeRandomInput(alg1, n, trials);
			time2 = timeRandomInput(alg2, n, trials);
		}
		
		StdOut.printf("For %d random Doubles\n %s is ", n, alg1);
		StdOut.printf("%.1f times faster than %s\n", time2/time1, alg2);
	}
}
