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

    /* see if given strings contain the same chars
    static boolean anagrammatic(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        char c1[] = s1.toCharArray();
        char c2[] = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1,c2);
    }

    static List<String> substrings(String s) {
        int end = s.length();
        List<String> sss = new ArrayList<String>();

        for (int i=0; i<=end; i++) {
            for (int j=i+1; j<=end; j++) {
		String sub = s.substring(i,j);
                sss.add(sub);
	        // Anna.debug.log(java.util.logging.Level.SEVERE,String.format("%s %d %d",sub,i,j));
            }
        }

	sss.remove(s); // strict subsets
        return sss;
    }
    */

    // Complete the minimumSwaps function below.
    // https://www.hackerrank.com/challenges/minimum-swaps-2/problem
    public static int minimumSwaps(int[] arr) {
        int ret = 0;

        return ret;
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
