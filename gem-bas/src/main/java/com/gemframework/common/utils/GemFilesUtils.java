package com.gemframework.common.utils;
 
import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 学习使用java.util.zip压缩文件或者文件夹
 * @author lhm
 *
 */
 
public class GemFilesUtils {
 
    /**
     * @param args 主方法
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去
        compress("H:\\zip/scala","H:\\zip/oo.zip");
    }
 
    /**s
     * 压缩文件
     * @param srcFilePath 压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath) {
        //
        File src = new File(srcFilePath);
 
        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);
 
        try {
 
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();
 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
 
        }
    }
    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     * @param src
     * @param zos
     * @param baseDir
     */
     private static void compressbyType(File src, ZipOutputStream zos,String baseDir) {
 
            if (!src.exists())
                return;
            System.out.println("压缩路径" + baseDir + src.getName());
            //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
            if (src.isFile()) {
                //src是文件，调用此方法
                compressFile(src, zos, baseDir);
                 
            } else if (src.isDirectory()) {
                //src是文件夹，调用此方法
                compressDir(src, zos, baseDir);
 
            }
 
        }
      
        /**
         * 压缩文件
        */
        private static void compressFile(File file, ZipOutputStream zos,String baseDir) {
            if (!file.exists())
                return;
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(baseDir + file.getName());
                zos.putNextEntry(entry);
                int count;
                byte[] buf = new byte[1024];
                while ((count = bis.read(buf)) != -1) {
                    zos.write(buf, 0, count);
                }
                bis.close();
 
            } catch (Exception e) {
              // TODO: handle exception
 
            }
        }
         
        /**
         * 压缩文件夹
         */
        private static void compressDir(File dir, ZipOutputStream zos,String baseDir) {
            if (!dir.exists())
                return;
            File[] files = dir.listFiles();
            if(files.length == 0){
                try {
                    zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (File file : files) {
                compressbyType(file, zos, baseDir + dir.getName() + File.separator);
            }
    }

    /**
     * 文件下载方法
     * @param response
     * @param filePath
     * @param encode
     */
    public static void download(HttpServletResponse response, String filePath, String encode) {
        response.setContentType("text/html;charset=" + encode);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String downLoadPath = filePath;
        try {
            File file = new File(downLoadPath);
            if(!file.exists()){
                // 文件不存在
                throw new GemException(ResultCode.FILE_NOT_EXIST);
            }
            long fileLength = file.length();
            String fileName = file.getName();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(encode), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            FileInputStream fileInputStream = new FileInputStream(downLoadPath);
            bis = new BufferedInputStream(fileInputStream);
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new GemException(ResultCode.FILE_NOT_EXIST);
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                }
        }
    }
}