package com.xy.bean;

import java.util.Arrays;

/*
 * 签购单exl信息
 */
public class QgdExlInfo {
	
	private String shid;//商户编号
	private String shmc;//商户名称
	private String qzmc;//签章名称
	private String yyzzh;//营业执照号
	private String tplx;//图片类型(版本)
	private String [] qzwz;//签章位置
	private String [] qzjd;//签章角度
	private String qzzb;//签章占比
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getShmc() {
		return shmc;
	}
	public void setShmc(String shmc) {
		this.shmc = shmc;
	}
	public String getQzmc() {
		return qzmc;
	}
	public void setQzmc(String qzmc) {
		this.qzmc = qzmc;
	}
	public String getYyzzh() {
		return yyzzh;
	}
	public void setYyzzh(String yyzzh) {
		this.yyzzh = yyzzh;
	}
	public String getTplx() {
		return tplx;
	}
	public void setTplx(String tplx) {
		this.tplx = tplx;
	}
	public String[] getQzwz() {
		return qzwz;
	}
	public void setQzwz(String[] qzwz) {
		this.qzwz = qzwz;
	}
	public String[] getQzjd() {
		return qzjd;
	}
	public void setQzjd(String[] qzjd) {
		this.qzjd = qzjd;
	}
	public String getQzzb() {
		return qzzb;
	}
	public void setQzzb(String qzzb) {
		this.qzzb = qzzb;
	}
	@Override
	public String toString() {
		return "QgdExlInfo [shid=" + shid + ", shmc=" + shmc + ", qzmc=" + qzmc
				+ ", yyzzh=" + yyzzh + ", tplx=" + tplx + ", qzwz="
				+ Arrays.toString(qzwz) + ", qzjd=" + Arrays.toString(qzjd)
				+ ", qzzb=" + qzzb + "]";
	}
	
}
