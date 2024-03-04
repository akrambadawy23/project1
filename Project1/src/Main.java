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

    for (int i = 0; i < files.length; i++) {
        long startTime = System.nanoTime();
        ArrayList<Integer> list = readFile(files[i]);
        System.out.println("INVERSIONS: " + QuickSort(list));
        // if(i < 2)
        //System.out.println(list);

        long endTime = System.nanoTime();
        System.out.println("f" + (i + 1) + " TIME RUN: " + (endTime - startTime) / 1000000000 + " SECONDS");

    }

        for (int i = 0; i < files.length; i++) {
            long startTime = System.nanoTime();
            ArrayList<Integer> list = readFile(files[i]);
            System.out.println("INVERSIONS: " + BubbleSort(list));
            // if(i < 2)
            //System.out.println(list);

            long endTime = System.nanoTime();
            System.out.println("f" + (i + 1) + " TIME RUN: " + (endTime - startTime) / 1000000000 + " SECONDS");

        }

        for (int i = 0; i < files.length; i++) {
            long startTime = System.nanoTime();
            ArrayList<Integer> list = readFile(files[i]);
            System.out.println("INVERSIONS: " + mergeSort(list));
            // if(i < 2)
            //System.out.println(list);

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

        for(int i = 0; i < list.size(); i++) {
            for(int j = i+1; j < list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) > 0) {
                    int temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                    count++;
                }
            }
        }



        return count;
    }



    public static int QuickSort(ArrayList<Integer> file) {
        //Collections.shuffle(file);
        return sort(file, 0, file.size()-1, 0);
    }

    public static int sort(ArrayList<Integer> a, int lo, int hi, int count) {
        //int count = 0;
        if(hi <= lo) return count;
        int lt = lo;
        int gt = hi;
        //int mid = (int)(lo + (double)((hi-lo)/2));
        //if(a.get(hi) < a.get(lo))
            //Collections.swap(a, lo, hi);
Random rand = new Random();
int b;
b = rand.nextInt(lo, hi);
//Collections.swap(a, b, lo);

        int v = a.get(lo);
        if(a.get(hi) < a.get(lo))
            v = a.get(hi);
        //System.out.println(v);

        for(int j = lo; j < hi; j++) {
            if(a.get(j).compareTo(a.get(j+1)) > 0)
                count++;
        }

        int i = lo+1;
        while(i <= gt) {



            if(a.get(i).compareTo(v) < 0) {
                int temp = a.get(lt);
                a.set(lt, a.get(i));
                a.set(i, temp);
//count++;
                lt++;
                i++;
            } else if(a.get(i).compareTo(v) > 0) {
                int temp = a.get(i);
                a.set(i, a.get(gt));
                a.set(gt, temp);
                //count++;
                gt--;
            }
            else
                i++;


        }
        sort(a, lo, lt-1, count);
        sort(a, gt+1, hi, count);
        return count;
    }


    public static int HeapSort(ArrayList<Integer> file) {
        int count = 0;

        return count;
    }

    private static int merge(ArrayList<Integer> a, ArrayList<Integer> aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        int count = 0;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k));
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)
                a.set(k, a.get(j++));
            else if (j > hi)
                a.set(k, a.get(i++));
            else if (a.get(j) <= a.get(i)) {
                a.set(k, aux.get(j++));
            }
            else {
                a.set(k, aux.get(i++));
                count += (mid+1) - (i);
            }
        }
        return count;
        // postcondition: a[lo .. hi] is sorted

    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static int mergeSort(ArrayList<Integer> a, ArrayList<Integer> aux, int lo, int hi) {
        //if (hi <= lo) return;
        int count = 0;
        if(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            count += mergeSort(a, aux, lo, mid);
            count += mergeSort(a, aux, mid + 1, hi);
            count += merge(a, aux, lo, mid, hi);
        }
        return count;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static int mergeSort(ArrayList<Integer> a) {
        ArrayList<Integer> aux = new ArrayList<Integer>();
        return mergeSort(a, aux, 0, a.size()-1);
    }

}
