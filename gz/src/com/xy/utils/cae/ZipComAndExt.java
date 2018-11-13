package com.xy.utils.cae;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.xy.utils.OtherUtil;

public class ZipComAndExt implements CompressAndExtractFactory{
	
	
	@Override
	public boolean doExtract(String sourcePath, String targetPath, String passwold) {
		String fileName = sourcePath.substring(sourcePath.lastIndexOf("\\")+1);
		
		try{
			long startTime = System.currentTimeMillis();
        	ZipFile zipFile2 = new ZipFile(sourcePath);  
        	//设置编码格式
        	zipFile2.setFileNameCharset("GBK");
        	if (!zipFile2.isValidZipFile()){
        		OtherUtil.say("【"+ fileName +"】文件不合法或不存在");
        		throw new ZipException("文件不合法或不存在");
        	}
        	//检查是否需要密码
        	checkEncrypted(zipFile2);
        	@SuppressWarnings("unchecked")
        	List<FileHeader> fileHeaderList = zipFile2.getFileHeaders();  
        	for (int i = 0; i < fileHeaderList.size(); i++) {  
        		FileHeader fileHeader = fileHeaderList.get(i);  
        		zipFile2.extractFile(fileHeader,targetPath);  
        	}  
        	long endTime = System.currentTimeMillis();
        	OtherUtil.say("解压【"+fileName+"】文件成功！耗时：" + (endTime - startTime) + "ms");
        	return true;
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  OtherUtil.say("解压【"+fileName+"】文件失败！");
	      }
		return false;
	}
	
	//检测密码
    private static void checkEncrypted(ZipFile zip) throws ZipException {
    	if (zip.isEncrypted()) {
    		OtherUtil.say("文件"+zip.getFile().getName()+"有密码！");
    		String pwd = JOptionPane.showInputDialog(null, "压缩文件有密码,请输入密码:");
    		zip.setPassword(pwd.trim());
    	}
    	
    }

    
	@Override
	public boolean doCompress(String comTargetFolder, String resultFilePath) {
		// TODO Auto-generated method stub
	    ZipFile zip = null;
		try {
			zip = new ZipFile(resultFilePath);
		    File file=zip.getFile();
		    if (!file.getParentFile().exists()) {
		        file.getParentFile().mkdirs();
		    }
		    //为了不被原有文件干扰,保证每次重新生成
		    if (file.exists()) {
		        file.delete();
		    }
		    ZipParameters para=new ZipParameters();
		    //设置压缩级别，压缩方法默认
		    para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	    	zip.addFolder(comTargetFolder, para);
	    	OtherUtil.say("结果文件压缩完成！");
	    	return true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return false;
	}
	
//	public static void main(String[] args) {
//		new ZipComAndExt().doExtract("C:\\Users\\Lenovo\\Desktop\\测试\\lsjy\\新建文件夹.zip", "e:\\",null);
//	}
}
