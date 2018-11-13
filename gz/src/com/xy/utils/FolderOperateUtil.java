package com.xy.utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * �ļ��й���
 * @author Lenovo
 *
 */
public class FolderOperateUtil {

    static String se = File.separator;
    
    /**
     * �����ļ���
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
        //����ļ��в������򴴽�
        if(!file.exists()  && !file.isDirectory()){
           // System.out.println("//������");
            file.mkdirs();
        }else{
            //System.out.println("//-Ŀ¼����");
        }
        return tp;
     }
	    
	    
	public static String getFolder(String path,String[] folderName){
	    for(int i = 0;i<folderName.length;i++){
	        File file =new File(path+se+folderName[i]);
	        //����ļ��в������򴴽�
	        if(!file.exists()  && !file.isDirectory()){
	           // System.out.println("//������");
	            file.mkdir();
	        }else{
	           // System.out.println("//+Ŀ¼����");
	        }
	        path=path+se+folderName[i];
	    }
	    return path;
	}
	    
    /**
     * ɾ��ĳ���ļ����µ������ļ��к��ļ�
     * 
     * @param delpath
     * @throws FileNotFoundException
     * @throws IOException
     * @return boolean
     */
    public static boolean deletefile(String delpath){
        try {

            File file = new File(delpath);
            // ���ҽ����˳���·������ʾ���ļ������� ��һ��Ŀ¼ʱ������ true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                       // System.out.println(delfile.getAbsolutePath() + "ɾ���ļ��ɹ�");
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
               // System.out.println(file.getAbsolutePath() + "ɾ���ɹ�");
                file.delete();
            }
        } catch (Exception e) {
           // System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }
}
