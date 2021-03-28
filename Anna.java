import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Anna {

    static java.util.logging.Logger debug = java.util.logging.Logger.getGlobal();

    /* see if given strings contain the same chars */
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

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int ret = 0;

        List<String> substring = substrings(s);
	// List<String> firstHalf = substring.subList(0,substring.size()/2);

        // Anna.debug.log(java.util.logging.Level.SEVERE,Arrays.toString(substring.toArray()));
	for (int i=0; i<substring.size(); i++) {
		
	    for (int j=i; j<substring.size(); j++) {

		String s1 = substring.get(i);
		String s2 = substring.get(j);
		boolean found = i != j && anagrammatic(s1,s2);
                if (found) {
                    ret = ret+1;
		    // Anna.debug.log(java.util.logging.Level.SEVERE,String.format("%s %d %s %d %b",s1,i,s2,j,found));
		}
            }
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
