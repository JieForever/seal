package com.xy.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 操作properties文件
 * @author Lenovo
 *
 */
public class OperateProperFile {
	
	private Logger logger =  Logger.getLogger(OperateProperFile.class);
	
	private Properties pro;
	private String path;
	
	public OperateProperFile(String path){
		this.path = path;
		this.pro = new Properties();
	}
	
	/**
	 * new ConfigService 后调用当前方法才能加载到properties
	 * @return
	 */
	public OperateProperFile read(){
		InputStream is = null;
		try{
			//is = new  FileInputStream(path);
			//is = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path));
			//is = new InputStreamReader(this.getClass().getResourceAsStream(path));
			String rootPath = System.getProperty("user.dir").replace("\\", "/");
			
			is = new FileInputStream(rootPath +"/"+ path);
			pro.load(is);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}
	
	/**
	 * 返回 properties 对象
	 * @return
	 */
	public  Properties getPro(){
		return this.pro;
	}
	
	/**
	 * 更新properties中的值
	 * @param key
	 * @param value
	 * @return 0成功，-1修改失败，1没有找到与key相同数据, -2参数key 为空
	 */
	public int update(String key, String value){
		if(key == null)
			return -2;
		int result = 1;
		OutputStream out = null;
		String[] keys = getKeys();
		for (String str : keys) {
			if(key.equals(str))
			{
				pro.setProperty(key, value);
				try{
					out = new FileOutputStream(path,false);
					pro.store(out, null);
					out.flush();
					result = 0;
				}catch(Exception e){
					result = -1;
					logger.error(e.getMessage());
					e.printStackTrace();
				}finally{
					try{
						if(out != null){
							out.close();
						}
					}catch(Exception e ){
						e.printStackTrace();
					}
				}
				break;
			}
		}
		return result;
	}
	
	/**
	 * 向properties插入数据
	 * key 有相同的则会更新value
	 * 若key不存在则新增
	 * @param key
	 * @param value
	 * @return 返回上次的value值
	 */
//	public Object insert(String key, String value) {
//		Object obj = null;
//		OutputStream  os = null;
//		try{
//			obj = pro.get(key);
//			pro.setProperty(key, value);
//			os = new FileOutputStream(new File(path));
//			
//			pro.store(os, null);
//			os.flush();
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}finally{
//			try{
//				if(os != null){
//					os.close();
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return obj;
//	}
	
	/**
	 * 得到properties中的所有key
	 * @return
	 */
	public String[] getKeys(){
		int size = pro.size();
		String[] s = new String[size];
		Set<Object> set = pro.keySet();
		Iterator<Object> it = set.iterator();
		int t = 0;
		while(it.hasNext()){
			s[t] = it.next().toString().trim();
			t++;
		}
		return s; 
	}
	
//	public static void main(String[] args) {
//		String[] s= new OperateProperFile("/model-config.properties").read().getKeys();
//		System.out.println(Arrays.toString(s));
//	}


}