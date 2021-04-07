package sorts;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Swaps {
    /*
    private static void debug(int[] arr) {
            StringBuffer buf = new StringBuffer();
            for (int num: arr) {
                buf.append(num); buf.append(" ");
            };
            System.out.println(buf.toString());
    }
    */
    /*
    // consecutive values with known least value (1) are a special sort,
    // since the value tells the correct position
    //
    // scan array (from left or right) checking if value position is correct,
    // if not then swap the value with the one in it's correct position
    // else move to check next position
    //
    // TODO: prove this gives all and minimum swaps, since
    // every swap fixes at least one incorrect position
    // and position corrections are never undone
    //
    // tricky part seems to be proving swaps will not loop
    */
    public static int minimumSwaps(int[] arr) {
        int swaps = 0; // should be < number of values in wrong position
        int position = arr.length-1;

        // debug(arr); // scan starting from right (arr.length-1) or left 0
        while (0 < position) { // check array positions from right
            int value = arr[position];
            if (value != position+1) { // value at position incorrect
                // swap value at position to correct spot (value-1)
                arr[position] = arr[value-1]; // and test swapped value next
                arr[value-1] = value;
                swaps += 1;
                // debug(arr);
             } else {
                     position--; // value at position was correct, move left
             }
         }
         return swaps; // O(n-1) because if n-1 are correct, the n'th is
         // example worst case: 2 3 4 5 6 7 1 takes 6 swaps starting from left
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
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

        int result = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
