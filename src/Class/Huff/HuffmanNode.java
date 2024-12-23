package Class.Huff;

public class HuffmanNode {
    byte Byte; // 字符
    int frequency; // 频率
    HuffmanNode left; // 左子节点
    HuffmanNode right; // 右子节点

    public HuffmanNode(byte Byte, int frequency) {
        this.Byte = Byte;
        this.frequency = frequency;
    }
}