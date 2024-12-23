# Huffman Coding Project

## Project Introduction
This project implements the Huffman coding algorithm for file compression and decompression. It includes functionalities to read a file, generate Huffman codes, compress the file content, and decompress it back to the original form.

## Features
- Read file content into byte array.
- Generate Huffman codes based on the frequency of each byte.
- Compress the file content using Huffman codes.
- Decompress the compressed content back to the original form.
- Calculate the compression rate.

## Usage
To use this project, follow these steps:
1. Place the file you want to compress in the `src/Static/` directory.
2. Modify the `filePath` variable in the `test.java` file to match your file name.
3. Run the `main` method in the `test.java` file.

## Code Structure
- **HuffmanNode.java**: Defines the structure of a Huffman tree node.
- **HuffmanTreeBuilder.java**: Contains the logic to build the Huffman tree.
- **HuffmanCodeGenerator.java**: Generates Huffman codes from the built Huffman tree.
- **FileUtils.java**: Provides utilities to read and write files.
- **decoding.java**: Implements the logic to decode the compressed content.
- **test.java**: Contains the main method to orchestrate the compression and decompression process.

## Example
The following code snippet demonstrates how to compress and decompress a file:
```
public static void main(String[] args) { String filePath = "test.docx"; byte[] bytes = FileUtils.readFileBytes("src/Static/" + filePath); Map<Byte, Integer> map = FileUtils.CountTimeMap(bytes); HuffmanNode root = HuffmanTreeBuilder.buildHuffmanTree(map); Map<Byte, String> huffmanCodes = HuffmanCodeGenerator.generateHuffmanCodes(root); String loadStr = ""; for (byte k : bytes) { loadStr = loadStr + huffmanCodes.get(k); } FileUtils.writeFile("src/Static/password.txt", loadStr);
String[] password = loadStr.split("(?<=\\G.{8})");
String result = "";
for (String s : password) {
result = result + (char) Integer.parseInt(s, 2);
}
FileUtils.writeFile("src/Static/result.txt", result);

double percent = (1 - (double) result.getBytes().length / bytes.length) * 100;
System.out.println("压缩率：" + String.format("%.2f%%", percent));

Map<String, Byte> huffmanCodes_reverse = new HashMap<>();
for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
huffmanCodes_reverse.put(entry.getValue(), entry.getKey());
}
String loadstr_reverse = "";
for (char k : result.toCharArray()) {
String binary = Integer.toBinaryString(k);
if (binary.length() < 8) {
int length = binary.length();
for (int i = 0; i < 8 - length; i++) {
binary = "0" + binary;
}
}
loadstr_reverse = loadstr_reverse + binary;
}
byte[] deResult = decoding.decode(loadstr_reverse, huffmanCodes_reverse);
FileUtils.writeFile("src/Static/de_" + filePath, deResult);
}
```