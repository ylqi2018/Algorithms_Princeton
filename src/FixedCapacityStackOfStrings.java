import java.util.Iterator;
import java.util.NoSuchElementException;
//https://stackoverflow.com/questions/16207427/compile-error-cannot-find-symbol-in-stdin-and-stdout
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings implements Iterable<String> {
    private String[] a; // holds the items
    private int N; // numbers of items  in stack
    
    /**
     * Create an empty stack with given capacity
     * @param capacity
     */
    public FixedCapacityStackOfStrings(int capacity) {
        a = new String[capacity];
        N = 0;
    }
    
    /**
     * Test if this stack is empty.
     * @return
     */
    public boolean isEmpty() {
        return N == 0; // if N==0, then the stack is empty
    }
    
    /**
     * Test if this stack is full.
     * @return
     */
    public boolean isFull() {
        return N == a.length; // if N==a.length, then the stack is full
    }
    
    /**
     * Pushes an item onto the top of this stack.
     * @param item
     */
    public void push(String item) {
        a[N++] = item; // put item in array a and then increment N
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this fuction.
     * @return
     */
    public String pop() {
        return a[--N];
    }
    
    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return
     */
    public String peek() {
        return a[N-1];
    }
    
    /**
     * 
     */
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<String> {
        private int i = N - 1;
        
        /**
         * Return true if the iteration has more elements
         */
        public boolean hasNext() {
            return i >= 0;
        }
        
        /**
         * Return the next element in the iteration.
         */
        public String next() {
            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
        
        /**
         * Removes from the underlying collection the last element returned by this iterator.
         */
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