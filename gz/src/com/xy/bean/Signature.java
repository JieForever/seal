package com.xy.bean;

import java.awt.Color;

/**
 * 圆章
 * @author Lenovo
 *
 */
public class Signature {
	
	public static double bottomAreaAngle = Math.PI*5/12;//章的下半域所占的角度
	public static double topAreaAngle = Math.PI*19/12;//章的上半域所占的角度
	
	/*
	 * 依据签章名字数个数来生成huanW和字的大小
	 * 默认先设置签章名最少有5个中文字，最多30个
	 * 字体拉长，字宽随着字体大小变，字长大约在字宽的1.8-2.8之间变
	 */
	private static final int MINNUM = 5;
	private static final int SUOJV = 10;//32;
	private static final int TOPSIZE_5 = 28;
	private static final int BOTTOMSIZE_5 = 22;
	private static final double BL_TOP = 0.6;
//	private static final double BL_BOTTOM = 17;
	private static final double BL_SCALE = 0.04;
	
	private Color sColor = Color.RED;//章颜色
	private int huanW;//圆环的宽
	private int topStrFontSize ;//顶部文字大小
	private String sCenterShap;//中心图形
	private String sTopStr;//顶部文字串
	private String sBottomStr;//底部文字串
	//private double sRadius;//圆半径
	private double sPercent;//占比
	private int centerShapSize;//中间文字大小
	private int bottomStrFontSize;//底部文字大小
	private double rotationAngle;//旋转角度 0-360
	public double rotationRadian;//旋转弧度
	public double fontScaleY = 1.8;//字体在Y轴方向放大倍数
	

	
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
