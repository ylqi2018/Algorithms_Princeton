import java.util.Iterator;
import java.util.NoSuchElementException;
//https://stackoverflow.com/questions/16207427/compile-error-cannot-find-symbol-in-stdin-and-stdout
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings implements Iterable<String> {
    private String[] a; // holds the items
    private int N; // numbers of items  in stack
    
    // create an empty stack with given capacity
    public FixedCapacityStackOfStrings(int capacity) {
        a = new String[capacity];
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0; // if N==0, then the stack is empty
    }
    
    public boolean isFull() {
        return N == a.length; // if N==a.length, then the stack is full
    }
    
    public void push(String item) {
        a[N++] = item; // put item in array a and then increment N
    }
    
    public String pop() {
        return a[--N];
    }
    
    public String peek() {
        return a[N-1];
    }
    
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<String> {
        private int i = N - 1;
        
        public boolean hasNext() {
            return i >= 0;
        }
        
        public String next() {
            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(max);
        
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) {
                stack.push(item);
            } else if(stack.isEmpty()) {
                StdOut.println("Bad Input");
            } else {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println();
        
        // print what's left on the stack
        StdOut.print("Left on stack");
        for(String s: stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }   
}