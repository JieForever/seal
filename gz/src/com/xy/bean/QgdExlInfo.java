package com.xy.bean;

import java.util.Arrays;

/*
 * ǩ����exl��Ϣ
 */
public class QgdExlInfo {
	
	private String shid;//�̻����
	private String shmc;//�̻�����
	private String qzmc;//ǩ������
	private String yyzzh;//Ӫҵִ�պ�
	private String tplx;//ͼƬ����(�汾)
	private String [] qzwz;//ǩ��λ��
	private String [] qzjd;//ǩ�½Ƕ�
	private String qzzb;//ǩ��ռ��
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
