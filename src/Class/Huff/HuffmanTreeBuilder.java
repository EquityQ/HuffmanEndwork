package Class.Huff;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeBuilder {

    public static HuffmanNode buildHuffmanTree(Map<Byte, Integer> frequencyMap) {
        // 创建优先队列，根据频率排序
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // 将字符和频率放入优先队列
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // 构建哈夫曼树
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            // 创建一个新的父节点，频率为左右子节点之和，字符为空字符
            HuffmanNode parent = new HuffmanNode((byte) '\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            priorityQueue.offer(parent);
        }

        // 队列中剩下的唯一节点就是根节点
        return priorityQueue.poll();
    }
}