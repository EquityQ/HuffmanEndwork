package Class.Huff;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodeGenerator {

    public static Map<Byte, String> generateHuffmanCodes(HuffmanNode root) {
        Map<Byte, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void generateCodes(HuffmanNode node, String code, Map<Byte, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.Byte, code);
            return;
        }

        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }
}