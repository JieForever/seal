package com.xy.utils.cae;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import com.xy.utils.OtherUtil;

public class RarComAndExt implements CompressAndExtractFactory{
	
	public static Logger logger = Logger.getLogger(RarComAndExt.class);
	/**
	 * Ŀǰֻʵ����rar4��ʽ������,�Ҳ�֧���������ѹ
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean doExtract(String sourcePath, String targetPath,String password) {
		
		String fileName = sourcePath.substring(sourcePath.lastIndexOf("\\")+1);
		
		Archive archive = null; 
		try{
			archive = new  Archive(new File(sourcePath));
			if(archive == null){
				OtherUtil.say("��"+fileName+"��:���ļ�û���ҵ�");
				throw new FileNotFoundException("��"+fileName+"��:���ļ�û���ҵ�");
			}
			/**
			 * junrar������������
			 */
            List<FileHeader> files =  archive.getFileHeaders();
            int tempsize = files.size();
            if(tempsize == 0){
            	OtherUtil.say("��ȷ��rarѹ�����汾�� rar4 ������");
            	throw new Exception("��ȷ��rarѹ�����汾�� rar4 ������");
            }else{
                 for(FileHeader fh : files) {
                 	if(fh.isEncrypted())
                 	{
                 		throw new Exception(sourcePath + ":���ļ�������");
                 	}   
                    String fn = fh.getFileNameW(); 
                    if(fn != null && fn.trim().length() > 0){
                    	String saveFileName = targetPath+"\\"+fn;
                    	File saveFile = new File(saveFileName);
                    	if(saveFile.isDirectory()){
                    		continue;
                    	}
                    	File parent =  saveFile.getParentFile();  
                    	if(!parent.exists()){  
                    		parent.mkdirs();  
                    	}  
                    	if(!saveFile.exists()){  
                    		saveFile.createNewFile();  
                    	}  
                    	FileOutputStream fos = new FileOutputStream(saveFile);  
                    	try {   
                    		archive.extractFile(fh, fos);   
                    		fos.flush();  
                    		fos.close();  
                    	} catch (RarException e){
                    		e.printStackTrace();
                    	}finally{
                    		if(fos != null){
                    			fos.close();
                    		}
                    	}  
                    }  
                 }
                 OtherUtil.say("��"+ fileName + "��:��ѹ�ɹ�");
                 return true;
            }
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("��"+ fileName + "��:��ѹʧ��");
		}finally{
			if(archive != null){
				try{
					archive.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean doCompress(String comTargetFolder, String resultFilePath) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	public static void main(String[] args) {
//		new RarComAndExt().doExtract("C:\\Users\\Lenovo\\Desktop\\����\\lsjy\\�½��ļ���.rar", "E:\\temp1", null);
//	}
}
