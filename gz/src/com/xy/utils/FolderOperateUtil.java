package com.xy.utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 文件夹工具
 * @author Lenovo
 *
 */
public class FolderOperateUtil {

    static String se = File.separator;
    
    /**
     * 创建文件夹
     * @param path 
     * @param folderName
     * @return
     */
    public static String getFolder(String path,String folderName){
    	String tp = "";
        if(path == "" || path == null){
            tp = folderName;
        }else {
            tp = path + se + folderName;
        }
        File file = new File(tp);
        //如果文件夹不存在则创建
        if(!file.exists()  && !file.isDirectory()){
           // System.out.println("//不存在");
            file.mkdirs();
        }else{
            //System.out.println("//-目录存在");
        }
        return tp;
     }
	    
	    
	public static String getFolder(String path,String[] folderName){
	    for(int i = 0;i<folderName.length;i++){
	        File file =new File(path+se+folderName[i]);
	        //如果文件夹不存在则创建
	        if(!file.exists()  && !file.isDirectory()){
	           // System.out.println("//不存在");
	            file.mkdir();
	        }else{
	           // System.out.println("//+目录存在");
	        }
	        path=path+se+folderName[i];
	    }
	    return path;
	}
	    
    /**
     * 删除某个文件夹下的所有文件夹和文件
     * 
     * @param delpath
     * @throws FileNotFoundException
     * @throws IOException
     * @return boolean
     */
    public static boolean deletefile(String delpath){
        try {

            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                       // System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
               // System.out.println(file.getAbsolutePath() + "删除成功");
                file.delete();
            }
        } catch (Exception e) {
           // System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }
}
