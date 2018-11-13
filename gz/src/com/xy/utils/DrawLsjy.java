package com.xy.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import com.xy.bean.LsjyExlInfo;

/**
 * 绘制流水交易
 * @author Lenovo
 *
 */
public class DrawLsjy {
	public static int A = 50;
	public static int B = 80;
	public static int img_h = 2339;//生成图片的高
	public static int img_w = 1684;//1654;//生成图片的宽
	public static int fontSize = 25;//字体大小
	public static Color fontColor = Color.black;//字体颜色
	public static String fontStyle = "宋体";
	public static int rowNum = 50;//每页最大数据行数
	public static int rowSpacing = 15;//上下行间距
	
	public static int pointX0 = 130;
	public static int pointY0 = 200;

	private static int[] cs = new int[5];
	/**
	 * 创建流水交易图片
	 * @param list
	 * @return
	 */
	@Deprecated
	public static BufferedImage[] createLsyjImg(List<LsjyExlInfo> list){
		int shmcW = shmcW(list);
		
		int size = list.size() - 1;
		//生成多少页图片
		int imgPageNum = (int)Math.ceil(size/(rowNum/1.0));
		BufferedImage[] allImg = new BufferedImage[imgPageNum];
		//增量
		int add = 0;
		for(int i = 0 ; i < imgPageNum; i++){
			
			allImg[i] = new BufferedImage(img_w, img_h, BufferedImage.TYPE_INT_RGB); 
			Graphics2D g2 = (Graphics2D)allImg[i].getGraphics();
			//设置笔刷
			g2.setColor(Color.WHITE);
			//填充整个屏幕为白色
			g2.fillRect(0, 0, img_w, img_h);
			//设置笔刷
			g2.setColor(fontColor);
			//抗锯齿
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font f = new Font(fontStyle, Font.PLAIN, fontSize);
			g2.setFont(f);
			
			int y0 = 200;
			int currPageNum = size + 1 - i*rowNum;
			currPageNum = currPageNum > rowNum ? rowNum : currPageNum - 1;
			
			
			if(i == 0){
				g2.drawString(toLineHeadLsjy(list.get(0),shmcW),100, y0);
				y0 += 40;
			}
			
			for(int j = 1; j <= currPageNum; j++){
				int x0 = 100;
				LsjyExlInfo lsjy = list.get(add + j);
				String dataStr = toLineContentLsjy(lsjy);
				g2.drawString(dataStr,x0, y0);
				y0 += rowSpacing + fontSize;
			}
			add += rowNum;
		}
		return allImg;
	}
	
