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
        	//���ñ����ʽ
        	zipFile2.setFileNameCharset("GBK");
        	if (!zipFile2.isValidZipFile()){
        		OtherUtil.say("��"+ fileName +"���ļ����Ϸ��򲻴���");
        		throw new ZipException("�ļ����Ϸ��򲻴���");
        	}
        	//����Ƿ���Ҫ����
        	checkEncrypted(zipFile2);
        	@SuppressWarnings("unchecked")
        	List<FileHeader> fileHeaderList = zipFile2.getFileHeaders();  
        	for (int i = 0; i < fileHeaderList.size(); i++) {  
        		FileHeader fileHeader = fileHeaderList.get(i);  
        		zipFile2.extractFile(fileHeader,targetPath);  
        	}  
        	long endTime = System.currentTimeMillis();
        	OtherUtil.say("��ѹ��"+fileName+"���ļ��ɹ�����ʱ��" + (endTime - startTime) + "ms");
        	return true;
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  OtherUtil.say("��ѹ��"+fileName+"���ļ�ʧ�ܣ�");
	      }
		return false;
	}
	
	//�������
    private static void checkEncrypted(ZipFile zip) throws ZipException {
    	if (zip.isEncrypted()) {
    		OtherUtil.say("�ļ�"+zip.getFile().getName()+"�����룡");
    		String pwd = JOptionPane.showInputDialog(null, "ѹ���ļ�������,����������:");
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
		    //Ϊ�˲���ԭ���ļ�����,��֤ÿ����������
		    if (file.exists()) {
		        file.delete();
		    }
		    ZipParameters para=new ZipParameters();
		    //����ѹ������ѹ������Ĭ��
		    para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	    	zip.addFolder(comTargetFolder, para);
	    	OtherUtil.say("����ļ�ѹ����ɣ�");
	    	return true;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return false;
	}
	
//	public static void main(String[] args) {
//		new ZipComAndExt().doExtract("C:\\Users\\Lenovo\\Desktop\\����\\lsjy\\�½��ļ���.zip", "e:\\",null);
//	}
}
