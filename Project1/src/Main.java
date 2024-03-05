import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static File f1file = new File("f1.txt");
    static  File f2file = new File("f2.txt");
    static  File f3file = new File("f3.txt");
    static  File f4file = new File("f4.txt");
    static  File f5file = new File("f5.txt");
    static File f6file = new File("f6.txt");



    public static void main(String[] args) {

        File[] files = {f1file, f2file, f3file, f4file, f5file, f6file};

        //System.out.println(readFile(f1file));

        //MERGESORT OUTPUT

        /*for (int i = 0; i < files.length; i++) {
            long startTime = System.nanoTime();
            ArrayList<Integer> list = readFile(files[i]);
            System.out.println("Inversions " + mergeSort(list));
            long endTime = System.nanoTime();
            System.out.println("f" + (i + 1) + " TIME RUN: " + (endTime - startTime) / 1000000000 + " SECONDS");

        }
*/
        /*

        //BUBBLE SORT OUTPUT

        for (int i = 0; i < files.length; i++) {
            long startTime = System.nanoTime();
            ArrayList<Integer> list = readFile(files[i]);
            System.out.println("INVERSIONS: " + BubbleSort(list));
            long endTime = System.nanoTime();
            System.out.println("f" + (i + 1) + " TIME RUN: " + (endTime - startTime) / 1000000000 + " SECONDS");
        }

 */

        //QUICK SORT OUTPUT


        for (int i = 0; i < files.length; i++) {
            long startTime = System.nanoTime();
            ArrayList<Integer> list = readFile(files[i]);
sort(list);
System.out.println(list);
            long endTime = System.nanoTime();
            System.out.println("f" + (i + 1) + " TIME RUN: " + (endTime - startTime) / 1000000000 + " SECONDS");
        }
    }
    public static ArrayList<Integer> readFile(File f) {
        ArrayList<Integer> fin = new ArrayList<Integer>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
//StringBuilder s = new StringBuilder();
            String num = br.readLine();
            while(num != null) {
                fin.add(Integer.parseInt(num));
                //s.append(num);
                //s.append(System.lineSeparator());
                num = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(f.toString() + " not found");
            System.exit(-1);
        }


        return fin;
    }

    public static long BubbleSort(ArrayList<Integer> list) {
        long count = 0;
        boolean swapped;
        for(int i = 0; i < list.size(); i++) {
            swapped = false;
            for(int j = i+1; j < list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) > 0) {
                    int temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                    count++;
                    swapped = true;
                }
            }
            if(!swapped)
                break;
        }
        return count;
    }

    private static long merge(ArrayList<Integer> a, ArrayList<Integer> aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays

        // copy to aux[]
        long startTime = System.nanoTime();

        for (int k = lo; k <= hi; k++) {
            //aux[k] = a[k];
            aux.set(k, a.get(k));
        }

        long count = 0;
//System.out.println(aux.size() + " " + a.size());
        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)
                a.set(k, aux.get(j++));
                //a[k] = aux[j++];
            else if (j > hi)
                a.set(k, aux.get(i++));
                //a[k] = aux[i++];
            else if (aux.get(j) < aux.get(i)) {
                a.set(k, aux.get(j++));
                count += mid-i+1;

            }
            else {
                a.set(k, aux.get(i++));
            }
        }
        return count;
        // postcondition: a[lo .. hi] is sorted
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static long mergeSort(ArrayList<Integer> a, ArrayList<Integer> aux, int lo, int hi) {
        long count = 0;
        if (hi <= lo) return count;
        int mid = lo + (hi - lo) / 2;
        count+= mergeSort(a, aux, lo, mid);
        count+= mergeSort(a, aux, mid + 1, hi);
        count+=  merge(a, aux, lo, mid, hi);

        return  count;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static long mergeSort(ArrayList<Integer> a) {
        ArrayList<Integer> aux = new ArrayList<Integer>(a);
        return mergeSort(a, aux, 0, a.size()-1);
    }



    public static void sort(ArrayList<Integer> a) {
        sort(a, 0, a.size() - 1);
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(ArrayList<Integer> a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = a.get(lo);
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a.get(i).compareTo(v);
            if      (cmp < 0)
                Collections.swap(a, lt++, i++);
            else if (cmp > 0)
                Collections.swap(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    //HeapSort

    public static class HeapSort{

        static long inversionsCount = 0;
        public static long heapSort(ArrayList<Integer> pq, long inversionsCount) {

            int n = pq.size();

            // heapify phase
            for (int k = n/2; k >= 1; k--)
                sink(pq, k, n);

            // sortdown phase
            int k = n;
            while (k > 1) {
                inversionsCount = exch(pq, 1, k--);
                sink(pq, 1, k);
            }
            return inversionsCount;
        }

        /***************************************************************************
         * Helper functions to restore the heap invariant.
         ***************************************************************************/

        private static void sink(ArrayList<Integer> pq, int k, int n) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && less(pq, j, j+1)) j++;
                if (!less(pq, k, j)) break;
                exch(pq, k, j);
                inversionsCount+=(j-k)/2;
                k = j;
            }
        }

        /***************************************************************************
         * Helper functions for comparisons and swaps.
         * Indices are "off-by-one" to support 1-based indexing.
         ***************************************************************************/
        private static boolean less(ArrayList<Integer> pq, int i, int j) {
            return pq.get(i-1).compareTo(pq.get(j-1)) < 0;
        }

        private static long exch(ArrayList<Integer> pq, int i, int j) {

            int swap = pq.get(i-1);
            pq.set(i-1, pq.get(j-1));
            pq.set(j-1, swap);
            return inversionsCount;
        }
    }



    // Driver program
}



// Driver program
