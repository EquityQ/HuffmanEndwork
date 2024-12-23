package Class.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
    public static byte[] readFileBytes(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // 获取文件长度
            long fileLength = fis.getChannel().size();

            // 如果文件过大，可以进行分段读取，这里为了简单起见，假设文件不大
            if (fileLength > Integer.MAX_VALUE) {
                throw new IOException("File is too large to read into memory.");
            }

            byte[] buffer = new byte[(int) fileLength];
            int bytesRead = fis.read(buffer);

            // 检查是否读取了所有字节
            if (bytesRead != fileLength) {
                throw new IOException("Could not completely read file.");
            }

            return buffer;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null; // 或者抛出异常，根据你的需求决定
        }
    }

    public static Map<Byte, Integer> CountTimeMap(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.containsKey(b)) {
                map.put(b, map.get(b) + 1);
            } else {
                map.put(b, 1);
            }
        }
        return map;
    }

    public static void writeFile(String path, byte[] bytes) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
//            System.out.println("File written successfully to: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String path, String content) {
        FileUtils.writeFile(path, content.getBytes());
    }
}
