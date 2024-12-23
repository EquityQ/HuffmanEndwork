package Class.file;

import java.util.Map;

public class decoding {
    public static byte[] decode(String encodedString, Map<String, Byte> huffmanCodesReverse) {
        StringBuilder currentCode = new StringBuilder();
        byte[] decodedBytes = new byte[encodedString.length()]; // 预估最大长度
        int index = 0;

        for (char bit : encodedString.toCharArray()) {
            currentCode.append(bit);
            if (huffmanCodesReverse.containsKey(currentCode.toString())) {
                decodedBytes[index++] = huffmanCodesReverse.get(currentCode.toString());
                currentCode.setLength(0); // 清空 currentCode
            }
        }

        // 调整数组大小以匹配实际解码长度
        byte[] result = new byte[index];
        System.arraycopy(decodedBytes, 0, result, 0, index);

        return result;
    }
}
