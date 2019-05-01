package krysi;

/**
 * This class offers all methods needed for a reduction step
 */
public class Reduction {
    private int passwordLength;
    private char[] z;

    private Tools tools;

    /**
     * Construct the Reduction
     *
     * @param passwordLength The length of a possible password
     * @param z The alphabet the hash is consisting of
     */
    public Reduction(int passwordLength, char[] z) {
        this.passwordLength = passwordLength;
        this.z = z;
        tools = new Tools(z);
    }

    /**
     * Execute a reduction step
     *
     * @param inputHash The hash to be reduced
     * @param step The current step to be added to the hash
     * @return The reduced possible password
     */
    public String executeReduction(String inputHash, int step) {
        // Convert the hash to an integer
        int hash = tools.hashToInteger(inputHash);
        int[] rI = new int[passwordLength];
        // Add the step to the hash
        hash = hash + step;
        String wrongWayString = "";
        String rightWayString = "";

        // Execute the reduction and remember the alphabet indexes
        for (int i = 0; i < passwordLength; i++) {
            rI[i] = hash % z.length;
            hash = hash / z.length;
        }

        // Convert the integer indexes to actual characters
        wrongWayString = tools.intToString(rI);
        // Reverse the possible password before returning it
        rightWayString = tools.wrongToRightWayString(wrongWayString);

        return rightWayString;
    }


}