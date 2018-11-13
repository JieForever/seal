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
	 * 目前只实现了rar4格式及以下,且不支持有密码解压
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean doExtract(String sourcePath, String targetPath,String password) {
		
		String fileName = sourcePath.substring(sourcePath.lastIndexOf("\\")+1);
		
		Archive archive = null; 
		try{
			archive = new  Archive(new File(sourcePath));
			if(archive == null){
				OtherUtil.say("【"+fileName+"】:该文件没有找到");
				throw new FileNotFoundException("【"+fileName+"】:该文件没有找到");
			}
			/**
			 * junrar包可能有问题
			 */
            List<FileHeader> files =  archive.getFileHeaders();
            int tempsize = files.size();
            if(tempsize == 0){
            	OtherUtil.say("请确认rar压缩包版本在 rar4 或以下");
            	throw new Exception("请确认rar压缩包版本在 rar4 或以下");
            }else{
                 for(FileHeader fh : files) {
                 	if(fh.isEncrypted())
                 	{
                 		throw new Exception(sourcePath + ":该文件被加密");
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
                 OtherUtil.say("【"+ fileName + "】:解压成功");
                 return true;
            }
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("【"+ fileName + "】:解压失败");
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
//		new RarComAndExt().doExtract("C:\\Users\\Lenovo\\Desktop\\测试\\lsjy\\新建文件夹.rar", "E:\\temp1", null);
//	}
}
