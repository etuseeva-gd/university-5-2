package ThirdTask;

import Utils.Utils;

import java.io.*;
import java.util.List;

public class FourthSubTask {

    /**
     * Перебор ключей по ориентированному лесу возможных перестановок.
     *
     * Вход: файл ориентированного леса возможных перестановок; файл шифрограммы.
     * Выход: файл походящего ключа.
     */
    public void init() throws IOException {
        String cryptoText = Utils.read(Files.CRYPT_TEXT);
        List<int[]> keys = Files.readKeys(Files.PROBABLE_KEYS);

        StringBuilder decrypter = new StringBuilder();
        keys.forEach(key-> {
            int keyLength = key.length;
            String subCrypto = cryptoText.substring(0, keyLength * 3);
            for (int j = 0; j < subCrypto.length(); j += keyLength) {
                String block = subCrypto.substring(j, j + keyLength);
                for (int keyElement : key) {
                    decrypter.append(block.substring(keyElement, keyElement + 1));
                }
            }
        });

        Utils.print(Files.PROBABLE_DECRYPTION, String.valueOf(decrypter));
    }
}
