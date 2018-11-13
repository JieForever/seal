package com.xy.bean;

/**
 * 签章Excel共有信息。
 * 当有不同的个性信息，可以创建一个新的pojo并继承该签章公共类即可
 * @author Lenovo
 *
 */
public class QzExlInfo {

	public String qzmc;//签章名称
	public String qzwz;//签章位置
	public String qzjd;//签章角度
	public String qzzb;//签章占比
	
	

	public String getQzmc() {
		return qzmc;
	}
	public void setQzmc(String qzmc) {
		this.qzmc = qzmc;
	}
	public String getQzwz() {
		return qzwz;
	}
	public void setQzwz(String qzwz) {
		this.qzwz = qzwz;
	}
	public String getQzjd() {
		return qzjd;
	}
	public void setQzjd(String qzjd) {
		this.qzjd = qzjd;
	}
	public String getQzzb() {
		return qzzb;
	}
	public void setQzzb(String qzzb) {
		this.qzzb = qzzb;
	}
	
}
