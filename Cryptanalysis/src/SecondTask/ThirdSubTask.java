package SecondTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utils.Utils;

public class ThirdSubTask {
    private static final String
            inputAlph = "input_2.3_alph.txt",
            inputKey = "input_2.3_key.txt",
            inputText = "input_2.3_text.txt",
            outputCrypt = "output_2.3_crypt.txt",
            outputShiftText = "output_2.3_shift_text.txt",
            outputShiftCrypt = "output_2.3_shift_crypt.txt",
            outputDecrypt = "output_2.3_decrypt.txt";

    public void init() throws IOException {
        String alph = Utils.read(inputAlph).toLowerCase();
        String key = Utils.read(inputKey).toLowerCase();

        List<String> vignerTable = this.getVignerTable(alph);

        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Зашифровать открытый текст");
        System.out.println("2. Расшифровать криптограмму");

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        switch (action) {
            case 1: {
                String text = this.cleanText(Utils.read(inputText), alph);
//                Utils.Utils.print(outputShiftText, this.getShiftText(text));

                String cryptText = this.crypt(text, alph, key, vignerTable);

                Utils.print(outputCrypt, cryptText);
//                Utils.Utils.print(outputShiftCrypt, this.getShiftText(cryptText));
                break;
            }
            case 2: {
                String cryptText = this.cleanText(Utils.read(outputCrypt), alph);
                Utils.print(outputDecrypt, this.decrypt(cryptText, alph, key, vignerTable));
                break;
            }
            default: {
                System.out.println("Неверная операция!");
            }
        }
    }

    private String cleanText(String text, String alph) {
        StringBuilder updatedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (alph.indexOf(c) != -1) {
                updatedText.append(c);
            }
        }

        return String.valueOf(updatedText).toLowerCase();
    }

    private List<String> getVignerTable(String alph) {
        List<String> table = new ArrayList<>();
        table.add(alph);
        for (int i = 0; i < alph.length() - 1; i++) {
            table.add(alph.substring(i + 1) + alph.substring(0, i + 1));
        }
        return table;
    }

    private String crypt(String text, String alph, String key, List<String> table) {
        StringBuilder cryptText = new StringBuilder();
        int indexKey = 0;
        for (int i = 0; i < text.length(); i++) {
            int posI = alph.indexOf(key.charAt(indexKey));
            int posJ = alph.indexOf(text.charAt(i));
            cryptText.append(table.get(posI).charAt(posJ));
            indexKey = (indexKey + 1) % key.length();
        }
        return String.valueOf(cryptText);
    }

    private String decrypt(String cryptText, String alph, String key, List<String> table) {
        StringBuilder decryptText = new StringBuilder();
        int indexKey = 0;
        for (int i = 0; i < cryptText.length(); i++) {
            int posI = alph.indexOf(key.charAt(indexKey));
            String tableLine = table.get(posI);
            int posJ = tableLine.indexOf(cryptText.charAt(i));
            decryptText.append(table.get(0).charAt(posJ));
            indexKey = (indexKey + 1) % key.length();
        }
        return String.valueOf(decryptText);
    }

    private String getShiftText(String text) {
        StringBuilder shiftText = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            shiftText.append(text.substring(i + 1)).append(text.substring(0, i + 1));
        }
        return String.valueOf(shiftText);
    }
}
