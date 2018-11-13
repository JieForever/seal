package com.xy.bean;

import com.xy.exception.ParamHasProblemException;
import com.xy.utils.DrawLine;

public class TableXY {

	public static int minSpace = 10;
	//table���м���
	private int colNum;	
	private int rowNum;
	//table���еļ��
	private int[] colSpaceArr;
	private int[] rowSpaceArr;
	private int colSpace;
	private int rowSpace;
	//����
	private DrawLine dl;
	
	/**
	 * ������캯����Ϊ�˷������ÿ��ÿ����ͬ����
	 * @param colNum
	 * @param rowNum
	 * @param colSpace
	 * @param rowSpace
	 */
	public TableXY(int colNum, int rowNum, int colSpace, int rowSpace, DrawLine dl){
		setColNum(colNum);
		setRowNum(rowNum);
		setColSpace(colSpace);
		setRowNum(rowNum);
		setDl(dl);
	}
	
	/**
	 * ������캯���ǿ��Կ���ÿ��ÿ���в�ͬ�ļ��
	 * @param colNum
	 * @param rowNum
	 * @param colSpace
	 * @param rowSpace
	 */
	public TableXY(int colNum, int rowNum, int[] colSpace, int[] rowSpace, DrawLine dl){
		setColNum(colNum);
		setRowNum(rowNum);
		setColSpaceArr(colSpace);
		setRowSpaceArr(rowSpace);
		setDl(dl);
	}
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		if(rowNum < 0)
			throw new ParamHasProblemException("row line num cat not < 1");
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		if(colNum < 0)
			throw new ParamHasProblemException("col line num cat not < 1");
		this.colNum = colNum;
	}

	public int[] getColSpaceArr() {
		return colSpaceArr;
	}

	public void setColSpaceArr(int[] colSpaceArr) {
		int len = colSpaceArr.length;
		if(len > 1 && len != colNum)
			throw new ParamHasProblemException("colSpaceArr length not equal rowNum ; rowSpaceArr of length is: " + len);
		for(int arr: colSpaceArr){
			if(minSpace > arr)
				throw new ParamHasProblemException("colSpaceArr exist value < minSpace, :" + arr);
		}
		this.colSpaceArr = colSpaceArr;
	}

	public int[] getRowSpaceArr() {
		return rowSpaceArr;
	}

	public void setRowSpaceArr(int[] rowSpaceArr) {
		int len = rowSpaceArr.length;
		if(len > 1 && len != rowNum)
			throw new ParamHasProblemException("rowSpaceArr length not equal rowNum ; rowSpaceArr of length is: " + len);
		for(int arr: rowSpaceArr){
			if(minSpace > arr)
				throw new ParamHasProblemException("rowSpaceArr exist value < minSpace, :" + arr);
		}
		this.rowSpaceArr = rowSpaceArr;
	}

	public int getColSpace() {
		return colSpace;
	}

	public void setColSpace(int colSpace) {
		if(colSpace < minSpace)
			throw new ParamHasProblemException("colSpace can not <" + minSpace);
		this.colSpace = colSpace;
	}

	public int getRowSpace() {
		return rowSpace;
	}

	public void setRowSpace(int rowSpace) {
		if(rowSpace < minSpace)
			throw new ParamHasProblemException("rowSpace can not <" + minSpace);
		this.rowSpace = rowSpace;
	}

	public DrawLine getDl() {
		return dl;
	}

	public void setDl(DrawLine dl) {
		if(dl == null)
			throw new NullPointerException("dl is null");
		this.dl = dl;
	}
	
	
	
}
