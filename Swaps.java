package sorts;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Swaps {

    static java.util.logging.Logger debug = java.util.logging.Logger.getGlobal();

    // Complete the minimumSwaps function below.
    // https://www.hackerrank.com/challenges/minimum-swaps-2/problem
    public static int minimumSwaps(int[] arr) {
        int swaps = 0;
        int[] sorted = new int[arr.length];
        for (int i=1; i <= arr.length; i++) {
            sorted[i-1] = i;
        }
        //List<Integer> list;
        for (int i: sorted) {
            // TODO: optimize pos calc to not require this - too slow    
            //list = Arrays.stream(arr).boxed().collect(java.util.stream.Collectors.toList());
	        //Swaps.debug.log(java.util.logging.Level.SEVERE,
            //    String.format("swap %d from %s",swaps,list.toString()));
            if (arr[i-1] != i) {
                swaps += 1;
                int pos = 0;
                for (int j=i-1; j<arr.length; j++) {
                    if (arr[j]==i) {
                        pos = j;
                        break;
                    }

                }
                if (-1 == pos) {
	                //Swaps.debug.log(java.util.logging.Level.SEVERE,
                    //                String.format("indexOf(%d) not found in swap %d from %s",i,swaps,list.toString()));
                }
	            //Swaps.debug.log(java.util.logging.Level.SEVERE,String.format("swap number %s between indexes %d %d",swaps,i-1,pos));
                int temp = arr[i-1];
                arr[i-1] = arr[pos];
                arr[pos] = temp;
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
