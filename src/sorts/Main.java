package sorts;

import org.apache.commons.cli.*;
import org.junit.runner.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);


    public static Options options() {
        Options options = new Options();

        options.addOption("t", false, "run tests");
        options.addOption("v", false, "verbose");
        options.addOption(Option.builder("p")
                .longOpt( "max_positions" )
                .desc( "use this SIZE partitions of array when calculating swaps"  )
                .hasArg()
                .argName( "SIZE" )
                .build());

        return options;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Options options = options();
        try {
            CommandLine cmd = new DefaultParser().parse( options, args);
            if (cmd.hasOption("t")) {
                Swaps.testing = true;
                JUnitCore.main("sorts.TestSwaps");
            }

            Swaps.verbose = cmd.hasOption("v");
            if (cmd.hasOption("max_positions")) {
                Swaps.max_positions = Integer.parseInt(cmd.getOptionValue("max_positions"));
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "swaps", options );
            System.exit(1);
            // e.printStackTrace();
        }

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

        int result = Swaps.minimum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