	/**
	 * 生成一张流水交易图片
	 * @param list
	 * @param isHasHead
	 * @return
	 */
	public static BufferedImage createLsjyImg(List<LsjyExlInfo> list){
		BufferedImage img = new BufferedImage(img_w, img_h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		//设置笔刷
		g2d.setColor(Color.WHITE);
		//填充整个屏幕为白色
		g2d.fillRect(0, 0, img_w, img_h);
		//设置笔刷
		g2d.setColor(fontColor);
		//抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font f = new Font(fontStyle, Font.PLAIN, fontSize);
		g2d.setFont(f);
		
		if(list != null)
		{
			int x0 = pointX0
					, y0 = pointY0
					, start = 0;
			
			int len = list.size();
			for(int i = start; i < len; i++)
			{
				x0 = pointX0;
				LsjyExlInfo data = list.get(i);
				g2d.drawString(data.getShid(), x0, y0);
				x0+=cs[0];
				g2d.drawString(data.getShmc(), x0, y0);
				x0+=cs[1];
				g2d.drawString(data.getKh(), x0, y0);
				x0+=cs[2];
				g2d.drawString(data.getRqsj(), x0, y0);
				x0+=cs[3];
				g2d.drawString(data.getJyje(), x0, y0);
				x0+=cs[4];
				y0 += rowSpacing + fontSize;
			}
		}
		g2d.dispose();
		return img;
	}
	
	public static int[] sumMaxWidth(List<LsjyExlInfo> list){
		if(list.size() >= 2)
		{
			int a = list.get(0).getShid().length();
			int b = list.get(1).getShid().length();
			cs[0] =  a > b ? a*13 : b*13;
			
			a = list.get(0).getShmc().length();
			b = list.get(1).getShmc().length();
			cs[1] = a > b ? a*fontSize : b*fontSize;
			
			a = list.get(0).getKh().length();
			b = list.get(1).getKh().length();	
			cs[2] = a > b ? a*13 : b*13;
			
			a = list.get(0).getRqsj().length();
			b = list.get(1).getRqsj().length();
			cs[3] = a > b ? a*13 : b*13;
			
			a = list.get(0).getJyje().length();
			b = list.get(1).getJyje().length();
			cs[4] = a > b ? a*fontSize : b*fontSize;
		}
		return cs;
	}
	
	public static void setCs(int[] cjw){
		cs[0] = cjw[0]+A;
		cs[1] = cjw[1]+A;
		cs[2] = cjw[2]+A;
		cs[3] = cjw[3]+A;
		cs[4] = cjw[4]+B;
	}
	
	public static int[] getCs(){
		return cs;
	}
	
	@Deprecated
	private static String toLineContentLsjy(LsjyExlInfo lsjy){
		StringBuilder sb = new StringBuilder();
		sb.append(lsjy.getShid());
		sb.append("    ");//4
		sb.append(lsjy.getShmc());
		sb.append("     ");//5
		sb.append(lsjy.getKh());
		sb.append("     ");//4
		sb.append(lsjy.getRqsj());
		sb.append("    ");//4
		sb.append(lsjy.getJyje());
		return sb.toString();
	}
	
	//一个" " 14磅
	@Deprecated
	private static String toLineHeadLsjy(LsjyExlInfo lsjy,int w){
		cs = new int[5];
		int num = ((w-104)/14 + 1)/2; 
		StringBuilder sb = new StringBuilder();
		
		sb.append("   ");
		sb.append(lsjy.getShid());
		sb.append("    ");
		cs[0] = lsjy.getShid().length()*fontSize + 14*10;
		
		sb.append("    ");//4
		
		for(int i = 0; i < num; i++)
			sb.append(" ");
		sb.append(lsjy.getShmc());
		for(int i = 0; i < num; i++)
			sb.append(" ");
		cs[1] = lsjy.getShmc().length()*fontSize + 14*(num+1)*2+14;
		
		sb.append("     ");//5
		
		sb.append("     ");
		sb.append(lsjy.getKh());
		sb.append("     ");
		cs[2] = lsjy.getKh().length()*fontSize + 14*16;
		
		sb.append("     ");//4
		
		sb.append("        ");
		sb.append(lsjy.getRqsj());
		sb.append("     ");
		cs[3] = lsjy.getRqsj().length()*fontSize + 14*15;
		
		sb.append("    ");//4
		
		sb.append(" ");
		sb.append(lsjy.getJyje());
		cs[4] = lsjy.getJyje().length()*fontSize + 14*6;
		return sb.toString();
	}
	
	/**
	 * 计算商户名称的宽度
	 * @param list
	 * @return
	 */
	@Deprecated
	private static int shmcW(List<LsjyExlInfo> list){
		if(list.size() >= 2)
		{
			int len = list.get(1).getShmc().length();
			return len*(fontSize + 1) ;
		}
		return 0;
	}
	
	
	/*
	public static void main(String[] args) {
		try {
		List<LsjyExlInfo> l = ReadExlInfoUtil.readLsjyExl("C:\\Users\\Lenovo\\Desktop\\测试\\cs.xls");
		BufferedImage[] i = createLsyjImg(l);
		
			int a = 1;
			for (BufferedImage bufferedImage : i) {
				ImgOperateUtil.printImg(bufferedImage, "C:\\Users\\Lenovo\\Desktop\\测试\\lsjy_0"+a+".jpg");
				a++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
}
