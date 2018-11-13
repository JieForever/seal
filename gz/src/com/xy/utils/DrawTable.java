package com.xy.utils;

import java.awt.image.BufferedImage;

import com.xy.bean.TableXY;
import com.xy.exception.ParamHasProblemException;

public class DrawTable {

	private int x1; //table 左上角横坐标
	private int y1; //table 左上角纵坐标
	private int x2; //table 右下角横坐标
	private int y2; //table 右下角纵坐标
	private TableXY table;
	
	
	public DrawTable(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public DrawTable initTable(TableXY table){
		this.table = table;
		return this;
	}
	
	/**
	 * 绘制行与行，列与列间距相等的表格
	 * @param img
	 */
	public void createSameSpaceTable(BufferedImage img){
		if(img == null)
			throw new ParamHasProblemException("img cat not equal null");
		if(table == null)
			throw new ParamHasProblemException("you must invoke initTable(..) method");
	
		//绘制列
		int colSameSpace = x2-x1/table.getColNum(); 
		table.getDl().drawSameHorizontalShiftLine(img, x1, y1, x1, y2, table.getColNum(),colSameSpace);
	//	table.getDl().drawSameHorizontalShiftLine(img, x2, y1, x2, y2, 1);
		//绘制行
		int rowSameSpace = y2-y1/table.getRowNum();
		table.getDl().drawSameVerticalShiftLine(img, x1, y1, x2, y1, table.getRowNum(), rowSameSpace);
	//	table.getDl().drawSameVerticalShiftLine(img, x1, y2, x2, y2, 1);
	}
	
	/**
	 * 依据table属性绘制等间距表格，
	 * 但是最后一行或者一列的间距可能会与之前的不想等
	 * @param img
	 */
	public void createDefSameSpaceTable(BufferedImage img){
		if(img == null)
			throw new ParamHasProblemException("img cat not equal null");
		if(table == null)
			throw new ParamHasProblemException("you must invoke initTable(..) method");
		//绘制列
		table.getDl().drawSameHorizontalShiftLine(img, x1, y1, x1, y2, table.getColNum(), table.getColSpace());
	//	table.getDl().drawSameHorizontalShiftLine(img, x2, y1, x2, y2, 1);
		//绘制行
		table.getDl().drawSameVerticalShiftLine(img, x1, y1, x2, y1, table.getRowNum(), table.getRowSpace());
	//	table.getDl().drawSameVerticalShiftLine(img, x1, y2, x2, y2, 1);
	}
	
	/**
	 * 依据table属性绘制表格
	 * 从table中的间距数组中取值
	 * @param img
	 */
	public void createDiffSpaceTable(BufferedImage img){
		if(img == null)
			throw new ParamHasProblemException("img cat not equal null");
		if(table == null)
			throw new ParamHasProblemException("you must invoke initTable(..) method");
		
		//绘制列
		table.getDl().drawSameHorizontalShiftLine(img, x1, y1, x1, y2, table.getColNum(), table.getColSpaceArr());
	//	table.getDl().drawSameHorizontalShiftLine(img, x2, y1, x2, y2, 1);
		//绘制行
		table.getDl().drawSameVerticalShiftLine(img, x1, y1, x2, y1, table.getRowNum(), table.getRowSpaceArr());
	//	table.getDl().drawSameVerticalShiftLine(img, x1, y2, x2, y2, 1);
	}
	
	
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		if(x1 < 0)
			throw new ParamHasProblemException("x1 must >= 0 ");
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		if(y1 < 0)
			throw new ParamHasProblemException("y1 must >= 0");
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	
	
	
	
	
}
