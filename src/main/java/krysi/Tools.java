package krysi;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class offers methods commonly used by ciphers
 */
public class Tools {
    private char[] z;

    private String fileContent = "";

    /**
     * Construct the Tools with parameters
     *
     * @param z The alphabet the hash is consisting of
     */
    public Tools(char[] z) {
        this.z = z;
    }

    /**
     * Construct the Tools
     */
    public Tools() {
    }

    /**
     * Read an input file
     *
     * @param file the file path
     * @return the file content
     */
    public String readHashValue(String file) {
        try {
            Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(s -> fileContent += s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileContent;
        }
    }

    /**
     * Convert a string hash to an integer hash
     *
     * @param stepHash The string hash to be converted
     * @return The converted integer hash
     */
    public int hashToInteger(String stepHash) {
        int intHash = 0;
        String indexZ = "";
        for (int i = 0; i < stepHash.length(); i++) {
            char actualSymbol = stepHash.charAt(i);
            for (int j = 0; j < z.length; j++) {
                if (actualSymbol == z[j]) {
                    indexZ += j;
                }
            }
        }
        intHash = Integer.parseInt(indexZ);
        return intHash;
    }

    /**
     * Convert an integer hash to a string hash
     *
     * @param rI The integer hash to be converted
     * @return The converted string hash
     */
    public String intToString(int[] rI) {
        String rIString = "";
        for (int i = 0; i < rI.length; i++) {
            int lookupIndex = rI[i];
            rIString += z[lookupIndex];
        }
        return rIString;
    }

    /**
     * Reverse a string
     *
     * @param wrongWayString The string to be reversed
     * @return The reversed string
     */
    public String wrongToRightWayString(String wrongWayString) {
        String rigthWayString = "";
        rigthWayString = new StringBuilder(wrongWayString).reverse().toString();

        return rigthWayString;
    }

    /**
     * Hash a given string with an MD5 algorithm
     *
     * @param plainText The string to be hashed
     * @return The hashed string
     */
    public String hashWithMd5(String plainText) {
        // Use Apache DigestUtils to simplify MD5 hashing and avoid dealing with byte conversion
        String hash = DigestUtils.md5Hex(plainText).toLowerCase();
        return hash;
    }
}


