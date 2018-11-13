package com.xy.bean;


/**
 * ������ˮbean
 * @author Lenovo
 *
 */
public class RzlsExlInfo {

	
	private String shid;//�̻����
	private String shmc;//�̻�����
	private String qsrq;//��������
	private String qfje;//��ֽ��
	private String zhmc;//�˻�����
	private String zh;//�˻�
	private String khh;	//������

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

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getQfje() {
		return qfje;
	}

	public void setQfje(String qfje) {
		if(qfje.contains("."))
		{
			String [] s = qfje.split("\\.");
			if(s.length == 2)
			{
				if(s[1].length() > 2)
				{
					s[1] = s[1].substring(0, 2);
					qfje = s[0] +"."+ s[1];
				}
			}
		}
		this.qfje = qfje;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}

	public String getZhmc() {
		return zhmc;
	}

	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}

	@Override
	public String toString() {
		return "RzlsExlInfo [shid=" + shid + ", shmc=" + shmc + ", qsrq="
				+ qsrq + ", qfje=" + qfje + ", zhmc=" + zhmc + ", zh=" + zh
				+ ", khh=" + khh + "]";
	}

	
	
	
}
