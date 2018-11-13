package com.xy.enumclass;

public enum ImgTypeEnum {

	TYPE1(1,"1","3张签购单图合成1张")
	,TPYE2(2,"2","2张签购单图合成1张")
	,TYPE3(3,"3","1张签购单");
	
	private Integer code;
	private String type;
	private String description;
	private ImgTypeEnum(Integer c,String imgType, String desc){
		code = c;
		type = imgType;
		description = desc;
	}
	
	public static Integer getCodeByType(String t){
		 for(ImgTypeEnum ite: ImgTypeEnum.values()){
			 if(ite.type.equals(t))
				 return ite.code;
		 }
		 return null;
	}
	
	public static String getDescByType(String t){
		for(ImgTypeEnum ite: ImgTypeEnum.values()){
			if(ite.type.equals(t))
				return ite.description;
		}
		return null;
	}
}
