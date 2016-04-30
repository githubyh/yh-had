package com.yh.hadoop;

import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Test {  
    public static void main(String[] args) throws Exception {  
        String uri = "hdfs://appserver:9000/";  
        Configuration config = new Configuration();  
        FileSystem fs = FileSystem.get(URI.create(uri), config);  
   
        // �г�hdfs��/user/fkong/Ŀ¼�µ������ļ���Ŀ¼  
        FileStatus[] statuses = fs.listStatus(new Path("/user"));  
        for (FileStatus status : statuses) {  
            System.out.println(status);  
        }  
   
        // ��hdfs��/user/fkongĿ¼�´���һ���ļ�����д��һ���ı�  
        FSDataOutputStream os = fs.create(new Path("/user/test1.log"));  
        os.write("Hello World! ����".getBytes());  
        os.flush();  
        os.close();  
   
        // ��ʾ��hdfs��/user/fkong��ָ���ļ�������  
        InputStream is = fs.open(new Path("/user/test.log"));  
        IOUtils.copyBytes(is, System.out, 1024, true);  
    }  
}  