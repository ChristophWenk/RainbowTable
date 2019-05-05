package krysi;

import java.time.Duration;
import java.time.Instant;

/**
 * The main class to execute the application
 */
public class Program {

    /**
     * Main Method for program execution.
     * 1. Build the rainbow table
     * 2. Find the password to a given string
     *
     * @param args The optional JVM arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();


        // Initialize variables
        char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int passwordLength = 7;
        int chainLength = 2000;
        int r = 2000;
        Tools tools = new Tools();
        Rainbow rainbow = new Rainbow(passwordLength, z, chainLength, r);

        // Get the hash to find
        String hashValue = tools.readHashValue("src/main/resources/hashValue.txt");

        // Build the rainbow table
        rainbow.createRainbowTable();

        // Find the password for the given hash
        String firstValue = rainbow.findFirstValue(hashValue);
        String password = rainbow.findPassword(firstValue, hashValue);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("You are looking for a possible password for the hash: " + hashValue);
        System.out.println("The password might be: " + password);
        System.out.println("This took " + timeElapsed + "ms");
    }
}
