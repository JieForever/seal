package com.xy.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import com.xy.bean.RzlsExlInfo;

public class DrawRzls {

	public static int A = 50;

	public static int img_h = 2339;//生成图片的高
	public static int img_w = 1654;//生成图片的宽
	public static int img_w_heng = 2500;
	public static int fontSize = 25;//字体大小
	public static Color fontColor = Color.black;//字体颜色
	public static String fontStyle = "宋体";
	public static int rowSpacing = 15;//上下行间距
	
	public static int pointX0 = 120;
	public static int pointY0 = 170;
	
	private static int[] cs = new int[7];
	
	/**
	 * 生成一张入账流水图片
	 * @param list
	 * @param type 版式    默认：竖版；1：横版
	 * @return
	 */
	public static BufferedImage createRzlsImg(List<RzlsExlInfo> list, int type){
		int w = img_w
			,h = img_h;
		if(1 == type)
		{
			w = img_w_heng;
			h = img_w;
		}
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		//设置笔刷
		g2d.setColor(Color.WHITE);
		//填充整个屏幕为白色
		g2d.fillRect(0, 0, w, h);
		//设置笔刷
		g2d.setColor(fontColor);
		//抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font f = new Font(fontStyle, Font.PLAIN, fontSize);
		g2d.setFont(f);
		
		if(list != null)
		{
			int x0 = pointX0//左边距
				, y0 = pointY0//上边距
				, start = 0;
			int len = list.size();
			for(int i = start; i < len; i++)
			{
				x0 = pointX0;
				RzlsExlInfo data = list.get(i);
				g2d.drawString(data.getShid(), x0, y0);
				x0+=cs[0];
				g2d.drawString(data.getShmc(), x0, y0);
				x0+=cs[1];
				g2d.drawString(data.getQsrq(), x0, y0);
				x0+=cs[2];
				g2d.drawString(data.getQfje(), x0, y0);
				x0+=cs[3];
				g2d.drawString(data.getZhmc(), x0, y0);
				x0+=cs[4];
				g2d.drawString(data.getZh(), x0, y0);
				x0+=cs[5];
				g2d.drawString(data.getKhh(), x0, y0);
				x0+=cs[6];
				y0 += rowSpacing + fontSize;
			}
		}
		g2d.dispose();
		return img;
	}
	
	public static void setCs(int[] cjw){
		cs[0] = cjw[0]+A;
		cs[1] = cjw[1]+A;
		cs[2] = cjw[2]+A;
		cs[3] = cjw[3]+A;
		cs[4] = cjw[4]+A;
		cs[5] = cjw[5]+A;
		cs[6] = cjw[6]+A;
	}
	
