import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Dups {

    public static Set<Character> toSet(final String string) {
        Set<Character> set = new HashSet<Character>();
        for(char c : string.toCharArray()) {
            set.add(c);
        }
        return set;
    }
    
    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        Set<Character> set1 = toSet(s1);
	System.err.println(set1);
        set1.retainAll(toSet(s2));
	System.err.println(set1);
        Boolean shared = ! set1.isEmpty();
        
        return shared ? "YES": "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
 
        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
