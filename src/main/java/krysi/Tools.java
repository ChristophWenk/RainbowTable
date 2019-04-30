package krysi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tools {
    char[] z;

    private String fileContent = "";

    public Tools(char[] z) {
        this.z = z;
    }

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

    public String intToString(int[] rI){
        String rIString="";
        for (int i=0; i<rI.length; i++){
            String actualSymbol = Integer.toString(rI[i]);
            for(int j=0; j<z.length;j++){
                if(actualSymbol.equals(z[j])){
                    rIString+=z[j];

                }
            }

        }
        return rIString;
    }

    public String wrongToRightWayString(String wrongWayString){
        String rigthWayString = "";
        rigthWayString = new StringBuilder(wrongWayString).reverse().toString();

        return  rigthWayString;
    }
}


