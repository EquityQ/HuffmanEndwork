import Class.Huff.HuffmanCodeGenerator;
import Class.Huff.HuffmanNode;
import Class.Huff.HuffmanTreeBuilder;
import Class.file.FileUtils;
import Class.file.decoding;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String filePath= "test.docx";
        byte[] bytes = FileUtils.readFileBytes("src/Static/"+filePath);
        Map<Byte, Integer> map = FileUtils.CountTimeMap(bytes);
        HuffmanNode root = HuffmanTreeBuilder.buildHuffmanTree(map);
        Map<Byte, String> huffmanCodes = HuffmanCodeGenerator.generateHuffmanCodes(root);
        String loadStr="";
        for (byte k : bytes){
            loadStr = loadStr + huffmanCodes.get(k);
        }
//        新建并写入文件
        FileUtils.writeFile("src/Static/password.txt",loadStr);

        String[] password = loadStr.split("(?<=\\G.{8})");
        String result = "";
        for (String s : password){
            result= result + (char)Integer.parseInt(s,2);
        }
        FileUtils.writeFile("src/Static/result.txt", result);

//        计算压缩率
        double percent = (1-(double)result.getBytes().length / bytes.length)*100;
        System.out.println("压缩率：" +  String.format("%.2f%%", percent));


//        解压缩
//        反转huffmanCodes
        Map<String, Byte> huffmanCodes_reverse = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            huffmanCodes_reverse.put(entry.getValue(), entry.getKey());
        }
        String loadstr_reverse = "";
        for (char k : result.toCharArray()){
//            字符串转int 8 位
            String binary = Integer.toBinaryString(k);
            if (binary.length() < 8){
                int length = binary.length();
                for (int i = 0; i < 8 - length; i++){
                    binary = "0" + binary;
                }
            }
            loadstr_reverse = loadstr_reverse + binary;
        }
        byte[] deResult = decoding.decode(loadstr_reverse, huffmanCodes_reverse);
        FileUtils.writeFile("src/Static/de_"+filePath, deResult);
    }
}
