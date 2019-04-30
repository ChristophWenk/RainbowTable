package krysi;

public class Program {

    public static void main(String[] args) {


        char[] z = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        int passwordLength = 7;
        int chainLength = 200;
        int r = 2000;
        Tools tools = new Tools(z);

        String hashValue = tools.readHashValue("src/main/resources/hashValue.txt");

        Reduction reduction = new Reduction(passwordLength,z);

    }
}
