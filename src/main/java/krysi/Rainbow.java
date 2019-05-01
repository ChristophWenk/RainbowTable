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
        createRainbowTable();
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
                byte [] hashedValue = tools.hashWithMd5(lastValue);
                lastValue = reduction.executeReduction(hashedValue,j);
            }
            rainbowTable.put(firstValue,lastValue);
        }
    }

    /**
     * Find a given hash in the Rainbow Table and return the matching password
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
                byte[] hash = tools.hashWithMd5(nextHash);
                String hashString = tools.getMD5String(nextHash);
                if (hashString.equals(hashValue)) {
                    return nextHash;
                }
                nextHash = reduction.executeReduction(hash,i);
            }
        }
        return null;
    }
}
