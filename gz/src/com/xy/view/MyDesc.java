package com.xy.view;

public class MyDesc {

	public static String qgdysb(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)在签购单图片的压缩包里，只容许有一个文件夹，而所有图片应该在这个唯一的文件夹里，\r\n");
		sb.append("   且不能再嵌套文件夹，还有压缩完的压缩文件名要和这个唯一的文件夹同名。\r\n");
		sb.append("      例如:\r\n");
		sb.append("         唯一的文件夹名为“结果”,则压缩文件名必须为“结果.rar”或“结果.zip”\r\n");
		sb.append("\r\n");
		sb.append("(2)单张签购单的图片分辨率不要小于150*150，2合1、3合1的图片分辨率不要小于500*500，\r\n");
		sb.append("   而且拍摄时最好光线充足明亮，使得整张图片明亮均匀，");
		sb.append("   图片也不要多余与签购单内容无关的部分，否则会影响最后盖章结果。\r\n");
		sb.append("\r\n");
		sb.append("(3)签购单图片的命名要和Excel里的商户名一致，而且所有图片的都是.jpg格式，\r\n");
		sb.append("   尤其在图片命名方面注意不要多字也不要少字，最显著的可能是开头或结尾多了个空格。");
		return sb.toString();
	}
	
	public static String qgdexl(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)Excel文件的第1行要作为数据的表头使用，所以软件不会处理这一行,\r\n");
		sb.append("   而且签购单要按以下模板，列与列之间不可调换顺序。\r\n");
		sb.append("   Excel表头模板:\r\n");
		sb.append("   ______________________________________________________________________________________________________\r\n");
		sb.append("   |商户号|商户名称|签章名称|营业执照号|图片版本|签章位置|签章位置|签章位置|签章角度|签章角度|签章角度|签章占比| \r\n");
		sb.append("\r\n");
		sb.append("(2)在读取Excel文件时,软件除了不检查“营业执照号”,其他均会检查\r\n");
		sb.append("   a.商户号、商户名称、签章名称不可为空\r\n");
		sb.append("   b.图片版本取值1或2\r\n");
		sb.append("   c.图片版本=1时,签章位置取值范围1-9;图片版本=2时,签章位置取值范围1-6;且签章位置不可相同\r\n");
		sb.append("   d.签章角度取值0-360\r\n");
		sb.append("   e.签章占比取值0%-100%\r\n");
		sb.append("\r\n");
		sb.append("(3)签购单Excel数据不能超过100行，超过的数据不会处理。\r\n");
		return sb.toString();
	}
	
	public static String lsysb(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)流水信息Excel文件要按以下模板，列与列之间不可调换顺序。\r\n");
		sb.append("   Excel表头模板:\r\n");
		sb.append("   _______________________________________________________\r\n");
		sb.append("   | 商户编号  | 商户名称  | 卡号  | 日期时间  | 交易金额  |\r\n");
		sb.append("\r\n");
		sb.append("(2)流水信息Excel文件，只检测商户编号、商户名称、卡号、时间、金额这五列数据是否为空。\r\n");
		sb.append("\r\n");
		sb.append("(3)流水交易的Excel文件中某行某个单元格有空缺，该行数据就会被忽略掉，不会出现在结果中，当然软件也会提示。\r\n");
		sb.append("   要注意的是，当Excel中某的行确实没数据，软件会报出警告，是没有问题的，\r\n");
		sb.append("   因为在操作Excel时使用了delete去删除数据,所以要确保的是有数据的但出现了空单元格那些行，\r\n");
		sb.append("   以便补全数据，重新生成正确结果。\r\n");
		sb.append("\r\n");
		sb.append("(4)流水交易一次性处理数据总量现在测试到50W条左右，这样每张图片按50条数据记载，会生成近1W张图片,\r\n");
		sb.append("   时间可能会耗很久，而且每张图片有630K左右，这样生成的结果多达6G，所以不要一次性数据量太大太大,\r\n");
		sb.append("   如果数据量还有更大，软件会提示：数据量太大，jvm内存溢出。");
		return sb.toString();
	}
	
	public static String lsexl(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)流水签章信息Excel文件要按以下模板，列与列之间不可调换顺序。\r\n");
		sb.append("   Excel表头模板:\r\n");
		sb.append("   _______________________________________________________________________\r\n");
		sb.append("   | 商户编号  | 营业执照号  | 签章名称  | 签章位置  | 签章角度  | 签章占比  |\r\n");
		sb.append("\r\n");
		sb.append("(2)在读取Excel文件时,软件除了不检查“营业执照号”,其他均会检查\r\n");
		sb.append("   a.商户编号、签章名称不可为空\r\n");
		sb.append("   b.签章位置取值1-20\r\n");
		sb.append("   c.签章角度取值0-360\r\n");
		sb.append("   d.签章占比取值0%-100%\r\n");
		sb.append("\r\n");
		sb.append("(3)流水签章信息Excel文件，如果数据的某行某个单元格有错误，那么与该行对应的商户号流水就不会被执行生成图片。\r\n");
		sb.append("\r\n");
		sb.append("(4)一定确保每个流水交易Excel文件的最后一行数据中商户号与签章信息表里的商户号一致。");
		return sb.toString();
	}
	
	public static String rzlsysb(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)入账流水信息Excel文件要按以下模板，列与列之间不可调换顺序。\r\n");
		sb.append("   Excel表头模板:\r\n");
		sb.append("   __________________________________________________________________________\r\n");
		sb.append("   | 商户编号  | 商户名称  | 清算日期  | 清分金额  | 账户名称  | 账号  | 开户行 |\r\n");
		sb.append("\r\n");
		sb.append("(2)入账流水信息Excel文件，只检测数据是否为空。\r\n");
		sb.append("\r\n");
		sb.append("(3)入账流水的Excel文件中某行某个单元格有空缺，该行数据就会被忽略掉，不会出现在结果中，当然软件也会提示。\r\n");
		sb.append("   要注意的是，当Excel中某的行确实没数据，软件会报出警告，是没有问题的，\r\n");
		sb.append("   因为在操作Excel时使用了delete去删除数据,所以要确保的是有数据的但出现了空单元格那些行，\r\n");
		sb.append("   以便补全数据，重新生成正确结果。\r\n");
		sb.append("\r\n");
		sb.append("(4)入账流水一次性处理数据总量不能太大，否则软件会提示：数据量太大，jvm内存溢出。,\r\n");
		return sb.toString();
	}
	
	public static String rzlsexl(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)入账流水签章信息Excel文件要按以下模板，列与列之间不可调换顺序。\r\n");
		sb.append("   Excel表头模板:\r\n");
		sb.append("   _______________________________________________________________________\r\n");
		sb.append("   | 商户编号  | 营业执照号  | 签章名称  | 签章位置  | 签章角度  | 签章占比  |\r\n");
		sb.append("\r\n");
		sb.append("(2)在读取Excel文件时,软件除了不检查“营业执照号”,其他均会检查\r\n");
		sb.append("   a.商户编号、签章名称不可为空\r\n");
		sb.append("   b.签章位置取值1-20\r\n");
		sb.append("   c.签章角度取值0-360\r\n");
		sb.append("   d.签章占比取值0%-100%\r\n");
		sb.append("\r\n");
		sb.append("(3)流水签章信息Excel文件，如果数据的某行某个单元格有错误，那么与该行对应的商户号流水就不会被执行生成图片。\r\n");
		sb.append("\r\n");
		sb.append("(4)一定确保每个入账流水Excel文件的最后一行数据中商户号与签章信息表里的商户号一致。");
		return sb.toString();
	}
	
	public static String oterhdesc(){
		StringBuilder sb = new StringBuilder();
		sb.append("(1)软件目前解压只支持zip和rar4版本。zip支持有密码的zip解压，rar不支持。\r\n");
		sb.append("\r\n");
		sb.append("(2)生成的结果最好不要在结果目录下解压。\r\n");
		sb.append("\r\n");
		sb.append("(3)签章占比大小，推荐60%-70%，也可以自己去测试判断。\r\n");
		sb.append("(注:最好可以查验一下结果)\r\n");
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(qgdysb());;
	}
	
}
