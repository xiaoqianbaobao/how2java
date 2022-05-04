package src.File;

import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        File f = new File("C:\\windows");
        File[] fs = f.listFiles();
        if (null==fs)
            return;
        long minSize = Integer.MAX_VALUE;
        long maxSize = 0;
        File minFile = null;
        File maxFile = null;
        for (File file : fs){
            if (file.isDirectory())
                continue;
            if (file.length()>maxSize){
                maxSize = file.length();
                maxFile = file;
            }

            if (file.length()!=0 && file.length()<minSize){
                minSize = file.length();
                minFile = file;
            }
        }
        System.out.printf("最大的文件是%s, 其大小是%, d字节%n", maxFile.getAbsoluteFile(), maxFile.length());
        System.out.printf("最小文件是%s, 其大小是%, d字节%n", minFile.getAbsoluteFile(), minFile.length());
    }
}
