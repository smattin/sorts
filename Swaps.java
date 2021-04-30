package sorts;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Swaps {
    //
    static final boolean verbose = true;

    private static void debug(int[] arr, int[] positions) {
        if (verbose) {
            Arrays.sort(positions,0,positions.length);
            StringBuffer buf = new StringBuffer();
            for (int pos: positions) {
                buf.append(arr[pos]); buf.append(" ");
            };
            System.out.println(buf.toString());
        }
    }
    private static void debug(int[] arr) {
        if (verbose) {
            StringBuffer buf = new StringBuffer();
            for (int num: arr) {
                buf.append(num); buf.append(" ");
            };
            System.out.println(buf.toString());
        }
    }
    //
    /*
    // consecutive values with known least value (1) are a special sort,
    // since the value tells the correct position
    //
    // for each position, check
    //   if value at position is correct,
    //   then move to check next position
    //   else swap that position with one at the value
    //   while the new value at position is not correct, move it
    //   check next position
    //
    // NOTE: the order of positions checked should not matter,
    // but all except the last need checking.
    // The last will be correct if all the others are.
    //
    // TODO: prove this gives all and minimum swaps, since
    // every swap fixes at least one incorrect position
    // and position corrections are never undone
    //
    // tricky part seems to be proving swaps will not loop
    */

    // if we were not given min_value=1, we could scan array for it first
    static final int min_value = 1;
    private static int correct_position(int value) {
        return value-min_value; // correct position for value
    }
    static final int max_array = 6; // if array.length greater max_array, split

    public static void swap(int i,int j, int[] arr) {
            int[] ij = {i,j};
            //debug(ij);
            //debug(arr);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            debug(arr);
    }

    // keep swapping incorrect values at pos in arr to their correct positions
    // results in the correct value at pos in arr
    // and returns the number of swaps performed
    public static int swap_incorrect_values(int pos,int[] arr) {
        int swaps = 0;

        int value = arr[pos];
        int correct = correct_position(value);
        while (pos != correct) {
            swap(pos,correct,arr);
            swaps++;

            // as long as we don't undo swaps, loop terminates
            value = arr[pos]; // swapped value
            correct = correct_position(value); // update correct position
        }
        return swaps;
    }

    public static int minimumSwaps(int[] arr, IntStream positions) {
        return positions
               .map(pos ->  swap_incorrect_values(pos,arr))
               .sum();
    }

    public class Swapper implements Callable<Integer> {
        private int[] array;
        private IntStream positions;

        public Swapper(int[] array, IntStream positions) {
            this.array = array;
            this.positions = positions;
        }
        @Override
        public Integer call() throws Exception {
            return minimumSwaps(array,positions);
        }
    }

    public int minimumSwaps(int[] arr) throws InterruptedException  {
        int swaps = 0;
        // select a random position in array without replacement
        //     get the correct value into that position
        //     recording the number of swaps it took
        // repeat

        debug(arr);

        /* if array is large, maybe use multiple concurrent tasks
         * for subsets of positions
         * (not of array because values might not belong in subset)
         *

        for (int element: arr) { // hack: use array values to iterate positions
            int position = correct_position(element); // select any position
            swaps += swap_incorrect_values(position,arr);
         }

         */

        if (arr.length > max_array) {
            int nThreads = arr.length/max_array+1;
             ExecutorService executor=Executors.newFixedThreadPool(nThreads);
             List<Future<Integer>> swapCounts = new ArrayList<Future<Integer>>();

            int min = 0;
            int max = arr.length;
            while (min < max) {
    
                max = Math.min(arr.length,min+max_array);
     
                //System.err.println(String.format("min %d max %d",min,max));
                IntStream positions = IntStream.range(min,max);
                Swapper task = new Swapper(arr,positions);
                Future<Integer> nSwaps = executor.submit(task);
                swapCounts.add(nSwaps);
    
                min = max;
                max = Math.min(arr.length,max+max_array);
            }
            swaps = swapCounts.stream().map(f -> {
                try {
                    return f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return 0;
            }).mapToInt(i -> i).sum();
        } else {
            swaps += minimumSwaps(arr, IntStream.range(0,arr.length));
        }

        return swaps; // O(n-1) because if n-1 are correct, the n'th is
        // example worst case: 2 3 4 5 6 7 1 takes 6 swaps starting from left
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = new Swaps().minimum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
