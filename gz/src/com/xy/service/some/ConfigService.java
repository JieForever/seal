package com.xy.service.some;

import com.xy.utils.OperateProperFile;

public class ConfigService {

	public static final String QZ_LIST_MODEL = "qzmodel.properties";
	public static final String MODEL_CONFIG = "model-config.properties";
	public static final String FONTCOLOR_CONFIG = "fontcolor-config.properties";

	private OperateProperFile opf;

	public ConfigService(String p) {
		opf = new OperateProperFile(p);
	}

	public String[] getKeys() {
		return opf.read().getKeys();
	}

	public String getValue(String key) {
		return opf.read().getPro().getProperty(key);
	}
	
	/**
	 * 保存修改签章模板图片
	 * @param funType
	 * @param model
	 */
	public void saveModelConfig(String funType, String model) {
	
		if ("签购单".equals(funType)) {
			opf.read().update("qgd", model);
		} else if ("流水交易".equals(funType)) {
			opf.read().update("lsjy", model);
		} else if ("入账流水".equals(funType)) {
			opf.read().update("rzls", model);
		}
		
	}
	
	/**
	 * 保存修改的字体颜色
	 * @param funType
	 * @param R
	 * @param G
	 * @param B
	 */
	public void saveRGBConfig(String funType, String R, String G, String B) {
	    String rgb = R+","+G+","+B;
		if ("签购单".equals(funType)) {
			opf.read().update("qgd_RGB", rgb);
		} else if ("流水交易".equals(funType)) {
			opf.read().update("lsjy_RGB", rgb);
		} else if ("入账流水".equals(funType)) {
			opf.read().update("rzls_RGB", rgb);
		}
	}
	
	public static void main(String[] args) {
	  //new ConfigService(ConfigService.MODEL_CONFIG).saveModelConfig("流水交易","sdf");
		 //String [] s=  new ConfigService(ConfigService.MODEL_CONFIG).getKeys();
		//System.out.println(Arrays.toString(s));
	
	}
}
