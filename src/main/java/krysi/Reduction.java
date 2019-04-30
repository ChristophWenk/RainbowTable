package krysi;

public class Reduction {
    int passwordLength;
    char[]z;

    Tools tools = new Tools();

    public Reduction(int passwordLength,char[]z){
        this.passwordLength = passwordLength;
    }


    public String executeReduction(String inputHash, int step){
        int hash = tools.hashToInteger(inputHash);
        int[] rI= new int[passwordLength];
        hash = hash + step;
        String wrongWayString = "";
        String rightWayString = "";

        for(int i = 0; i< passwordLength; i++){
            rI[i] = hash%z.length;
            hash = hash/z.length;
        }

        wrongWayString = tools.intToString(rI);
        rightWayString = tools.wrongToRightWayString(wrongWayString);

        return rightWayString;
    }



}
