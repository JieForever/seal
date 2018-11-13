package com.xy.enumclass;

public enum ImgTypeEnum {

	TYPE1(1,"1","3��ǩ����ͼ�ϳ�1��")
	,TPYE2(2,"2","2��ǩ����ͼ�ϳ�1��")
	,TYPE3(3,"3","1��ǩ����");
	
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