	public static int[] getCs(){
		return cs;
	}
	
	
	public static int[] sumMaxWidth2(List<RzlsExlInfo> list){
		if(list.size() >= 2)
		{
			FontSum fs = new FontSum();
			cs[0] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getShid(), list.get(1).getShid());
			cs[1] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getShmc(), list.get(1).getShmc());
			cs[2] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getQsrq(), list.get(1).getQsrq());
			cs[3] = 50 + fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getQfje(), list.get(1).getQfje());
			cs[4] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getZhmc(), list.get(1).getZhmc());
			cs[5] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getZh(), list.get(1).getZh());
			cs[6] = fs.sumMaxWidthFont(fontStyle, fontSize, list.get(0).getKhh(), list.get(1).getKhh());
			
			fs.dispose();
		}
		return cs;
	}
	
	
	@Deprecated
	public static int[] sumMaxWidth(List<RzlsExlInfo> list){
		if(list.size() >= 2)
		{
			int a = list.get(0).getShid().length();
			int b = list.get(1).getShid().length();
			cs[0] =  a > b ? a*13 : b*13;
			
			a = list.get(0).getShmc().length();
			b = list.get(1).getShmc().length();
			cs[1] = a > b ? a*fontSize : b*fontSize;
			
			a = list.get(0).getQsrq().length();
			b = list.get(1).getQsrq().length();	
			cs[2] = a > b ? a*13 : b*13;
			
			a = list.get(0).getQfje().length();
			b = list.get(1).getQfje().length();
			cs[3] = a > b ? a*20 : b*20;
			
			a = list.get(0).getZhmc().length();
			b = list.get(1).getZhmc().length();
			cs[4] = a > b ? a*fontSize : b*fontSize;
			
			a = list.get(0).getZh().length();
			b = list.get(1).getZh().length();
			cs[5] = a > b ? a*15 : b*15;
			
			a = list.get(0).getKhh().length();
			b = list.get(1).getKhh().length();
			cs[6] = a > b ? a*fontSize : b*fontSize;
		}
		return cs;
	}
	
	//一个" " 14磅
	@Deprecated
	private static String toLineContentLsjy(RzlsExlInfo rzls, int[] cs, int[] ds){
		StringBuilder sb = new StringBuilder();
		sb.append(rzls.getShid());
		sb.append("    ");
		sb.append(rzls.getShmc());
		sb.append("    ");
		sb.append(rzls.getQsrq());
		sb.append("     ");
		sb.append(rzls.getZhmc());
		for(int i = 0; i < (cs[4]-ds[4])/14;i++)
			sb.append(" ");
		sb.append("    ");
		sb.append(rzls.getZh());
		for(int i = 0; i < (cs[5]-ds[5])/14;i++)
			sb.append(" ");
		sb.append("    ");
		sb.append(rzls.getKhh());
		sb.append("     ");
		sb.append(rzls.getQfje());
		return sb.toString();
	}
	
	//一个" " 14磅
	@Deprecated
	private static String toLineHeadLsjy(RzlsExlInfo rzls, int[] dataWidth){
		cs = new int[7];
		
		StringBuilder sb = new StringBuilder();
		sb.append("    ");//52
		sb.append(rzls.getShid());
		sb.append("    ");//52
		cs[0] = rzls.getShid().length()*fontSize + 52 + 52 +24;
	
		sb.append("    ");//52
		
		int headShmc = dataWidth[1]-rzls.getShmc().length()*fontSize;
		int num = 0;
		if(headShmc > 0)
			num = (int)Math.ceil(headShmc/28.0);
		for(int i = 0; i < num; i++)//
			sb.append(" ");
		sb.append(rzls.getShmc());
		for(int i = 0; i < num; i++)
			sb.append(" ");
		cs[1] = rzls.getShmc().length()*fontSize + 28*num - 2*num + 52 +20;
		sb.append("     ");//4
		
		sb.append(rzls.getQsrq());
		cs[2] = rzls.getQsrq().length()*fontSize + 84;
		
		sb.append("    ");//4
		
		int headZhmc = dataWidth[4]-rzls.getZhmc().length()*fontSize;
		int num2 = 0;
		if(headZhmc > 0)
			num2 = (int)Math.ceil(headZhmc/28.0);
		for(int i = 0; i < num2; i++)
			sb.append(" ");
		sb.append(rzls.getZhmc());
		for(int i = 0; i < num2; i++)
			sb.append(" ");
		cs[3] = rzls.getZhmc().length()*fontSize +  28*num2 - 2*num2 + 52;
		
		sb.append("    ");//4
		
		int headZh = dataWidth[5]-rzls.getZh().length()*fontSize;
		int num3 = 0;
		if(headZh > 0)
			num3 = (int)Math.ceil(headZh/28.0);
		for(int i = 0; i < num3; i++)
			sb.append(" ");
		sb.append(rzls.getZh());
		for(int i = 0; i < num3; i++)
			sb.append(" ");
		cs[4] = rzls.getZh().length()*fontSize+  28*num3 - 2*num3 + 52;
		
		for(int i = 0; i < (num+num2+num3)/14; i++)
			sb.append(" ");
		sb.append("    ");//4
		
		sb.append(rzls.getKhh());
		cs[5] = rzls.getKhh().length()*fontSize+ 14*5;
		
		sb.append("    ");//52
		sb.append(rzls.getQfje());
		cs[6] = rzls.getQfje().length()*fontSize + 76;
		
		System.out.println(Arrays.toString(cs));
		System.out.println(Arrays.toString(dataWidth));
		return sb.toString();
	}
	
	/**
	 * 计算第一列数据宽度
	 * @param list
	 * @return
	 */
	@Deprecated
	private static int[] getDataWidth(List<RzlsExlInfo> list)
	{
		int[] r = new int[]{0,0,0,0,0,0,0};
		if(list.size() >= 2)
		{
			r[0] = list.get(1).getShid().length()*fontSize;
			r[1] = list.get(1).getShmc().length()*fontSize;
			r[2] = list.get(1).getQsrq().length()*fontSize;
			r[3] = list.get(1).getQfje().length()*fontSize;
			r[4] = list.get(1).getZhmc().length()*fontSize;
			r[5] = list.get(1).getZh().length()*fontSize;
			r[6] = list.get(1).getKhh().length()*fontSize;
		}
		return r;
	}
	
	

	
}//
