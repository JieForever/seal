package com.xy.bean;

import java.awt.Color;

/**
 * Բ��
 * @author Lenovo
 *
 */
public class Signature {
	
	public static double bottomAreaAngle = Math.PI*5/12;//�µ��°�����ռ�ĽǶ�
	public static double topAreaAngle = Math.PI*19/12;//�µ��ϰ�����ռ�ĽǶ�
	
	/*
	 * ����ǩ������������������huanW���ֵĴ�С
	 * Ĭ��������ǩ����������5�������֣����30��
	 * �����������ֿ����������С�䣬�ֳ���Լ���ֿ��1.8-2.8֮���
	 */
	private static final int MINNUM = 5;
	private static final int SUOJV = 10;//32;
	private static final int TOPSIZE_5 = 28;
	private static final int BOTTOMSIZE_5 = 22;
	private static final double BL_TOP = 0.6;
//	private static final double BL_BOTTOM = 17;
	private static final double BL_SCALE = 0.04;
	
	private Color sColor = Color.RED;//����ɫ
	private int huanW;//Բ���Ŀ�
	private int topStrFontSize ;//�������ִ�С
	private String sCenterShap;//����ͼ��
	private String sTopStr;//�������ִ�
	private String sBottomStr;//�ײ����ִ�
	//private double sRadius;//Բ�뾶
	private double sPercent;//ռ��
	private int centerShapSize;//�м����ִ�С
	private int bottomStrFontSize;//�ײ����ִ�С
	private double rotationAngle;//��ת�Ƕ� 0-360
	public double rotationRadian;//��ת����
	public double fontScaleY = 1.8;//������Y�᷽��Ŵ���
	

	
	public Signature(Color sColor, String sTopStr, String sBottomStr, double sPercent,
			int topfontsize, int bottomfontsize,int huanW, double rotationAngle){
		this.sColor = sColor;
		this.sTopStr = sTopStr;
		this.sBottomStr = sBottomStr;
		this.sPercent = sPercent;
		setHuanW(huanW);
		setBottomStrFontSize(bottomfontsize);
		setTopStrFontSize(topfontsize);
		setRotationAngle(rotationAngle);
		fontScaleY = fontScaleY + (sTopStr.length() - MINNUM)*BL_SCALE;
	}
	
	public Signature(Color sColor,String sCenterShap,String sTopStr, String sBottomStr, double sPercent,
			int topStrFontSize, int centerShapSize, int bottomStrFontSize, int sHuanW,double rotationAngle){
		this.sColor = sColor;
		this.sCenterShap = sCenterShap;
		this.sTopStr = sTopStr;
		this.sBottomStr = sBottomStr;
		this.sPercent = sPercent;
		this.centerShapSize = centerShapSize;
		setHuanW(sHuanW);
		setTopStrFontSize(topStrFontSize);
		setBottomStrFontSize(bottomStrFontSize);
		setRotationAngle(rotationAngle);
	}
	
	public Color getsColor() {
		return sColor;
	}
	public void setsColor(Color sColor) {
		this.sColor = sColor;
	}
	public String getsCenterShap() {
		return sCenterShap;
	}
	public void setsCenterShap(String sCenterShap) {
		this.sCenterShap = sCenterShap;
	}
	public String getsTopStr() {
		return sTopStr;
	}
	public void setsTopStr(String sTopStr) {
		this.sTopStr = sTopStr;
	}
	public String getsBottomStr() {
		return sBottomStr;
	}
	public void setsBottomStr(String sBottomStr) {
		this.sBottomStr = sBottomStr;
	}
//	public double getsRadius() {
//		return sRadius;
//	}
//	public void setsRadius(double sRadius) {
//		this.sRadius = sRadius;
//	}
	public double getsPercent() {
		return sPercent;
	}

	public void setsPercent(double sPercent) {
		this.sPercent = sPercent;
	}

	public int getTopStrFontSize() {
		return topStrFontSize;
	}

	public void setTopStrFontSize(int topStrFontSize) {
		if(topStrFontSize <= 0){
			topStrFontSize = TOPSIZE_5;
		}
		int num = this.sTopStr.length();
		topStrFontSize = (int)(topStrFontSize - (num-MINNUM)*BL_TOP);
		//System.out.println(topStrFontSize);
		this.topStrFontSize = (int)(topStrFontSize);
	}

	public int getCenterShapSize() {
		return centerShapSize;
	}

	public void setCenterShapSize(int centerShapSize) {
		this.centerShapSize = centerShapSize;
	}

	public int getBottomStrFontSize() {
		return bottomStrFontSize;
	}

	public void setBottomStrFontSize(int bottomStrFontSize) {
		if(bottomStrFontSize <= 0){
			bottomStrFontSize = (int)BOTTOMSIZE_5;
		}
		this.bottomStrFontSize = (int)(bottomStrFontSize );
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
		this.rotationRadian = (2*Math.PI)*(rotationAngle/360.0);
	}

	public int getHuanW() {
		return huanW;
	}

	public void setHuanW(int huanW) {
		if(huanW <= 0){
			huanW = SUOJV;
		}
		int num = this.sTopStr.length();
		huanW = (int)(huanW - (num-MINNUM)*0.02);
		this.huanW = (int)(huanW );
	}

	@Override
	public String toString() {
		return "Signature [sColor=" + sColor + ", huanW=" + huanW
				+ ", topStrFontSize=" + topStrFontSize + ", sCenterShap="
				+ sCenterShap + ", sTopStr=" + sTopStr + ", sBottomStr="
				+ sBottomStr + ", sPercent=" + sPercent + ", centerShapSize="
				+ centerShapSize + ", bottomStrFontSize=" + bottomStrFontSize
				+ ", rotationAngle=" + rotationAngle + ", rotationRadian="
				+ rotationRadian + ", fontScaleY=" + fontScaleY + "]";
	}

	
	
}
