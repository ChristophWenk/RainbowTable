package krysi;

import java.math.BigInteger;

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
        // Convert the hash to a BigInteger
        BigInteger hash = tools.hashToBigInteger(inputHash);
        // Add the step to the hash. Convert step to String to be able to use BigInteger
        hash = hash.add(new BigInteger(String.valueOf(step)));

        // Initialize variables
        BigInteger zLength = new BigInteger(String.valueOf(z.length));
        int[] rI = new int[passwordLength];
        String wrongWayString = "";
        String rightWayString = "";

        // Execute the reduction and remember the alphabet indexes
        for (int i = 0; i < passwordLength; i++) {
            BigInteger test = hash.mod(zLength);
            rI[i] = hash.mod(zLength).intValue();
            hash = hash.divide(zLength);
        }

        // Convert the integer indexes to actual characters
        wrongWayString = tools.intToString(rI);
        // Reverse the possible password before returning it
        rightWayString = tools.wrongToRightWayString(wrongWayString);

        return rightWayString;
    }
}