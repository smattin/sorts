package sorts;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Swaps {
    private static void debug(int[] arr) {
            StringBuffer buf = new StringBuffer();
            for (int num: arr) {
                buf.append(num); buf.append(" ");
            };
            System.out.println(buf.toString());
    }

    // consecutive values with known least value (1) are a special sort,
    // since the value tells the correct position
    public static int minimumSwaps(int[] arr) {
        int swaps = 0; // should be < number of values in wrong position
        int nsorted = 0; // array positions < nsorted are checked correct

        debug(arr); // scanning could start either right (arr.length-1) or left
        while (nsorted < arr.length) { // scan array from left (position 0)
            if (arr[nsorted] == nsorted+1) { // sorted
                    nsorted++;
            } else { // swap value at position nsorted to correct spot (value-1)
                int value = arr[nsorted]; // swap positions value-1 and nsorted
                arr[nsorted] = arr[value-1]; // and test swapped value next
                arr[value-1] = value;
                swaps += 1;
                debug(arr);
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
