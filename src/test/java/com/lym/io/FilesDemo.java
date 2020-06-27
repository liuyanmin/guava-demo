package com.lym.io;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.*;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class FilesDemo {

    /**
     * 1、BufferedReader
     */
    @Test
    public void testNewReader() throws FileNotFoundException {
        BufferedReader bufferedReader = Files.newReader(new File("test.txt"), Charsets.UTF_8);
    }

    /**
     * 2、BufferedWriter
     */
    @Test
    public void testNewWriter() throws FileNotFoundException {
        BufferedWriter bufferedWriter = Files.newWriter(new File("test.txt"), Charsets.UTF_8);
    }

    /**
     * 3、按字节读取内容
     */
    @Test
    public void testAsByteSource() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("test.txt"));
        // 获取文件流
        InputStream inputStream = byteSource.openBufferedStream();
        // 获取文件大小
        long size = byteSource.size();
        System.out.println(size);
        // 获得字节数组
        byte[] bs = byteSource.read();
    }

    /**
     * 4、按字节追加内容
     */
    @Test
    public void testAsByteSink() throws IOException {
        ByteSink byteSink = Files.asByteSink(new File("test.txt"), FileWriteMode.APPEND);
        byte[] bs = {1, 2, 3};
        byteSink.write(bs);
    }

    /**
     * 5、按行读取内容
     */
    @Test
    public void testAsCharSource() throws IOException {
        CharSource charSource = Files.asCharSource(new File("test.txt"), Charsets.UTF_8);
        String firstLine = charSource.readFirstLine();
        List<String> readLines = charSource.readLines();
        System.out.println(firstLine);
        System.out.println(readLines);
    }

    /**
     * 6、按行追加内容
     */
    @Test
    public void testAsCharSink() throws IOException {
        CharSink charSink = Files.asCharSink(new File("test.txt"), Charsets.UTF_8, FileWriteMode.APPEND);
        charSink.write("aaa");
        charSink.writeLines(Lists.newArrayList("a", "b"));
        charSink.writeLines(Lists.newArrayList("a", "b"), "|");
    }

    /**
     * 7、获得字节数组
     */
    @Test
    public void testToByteArray() throws IOException {
        byte[] bs = Files.toByteArray(new File("test.txt"));
    }

    /**
     * 8、为文件写入一个字节数组的内容
     */
    @Test
    public void testWrite() throws IOException {
        byte[] bs = {48, 49, 50};
        Files.write(bs, new File("test.txt"));
    }

    /**
     * 9、把一个文件内容拷贝到一个输出流中
     */
    @Test
    public void testCopy() throws IOException {
        OutputStream outputStream = new FileOutputStream(new File("test2.txt"));
        Files.copy(new File("test.txt"), outputStream);
    }

    /**
     * 10、把一个文件拷贝到另外一个地方
     */
    @Test
    public void testCopy2() throws IOException {
        Files.copy(new File("test.txt"), new File("test3.txt"));
    }

    /**
     * 11、判断两个文件是否一致
     */
    @Test
    public void testEqual() throws IOException {
        File file1 = new File("test.txt");
        File file2 = new File("test2.txt");
        boolean equal = Files.equal(file1, file2);
        System.out.println(equal);
    }

    /**
     * 12、创建一个临时目录
     */
    @Test
    public void testCreateTempDir() {
        File file = Files.createTempDir();
        System.out.println(file.getAbsolutePath());
    }

    /**
     * 12、创建一个文件（如果不存在）或者修改一下该文件的末次修改时间（如果已经存在），就相当于linux的touch命令
     */
    @Test
    public void testTouch() throws IOException {
        Files.touch(new File("test.txt"));
    }

    /**
     * 13、为文件创建父路径
     */
    @Test
    public void testCreateParentDirs() throws IOException {
        Files.createParentDirs(new File("D:/a/b/c/test.txt"));
    }

    /**
     * 14、移动文件
     */
    @Test
    public void testMove() throws IOException {
        Files.move(new File("test2.txt"), new File("D:/a/b/c/test.txt"));
    }

    /**
     * 15、把文件转成一个List<String>
     */
    @Test
    public void test() throws IOException {
        List<String> list = Files.readLines(new File("test.txt"), Charsets.UTF_8);
        System.out.println(list);
    }
}
