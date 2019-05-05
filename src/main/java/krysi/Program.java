package krysi;

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

        // Initialize variables
        char[] z = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int passwordLength = 7;
        int chainLength = 2000;
        int r = 2000;
        Tools tools = new Tools();
        Reduction reduction = new Reduction(passwordLength, z);
        Rainbow rainbow = new Rainbow(passwordLength, z, chainLength, r);

        // Get the hash to find
        String hashValue = tools.readHashValue("src/main/resources/hashValue.txt");

        // Build the rainbow table and find the hash
        rainbow.createRainbowTable();
        //String password = rainbow.matchInput(hashValue);


        String firstValue = rainbow.findFirstValue(hashValue);
        String password = rainbow.findPassword(firstValue, hashValue);

        System.out.println(password);
    }
}
