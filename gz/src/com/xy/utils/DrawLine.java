package com.xy.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.xy.exception.ParamHasProblemException;

public class DrawLine {
	
	private static final int DEFAULT = 20; 
	//������ϸ
	private int lineHeight;
	//����rgb
	private int lineR;
	private int lineG;
	private int lineB;
	
	public DrawLine(int lh,int r, int g, int b){
		lineHeight = lh;
		lineR = r;
		lineG = g;
		lineB = b;
	}
	
	private void g2dLine(Graphics2D g2, int x1, int y1, int x2, int y2){
		if(g2 == null)
			throw new NullPointerException("g2 is null");
		//�����
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//���û�����ɫ
		g2.setColor(new Color(lineR, lineG, lineB));
		BasicStroke bs = new BasicStroke(lineHeight,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(bs);
		g2.drawLine(x1, y1, x2, y2);
	}
	
	
	/**
	 * ������ͬ��ˮƽλ��ֱ��
	 * 
	 *  ��x1,y1��                                                ��x1+space,y1��
	 *         \						   \
	 *          \        ------->			\
	 *           \							 \
	 *           ��x2,y2��					��x2+space,y2��
	 * @param img  
	 * @param x1 �߶����x����
	 * @param y1 �߶����y����
	 * @param x2 �߶��յ�x����
	 * @param y2 �߶��յ�y����
	 * @param lineNum ������������
	 * @param space ����֮��ļ��
	 */
	public void drawSameHorizontalShiftLine(BufferedImage img, int x1, int y1, int x2, int y2, int lineNum, int ...space){
		if(img == null)
			throw new NullPointerException("img is null");
		if(lineNum < 0)
			throw new IllegalArgumentException("lineNum can not equel negative number");
		int len = 0;
		if(space != null)
			len = space.length;
		if(len > 1 && lineNum > len)
			throw new ParamHasProblemException("lineNum can not > space.length");
		Graphics2D g2 = img.createGraphics();
		for(int i = 0; i < lineNum; i++)
		{
			g2dLine(g2, x1, y1, x2, y2);
			x1+= len == 0 ? DEFAULT : len == 1 ? space[0] : space[i];
			x2+= len == 0 ? DEFAULT : len == 1 ? space[0] : space[i];
		}
		g2dLine(g2, x1, y1, x2, y2);
		g2.dispose();
	}
	
	/**
	 * ������ͬ�Ĵ�ֱλ��ֱ��
	 * 
	 *  ��x1,y1��                                                ��x1,y1+space��
	 *         \						   \
	 *          \        ------->			\
	 *           \							 \
	 *           ��x2,y2��					��x2,y2+space��
	 * @param img  
	 * @param x1 �߶����x����
	 * @param y1 �߶����y����
	 * @param x2 �߶��յ�x����
	 * @param y2 �߶��յ�y����
	 * @param lineNum ������������
	 * @param space ����֮��ļ��
	 */
	public void drawSameVerticalShiftLine(BufferedImage img, int x1, int y1, int x2, int y2, int lineNum, int ...space){
		if(img == null)
			throw new NullPointerException("img is null");
		if(lineNum < 0)
			throw new IllegalArgumentException("lineNum can not equel negative number");
		int len = 0;
		if(space != null)
			len = space.length;
		if(len >1 && lineNum > len)
			throw new ParamHasProblemException("lineNum can not > space.length");
		Graphics2D g2 = img.createGraphics();
		for(int i = 0; i < lineNum; i++)
		{
			g2dLine(g2, x1, y1, x2, y2);
			y1+= len == 0 ? DEFAULT : len == 1 ? space[0] : space[i];
			y2+= len == 0 ? DEFAULT : len == 1 ? space[0] : space[i];
		}
		g2dLine(g2, x1, y1, x2, y2);
		g2.dispose();
	}
	
	
	/*
	public static void main(String[] args) {
		
		BufferedImage newImg = new BufferedImage(1000,1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = newImg.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		g.dispose();
		
		DrawLine dl = new DrawLine(2, 128, 128, 128);
		TableXY tableXY = new TableXY(3, 3, new int[]{20,20,20}, new int[]{20,20,20},dl);
		DrawTable dt = new DrawTable(100, 100, 900, 900);
		dt.initTable(tableXY).createDiffSpaceTable(newImg);
		try {
			ImgOperateUtil.printImg(newImg, "C:\\Users\\Lenovo\\Desktop\\c\\cjw1.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
