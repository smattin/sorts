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

    // Complete the minimumSwaps function below.
    // https://www.hackerrank.com/challenges/minimum-swaps-2/problem
    public static int minimumSwaps(int[] arr) {
        int swaps = 0;

        for (int i=0; i < arr.length; i++) { // for each place in the array
            // debug(arr);
            if (arr[i] != i+1) { // if the number found there is not correct
                swaps += 1;
                int temp = arr[i]; // swap that found number to it's right spot 
                arr[i] = arr[temp-1];
                arr[temp-1] = temp;
                i--;
                debug(arr);
             }
         }
         return swaps;
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
