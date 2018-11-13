package com.xy.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 图片工具操作
 * @author Lenovo
 *
 */
public class ImgOperateUtil {

	public int colPartNum = 1;//纵向切块个数
	public int rowPartNum = 1;//横向切块个数
	
	public ImgOperateUtil(int colPartNum,int rowPartNum){
		this.colPartNum = colPartNum;
		this.rowPartNum = rowPartNum;
	}
	
	/**
	 * 读取一张图片
	 * （读取程序目录下的）
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage readModeImg(String path) throws IOException{
		URL url = ImgOperateUtil.class.getResource(path);
		InputStream is = url.openStream();
		//return ImageIO.read(new File(path));
		return ImageIO.read(is);
	}
	
	/**
	 * 读取一张图片
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage readModeImg2(String path) throws IOException{
		return ImageIO.read(new File(path));
		
	}
	
	/**
	 * 分割图片
	 * @param f 源文件
	 * @return 分割后的图片(在内存)
	 * @throws IOException
	 */
	public BufferedImage[][] cutImg(File f) throws IOException{
		BufferedImage source = ImageIO.read(f);
		//图片宽度
		int sWidth = source.getWidth();
		//图片高度
		int sHeight = source.getHeight();
		return cutImg1(source, sWidth, sHeight, colPartNum, rowPartNum);
	}
	
	/**
	 * 分割图片
	 * @param source bufferedImage图片
	 * @return 分割后的图片(在内存)
	 * @throws IOException
	 */
	public BufferedImage[][] cutImg(BufferedImage source) throws IOException{
		//图片宽度
		int sWidth = source.getWidth();
		//图片高度
		int sHeight = source.getHeight();
		return cutImg1(source, sWidth, sHeight, colPartNum, rowPartNum);
	}
	
	private static BufferedImage[][] cutImg1(BufferedImage bi,int imgW, int imgH, int colNum, int rowNum){
		int imgPartH = imgH / rowNum;   
		int imgPartW = imgW / colNum;	
		int px = 0, py = 0;
		BufferedImage [][] biPart = new BufferedImage[rowNum][colNum];
		for(int i = 0; i < rowNum; i++){
			for(int j = 0; j < colNum; j++){
				biPart[i][j] = bi.getSubimage(px, py, imgPartW, imgPartH);
				px = px + imgPartW;
			}
			py = py + imgPartH;
			px = 0;
		}
		return biPart;
	}
	
	/**
	 * 拼接图片(长宽需要一致)
	 * @param imgs 要拼接的图片
	 * @return
	 */
	public static BufferedImage jointImg(BufferedImage[][] imgs){
		if(imgs.length <= 0){
			return null;
		}
        int newHeight = 0, newWidth = 0;
        int imgsRowNum = imgs.length, imgsColNum = imgs[0].length;
        newHeight = imgs[0][0].getHeight() * imgsRowNum;
        newWidth = imgs[0][0].getWidth();  
       
        if ( newWidth < 1) {  
            return null;  
        }  
        if (newHeight < 1) {  
            return null;  
        }
        
        BufferedImage[] imageNewCol = new BufferedImage[imgsColNum];
        BufferedImage imageNewAll  = new BufferedImage(newWidth * imgsColNum, newHeight, BufferedImage.TYPE_INT_RGB);;
        // 生成新图片  
        try {
        	int width_i = 0;  
            for (int i = 0; i < imgs[0].length; i++) {
            	int height_i = 0; 
            	imageNewCol[i] = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            	//纵向拼接
            	for(int j = 0; j < imgs.length; j++){
            		 int width = imgs[j][i].getWidth();  
                     int height = imgs[j][i].getHeight();  
                     int [] temp  = imgs[j][i].getRGB(0, 0, width, height, new int[width * height], 0, width); 
                     imageNewCol[i].setRGB(0, height_i, width, height, temp, 0, width);
                     height_i += height;         
            	}
            	//横向拼接
            	int [] temp = imageNewCol[i].getRGB(0, 0, newWidth, height_i, new int[newWidth * height_i], 0, newWidth);
            	imageNewAll.setRGB(width_i, 0, newWidth, height_i, temp, 0, newWidth);
            	width_i += newWidth;
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
		return imageNewAll;
	}
	
	
	/**
	 * 将分割后的图片输出
	 * @param imgPart 内存中的图片
	 * @param targetFolder 输出目标文件夹
	 * @throws IOException
	 */
	public static void printImgPart(Image[][] imgPart,String targetFolder) throws IOException{
		for(int m = 0 ; m < imgPart.length; m++){
			for (int n = 0; n < imgPart[m].length; n++) {
				String fileName = targetFolder + "/map_" + m + "_" + n + ".jpg"; 
				ImageIO.write((RenderedImage) imgPart[m][n], "jpeg", new File(fileName));
			}
		}
		
	}
	/**
	 * 输出图片
	 * @param img
	 * @param targetFolder 完整路径(包含文件名加文件后缀)
	 * @throws IOException
	 */
	public static void printImg(Image img, String targetFolder) throws IOException{
		ImageIO.write((RenderedImage)img, "jpeg", new File(targetFolder));
	}
	
	/**
	 * 缩放图片
	 * @param img 要缩放的图片
	 * @param sx x轴方向倍数
	 * @param sy y轴方向倍数
	 * @param isTranslucent 图片背景是否透明
	 * @return
	 */
	public static BufferedImage scaleImg(BufferedImage img, double sx, double sy, boolean isTranslucent){
		int w = img.getWidth();//得到源图宽
		int h = img.getHeight();//得到源图的高
		w = (int)(w*sx);
		h = (int)(h*sy);
		//预定义一个图像
		BufferedImage newImg = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D)newImg.getGraphics();
		if(isTranslucent){
			newImg = g2d.getDeviceConfiguration().createCompatibleImage(w, h,Transparency.TRANSLUCENT);
		}
		g2d.dispose();
		g2d = newImg.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//返回图像的缩放版本，默认的图像缩放算法
		Image image = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		//用图像的缩放版本绘制缩放后的图
		g2d.drawImage(image,0,0,null);
		g2d.dispose();
		return newImg;
	}
	
	/**
	 * 返回图片的宽和高
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static int[] getImgWH(File file) throws IOException{
		BufferedImage bi = ImageIO.read(file);
		return new int[]{bi.getWidth(),bi.getHeight()};
	}
	
	/**
	 * 返回图片最小的边长
	 * @param img
	 * @return
	 */
	public static int getMinBorder(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		return w > h ? h : w;
	}
	
	/**
	 * 放大缩小倍数
	 * @param img
	 * @param up 上限
	 * @param down 下限
	 * @return
	 */
	public static double getImgScale(BufferedImage img, int up, int down){
		double scale = 1.0;
		int minb = getMinBorder(img);
		if(minb < down || minb > up){
			double temp =  minb/1.0/down;
			scale = 1.0/temp;
		}
		return scale;
	}
	
	/*
	public static void main(String[] args) {
		try {
			//e:\\temp_c\\河南省签购单照片31\\福建省莆田神光钟表维修店.jpg
			BufferedImage img = readModeImg2("e:\\temp_c\\河南省签购单照片31\\福建省莆田神光钟表维修店 .jpg");
			//BufferedImage ii = ImgOperateUtil.scaleImg(img, 1, 1,false);
			ImageIO.write(img, "jpeg", new File("E:\\o.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
}
