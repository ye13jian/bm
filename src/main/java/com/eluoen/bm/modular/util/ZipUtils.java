package com.eluoen.bm.modular.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 *
 * @author eluoen
 * @date 2018-11-26 16:55
 */
public class ZipUtils {
	
	
	public static void downLoadZipFile(File[] files,String zippath, HttpServletResponse response) throws IOException{
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zippath);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(File file:files){
                zipFile(file.getName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
	
	
	public static void zipFile(String fileName, ZipOutputStream out) throws IOException{
        File file = new File(fileName);
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len = 0 ;
            //读入需要下载的文件的内容，打包到zip文件    
            while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }

	
	
	
	
	
	
	/**
	 * 将_check和_data文件打成一个压缩包
	 * @param files
	 * @param zippath
	 */
    public static void ZipMultiQrcodeFile(File[] files,String zippath) {
		try {
	        File zipFile = new File(zippath);
	        InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            for(int i = 0; i < files.length; ++i){
                input = new FileInputStream(files[i]);
                zipOut.putNextEntry(new ZipEntry(files[i].getName()));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }
	        zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    

    
    /**
     * 通用压缩多个文件（文件夹）
     * @param filepath
     * @param zippath
     */
    public static void ZipMultiFile(String filepath ,String zippath) {
		try {
	        File file = new File(filepath);// 要被压缩的文件夹
	        File zipFile = new File(zippath);
	        InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	        if(file.isDirectory()){
	            File[] files = file.listFiles();
	            for(int i = 0; i < files.length; ++i){
	                input = new FileInputStream(files[i]);
	                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
	                int temp = 0;
	                while((temp = input.read()) != -1){
	                    zipOut.write(temp);
	                }
	                input.close();
	            }
	        }
	        zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}