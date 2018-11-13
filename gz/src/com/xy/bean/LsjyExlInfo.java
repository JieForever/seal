package com.xy.bean;


/*
 * 流水交易exl信息
 */
public class LsjyExlInfo {
	
	private String shid;//商户编号
	private String shmc;//商户名称
	private String kh;//卡号
	private String rqsj;//日期时间
	private String jyje;//交易金额
	
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
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getRqsj() {
		return rqsj;
	}
	public void setRqsj(String rqsj) {
		this.rqsj = rqsj;
	}
	public String getJyje() {
		return jyje;
	}
	public void setJyje(String jyje) {
		if(jyje.contains("."))
		{
			/*double d = Double.valueOf(jyje);
			BigDecimal   b   =   new   BigDecimal(d);
			double   d2   =   b.setScale( 7,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
			jyje =  ""+d2;*/
			
			String [] s = jyje.split("\\.");
			if(s.length == 2)
			{
				if(s[1].length() > 2)
				{
					s[1] = s[1].substring(0, 2);
					jyje = s[0] +"."+ s[1];
				}
			}
		}
		this.jyje = jyje;
	}
	@Override
	public String toString() {
		return "LsjyExlInfo [shid=" + shid + ", shmc=" + shmc + ", kh=" + kh
				+ ", rqsj=" + rqsj + ", jyje=" + jyje + "]";
	}

	
	
}
