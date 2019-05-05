package krysi;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

/**
 * This class provides all methods to build a rainbow table
 */
public class Rainbow {

    // Class variables
    private char[] z;
    private int passwordLength;
    private int chainLength;
    private int r;
    private BidiMap rainbowTable = new DualHashBidiMap();
    private Reduction reduction;
    private Tools tools;

    /**
     * Construct a Rainbow Table
     *
     * @param passwordLength The length of a possible password
     * @param z The alphabet the hash is consisting of
     * @param chainlength  The length of one single chain
     * @param r The amount of passwords generated
     */
    public Rainbow(int passwordLength, char[] z, int chainlength, int r) {
        this.passwordLength = passwordLength;
        this.z = z;
        this.chainLength = chainlength;
        this.r = r;
        reduction  = new Reduction(passwordLength, z);
        tools = new Tools(z);
    }

    /**
     * Create a Rainbow Table
     */
    public void createRainbowTable() {
        // Create the first 2000 password pairs
        for (int i = 0; i < r; i++) {
            // Create first value in chains
            String firstValue = Integer.toString(i, 36);
            String missingZeroes = "";
            if (firstValue.length() < 7) {
                int difference = 7 - firstValue.length();
                for (int j = 0; j < difference; j++) {
                    missingZeroes += "0";
                }
            }
            firstValue = missingZeroes + firstValue;

            // Create last value in chains
            String lastValue = firstValue;
            for (int j = 0; j < chainLength; j++) {
                String hashedValue = tools.getMD5String(lastValue);
                lastValue = reduction.executeReduction(hashedValue,j);
            }
            rainbowTable.put(firstValue,lastValue);
        }
    }

    /**
     * Find a given hash in the Rainbow Table and return the matching password.
     * This is very inefficient as it fully creates every chain until the password has been found.
     * Helpful for testing purposes.
     *
     * @param hashValue The hash to be checked
     * @return The password matching the hash
     */
    public String matchInput(String hashValue) {
        MapIterator it = rainbowTable.mapIterator();
        // Iterate through all chains
        while(it.hasNext()) {
            String nextHash = (String) it.next();
            // Check each chain if the value can be found
            for (int i = 0; i < chainLength; i++) {
                String hashString = tools.getMD5String(nextHash);
                if (hashString.equals(hashValue)) {
                    return nextHash;
                }
                nextHash = reduction.executeReduction(hashString,i);
            }
        }
        return null;
    }

    /**
     * Find a password in a specific chain. This can be used when we know that an input hash
     * matches a hash in a specific chain.
     *
     * @param firstValue The first possible password value of the chain to be searched in
     * @param hashValue The hash value to be compared
     * @return The found password
     */
    public String findPassword(String firstValue, String hashValue) {
        String possiblePassword = firstValue;
        for (int i = 0; i < chainLength; i++) {
            String hash = tools.getMD5String(possiblePassword);
            if (hash.equals(hashValue)) {
                return possiblePassword;
            }
            possiblePassword = reduction.executeReduction(hash, i);
        }
        return null;
    }

    /**
     * Search the first value of a chain by executing a chain process to find the last value and look
     * up the first value in the rainbow table.
     *
     * @param hashValue The hash value to be searched in the chain
     * @return The found first possible password value of a chain
     */
    public String findFirstValue(String hashValue) {
        String nextHash = "";
        String possiblePassword = "";
        // Start to search with max chain length to find the last value as fast as possible
        for (int i = chainLength; i >= 0; i--) {
            nextHash = hashValue;

            for (int j = i; j < chainLength; j++) {
                possiblePassword = reduction.executeReduction(nextHash, j);
                // If the found value matches a last value in the rainbow table,
                // lookup the first value and return it
                if (rainbowTable.getKey(possiblePassword) != null) {
                    return rainbowTable.getKey(possiblePassword).toString();
                }
                nextHash = tools.getMD5String(possiblePassword);
            }
        }
        return null;
    }
}
