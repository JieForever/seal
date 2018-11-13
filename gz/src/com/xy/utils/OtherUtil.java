package com.xy.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;



public class OtherUtil {
	
	/**
	 * 测试，输出
	 * @param obj
	 */
	public static void say(Object obj){
		System.out.println(obj);
	}

	/**
	 * 获得文件名，不含后缀
	 * @param path
	 * @return
	 */
	public static String getFN(String path){
		try{
			 String fileName = path.substring(path.lastIndexOf("\\")+1);
			 String ff = fileName.split("\\.")[0];
			 return ff;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获得文件名,含后缀
	 * @param path
	 * @return
	 */
	public static String getFN2(String path){
		try{
			 String fileName = path.substring(path.lastIndexOf("\\")+1);
			// String ff = fileName.split("\\.")[0];
			 return fileName;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取文件的后缀
	 * @param path
	 * @return
	 */
	public static String getFileSuffix(String path){
		 File file = new File(path);
	     String fileName = file.getName();
	     String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	     return suffix;
	}
	
	
	/**
	 * 递归获取某文件夹下的所有文件
	 * @param file 某文件
	 * @param p 存放所有文件的路径
	 */
	public static void getFiles(File file, List<String> p) {
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles(); 
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    getFiles(files[i],p);
                } else {
                    p.add(files[i].getPath());
                }
            }
        } else {
            p.add(file.getPath());
        }
    }
	
	public static int sumArr(int[] arr){
		int count = 0;
		if(arr != null)
			for (int i : arr) {
				count+=i;
			}
		return count;
	}
	
	public static int sumArr(int[] arr, int num){
		int count = 0;
		if(arr != null)
			count = arr[0] * num;
		return count;
	}
	
	
	
//	public static void main(String[] args) {
//		List<String> l = new ArrayList<String>();
//		getFiles(new File("C:\\Users\\Lenovo\\Desktop\\测试\\qgd\\河南省签购单照片4"), l);
//		for (String string : l) {
//			System.out.println(string);
//		}
//		System.out.println(l.size());
//	}
}
