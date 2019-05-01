package krysi;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

public class Rainbow {

    private char[] z;
    private int passwordLength;
    private int chainLength;
    private int r;
    private BidiMap rainbowTable = new DualHashBidiMap();
    private Reduction reduction;
    private Tools tools;

    public Rainbow(int passwordLength, char[] z, int chainlength, int r) {
        this.passwordLength = passwordLength;
        this.z = z;
        this.chainLength = chainlength;
        this.r = r;
        reduction  = new Reduction(passwordLength, z);
        tools = new Tools(z);
    }

    public void createRainbowtable() {
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
            System.out.println(lastValue);
        }
    }
}
