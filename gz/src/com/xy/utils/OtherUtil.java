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
	 * ���ԣ����
	 * @param obj
	 */
	public static void say(Object obj){
		System.out.println(obj);
	}

	/**
	 * ����ļ�����������׺
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
	 * ����ļ���,����׺
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
	 * ��ȡ�ļ��ĺ�׺
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
	 * �ݹ��ȡĳ�ļ����µ������ļ�
	 * @param file ĳ�ļ�
	 * @param p ��������ļ���·��
	 */
	public static void getFiles(File file, List<String> p) {
        // ������·�����ļ���
        if (file.isDirectory()) {
            // ��ȡ·���µ������ļ�
            File[] files = file.listFiles(); 
            for (int i = 0; i < files.length; i++) {
                // ��������ļ��� �ݹ��ȡ������ļ� �ļ���
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
//		getFiles(new File("C:\\Users\\Lenovo\\Desktop\\����\\qgd\\����ʡǩ������Ƭ4"), l);
//		for (String string : l) {
//			System.out.println(string);
//		}
//		System.out.println(l.size());
//	}
}
