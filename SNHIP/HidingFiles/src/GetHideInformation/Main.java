package GetHideInformation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    //    private final static String[] IMPORTANT_MARKS = {"C3", "C5", "C6", "C7", "C8", "C9",
//            "CA", "CB", "CD", "CE", "CF", "CC"};
    private final static String[] IMPORTANT_MARKS = {"C0", "C1", "C2", "C4", "D8", "D9", "DA", "DB"};

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "IMG_0001.jpg";
        String outputFile = "output.txt";

        Path fileLocation = Paths.get(inputFile);
        byte[] data = Files.readAllBytes(fileLocation);

        String hexFile = bytesToHex(data);

        Map<String, Integer> positions = new HashMap<>();
        for (int i = 0; i < hexFile.length(); i += 2) {
            String sub = "" + hexFile.charAt(i) + hexFile.charAt(i + 1);
            //System.out.println(sub);
            if (sub.equals("FF") && i + 3 < hexFile.length()) {
                String nextSub = "" + hexFile.charAt(i + 2) + hexFile.charAt(i + 3);
                System.out.println(nextSub);

                boolean notImportant = true;
                for (String IMPORTANT_MARK : IMPORTANT_MARKS) {
                    if (IMPORTANT_MARK.equals(nextSub)) {
                        notImportant = false;
                        break;
                    }
                }
                if (notImportant) {
                    positions.put(nextSub, i + 2);
                }
            }
        }

        System.out.println(positions);
    }
}
