package sorts;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Swaps {
    //
    static boolean verbose = false; // command line option '-v'
    static boolean testing = false; // command line option '-t'

    private static void debug(int[] arr) {
        if (verbose) {
            StringBuilder buf = new StringBuilder();
            for (int num : arr) {
                buf.append(num);
                buf.append(" ");
            }
            System.out.println(buf);
        }
    }
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
        return value - min_value; // correct position for value
    }

    // if array.length greater max_array, split
    public static int max_positions = 10000000;

    /* TODO: apart from debugging support,
     *   it should not be necessary to actually perform swaps,
     *   just to figure out how many are needed
     */
    public static int swap(int i, int j, int[] arr) {
        // swap values at positions i and j in arr
        // return the swapped value arr[j]
        int temp = arr[j];
        // if (verbose) {
            arr[j] = arr[i];
            arr[i] = temp;
            debug(arr);
        // }
        return temp;
    }

    // keep swapping incorrect values at position in array to their correct positions
    // corrects position for all incorrect values swapped to that position in array
    // and return array of corrected positions for each swap
    public static List<Integer> swap_incorrect_values(int position, int[] array) {
        List<Integer> swaps = new ArrayList<>(array.length);

        int value = array[position];
        while (position != correct_position(value)) {
            value = swap(position, correct_position(value), array);
            swaps.add(value);
            // as long as we don't undo swaps, loop terminates
        }
        return swaps;
    }

    public static int minimumSwaps(int[] array, IntStream positions) {
        int swaps = 0;
        Set<Integer> positionsToCheck = positions.boxed().collect(Collectors.toSet());
        while (!positionsToCheck.isEmpty()) {
            Integer pos = positionsToCheck.iterator().next(); // choose position
            positionsToCheck.remove(pos); // without replacement

            List<Integer> fixedPositions = swap_incorrect_values(pos, array);
            swaps += fixedPositions.size();
            fixedPositions.forEach(positionsToCheck::remove);
        }
        return swaps;
        /*
        return positions
                .map(pos -> swap_incorrect_values(pos, array).size())
                .sum();

         */
    }

    private static class Swapper implements Callable<Integer> {
        private final int[] array;
        private final IntStream positions;

        public Swapper(int[] array, IntStream positions) {
            this.array = array;
            this.positions = positions;
        }

        @Override
        public Integer call() {
            return minimumSwaps(array, positions);
        }
    }

    // calculate number of threads to use
    public static int concurrency(int[] arr) {
        // TODO: factor in system resources (cores)
        int nThreads = arr.length / max_positions;
        if (arr.length % max_positions != 0) {
            nThreads += 1;
        }
        return nThreads;
    }
    public static int minimum(int[] arr) throws InterruptedException {
        int swaps = 0;
        // select a random position in array without replacement
        //     get the correct value into that position
        //     recording the number of swaps it took
        // repeat

        debug(arr);
        /*
         for a large input array, run swapper in parallel on subsets of positions
         NOT on subsets of array because the correct positions might not be in subset
        */
        int min = 0;
        int max = arr.length;
        IntStream positions = IntStream.range(min, max);

        int nThreads = concurrency(arr);
        if (1 < nThreads) {

            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
            List<Callable<Integer>> tasks = new ArrayList<>(nThreads);

            // create tasks for array position subsets of max_positions
            while (min < max) {

                max = Math.min(arr.length, min + max_positions);

                //System.err.println(String.format("min %d max %d",min,max));
                positions = IntStream.range(min, max);
                tasks.add(new Swapper(arr, positions));

                min = max; // slide positions window
                max = Math.min(arr.length, max + max_positions);
            }

            // execute tasks and sum returned swap counts
            swaps = executor.invokeAll(tasks).stream().map(futureSwapCount -> {
                try { // why can't we move exception handling out?
                    return futureSwapCount.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return 0;
            }).mapToInt(i -> i).sum(); // unbox Integers and sum counts of swaps

            executor.shutdown();
        } else { // logically redundant, but probably more efficient
            swaps += minimumSwaps(arr, positions);
        }

        return swaps; // O(n-1) because if n-1 are correct, the nth is
        // example worst case: 2 3 4 5 6 7 1 takes 6 swaps
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Main.main(args);
    }
}
