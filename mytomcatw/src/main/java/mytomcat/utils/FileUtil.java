package mytomcat.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 11:00
 * @Version V1.0
 */
@Slf4j
public class FileUtil {

    public static boolean writeFile(InputStream inputStream, OutputStream outputStream) {
        boolean success = false;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(HttpUtil.getHttpResponseContext200("").getBytes("utf-8"));
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            int fileSize = inputStream.available();
            long written = 0;
            int byteSize = 1024;
            byte[] bytes = new byte[byteSize];
            while (written < fileSize) {
                if (written + byteSize > fileSize) {
                    byteSize = (int) (fileSize - written);
                    bytes = new byte[byteSize];
                }
                bufferedInputStream.read(bytes);
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                written += byteSize;
            }
            success = true;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return success;
    }

    public static boolean writeFile(File file, OutputStream outputStream) throws IOException {
        return writeFile(new FileInputStream(file), outputStream);
    }

    public static String getResourcePath(String path) {
        String resource = FileUtil.class.getResource("/").getPath();
        return resource + "\\" + path;
    }

}



