package com.xy.bean;

/**
 * 流水交易对应的签章信息
 * @author Lenovo
 *
 */
public class LsjyQzExlInfo extends QzExlInfo{

	private String shid;//商户编号
	private String yyzz;//营业执照

	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getYyzz() {
		return yyzz;
	}
	public void setYyzz(String yyzz) {
		this.yyzz = yyzz;
	}
	
}
