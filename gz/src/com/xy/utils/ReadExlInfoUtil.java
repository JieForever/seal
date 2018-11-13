package com.xy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.xy.bean.LsjyExlInfo;
import com.xy.bean.LsjyQzExlInfo;
import com.xy.bean.QgdExlInfo;
import com.xy.bean.RzlsExlInfo;
import com.xy.bean.RzlsQzExlInfo;
import com.xy.enumclass.ImgTypeEnum;
import com.xy.exception.ExlDataException;

/**
 * 读取Excel工具类
 * @author Lenovo
 *
 */
public class ReadExlInfoUtil {
	
	private static Logger log = Logger.getLogger(ReadExlInfoUtil.class);
	
	//签购单签章位置正则
	public static final String REGEX_QZWZ1 = "^[1-9]{1}$";
	public static final String REGEX_QZWZ2 = "^[1-6]{1}$";
	public static final String REGEX_QZWZ3 = "^[1-3]{1}$";
	/**
	 * 读取签购单的Excel
	 * @param exlPath
	 * @param maxRow
	 * @return
	 */
	public static List<QgdExlInfo> readQgdExl(String exlPath, int maxRow){
		OtherUtil.say("开始解析excel文件,请稍等...");
		List<QgdExlInfo> list = new ArrayList<QgdExlInfo>();
		try{ 
			Workbook workbook = WorkbookFactory.create(new FileInputStream(exlPath));
			int numCount = 0;
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int oneSheetRowNum = sheet.getPhysicalNumberOfRows();
				//读取每个sheet的中的数据
				for(int rowNum = 0; rowNum < oneSheetRowNum; rowNum++)
				{
					if(numCount > maxRow)
						break;
					numCount++;
					
					Row row = sheet.getRow(rowNum);
					if(row == null) continue;
					
					if(rowNum > 0)
					{
						try{
							checkQgdData(row,rowNum+1);
						}catch(Exception e){
							continue;
						}
					}
					//创建签购单实例
					QgdExlInfo qgd = new QgdExlInfo();
					qgd.setShid(cellValueOper2(row.getCell(0)));
					qgd.setShmc(cellValueOper2(row.getCell(1)));
					qgd.setQzmc(cellValueOper2(row.getCell(2)));
					qgd.setYyzzh(cellValueOper2(row.getCell(3)));
					qgd.setTplx(cellValueOper2(row.getCell(4)));
					
					String[] wz = new String[3];
					wz[0] = cellValueOper2(row.getCell(5));
					wz[1] = cellValueOper2(row.getCell(6));
					wz[2] = cellValueOper2(row.getCell(7));
					qgd.setQzwz(wz);
					
					String[] jd = new String[3];
					jd[0] = cellValueOper2(row.getCell(8));
					jd[1] = cellValueOper2(row.getCell(9));
					jd[2] = cellValueOper2(row.getCell(10));
					qgd.setQzjd(jd);
					
					qgd.setQzzb(cellValueOper2(row.getCell(11)));
					list.add(qgd);
				}
				if(numCount > maxRow)
					OtherUtil.say("【警告】:Excel文件数据行数超过处理上限("+ maxRow+"),余下的不读取,不处理");
					break;
			}
			OtherUtil.say("解析Excel文件成功");
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("解析Excel文件失败");
		}
		return list;
	}
	
	/**
	 * 读取流水交易签章的信息
	 * @param exlPath
	 * @return
	 */
	public static Map<String,LsjyQzExlInfo> readLsjyQzExl(String exlPath){
		Map<String,LsjyQzExlInfo> map = new HashMap<String,LsjyQzExlInfo>();
		try{
			File file = new File(exlPath);
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0 ; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);   
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				//循环row, j = 1 略去表头
				for(int j = 1; j < rowNum; j++)
				{
					Row row = sheet.getRow(j);
					if(row == null)
						continue;
					boolean isIllegal = false;
					String fileName = file.getName();
					//商户号
					if("".equals(cellValueOper2(row.getCell(0))))
					{
						isIllegal = true;
						OtherUtil.say("【错误】:"+file.getName()+" 第 "+ (j+1) +" 行 '商户号'数据为空");
						log.error("【错误】:"+file.getName()+" 第 "+ (j+1) +" 行 '商户号'数据为空");
					}
					
					//签章名称
					if("".equals(cellValueOper2(row.getCell(2))))
					{
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章名称'数据为空");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章名称'数据为空");
					}
					
					//签章位置
					try{
						int wz = Integer.valueOf(cellValueOper2(row.getCell(3)));
						if(wz < 1 || wz > 20)
						{
			        		throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
			        	}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
					}
					
		        	//签章角度
					try{
						double jd = Double.valueOf(cellValueOper2(row.getCell(4)));
						if(jd < 0 || jd >360)
						{
							throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
					}
					
				    //签章占比
					try{
						double zb = Double.valueOf(cellValueOper2(row.getCell(5)));
						if(zb < 0.0 || zb > 1.0)
						{
							throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
					}
					
					if(!isIllegal)
					{
						LsjyQzExlInfo qz = new LsjyQzExlInfo();
						qz.setShid(cellValueOper2(row.getCell(0)));
						qz.setYyzz(cellValueOper2(row.getCell(1)));
						qz.setQzmc(cellValueOper2(row.getCell(2)));
						qz.setQzwz(cellValueOper2(row.getCell(3)));
				        qz.setQzjd(cellValueOper2(row.getCell(4)));
				        qz.setQzzb(cellValueOper2(row.getCell(5)));
				        map.put(cellValueOper2(row.getCell(0)),qz);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 读取流水交易Excel文件
	 * @param exlPath
	 * @return
	 */
	public static List<LsjyExlInfo> readLsjyExl(String exlPath) throws FileNotFoundException{
		File file = new File(exlPath);
		return readLsjyExl(file);
	}
	/**
	 * 读取流水交易Excel文件
	 * @param file
	 * @return
	 */
	public static List<LsjyExlInfo> readLsjyExl(File file)throws FileNotFoundException{
		List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				//读取每个sheet的中的数据
				for(int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++)
				{
					Row row = sheet.getRow(rowNum);
					if(row == null) 
						continue;				
					try{
						//创建lsjy实例
						LsjyExlInfo lsjy = new LsjyExlInfo();
						lsjy.setShid(cellValueOper(row.getCell(0)));
						lsjy.setShmc(cellValueOper(row.getCell(1)));
						lsjy.setKh(cellValueOper(row.getCell(2)));
						lsjy.setRqsj(cellValueOper(row.getCell(3)));
						lsjy.setJyje(cellValueOper(row.getCell(4)));
						list.add(lsjy);
					}catch(ExlDataException e){
						OtherUtil.say("【警告】:"+file.getName()+"第   "+(rowNum+1)+" 行 前五列数据存在空");
						log.error("【警告】:"+file.getName()+"第   "+(rowNum+1)+" 行 前五列数据存在空");
						continue;
					}
				}
			}
			OtherUtil.say("解析【"+ file.getName()+"】文件成功");
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("解析【"+ file.getName()+"】文件失败");
		}
		return list;
	}
	
	/**
	 * 读取流水交易Excel文件
	 * @param exlPath
	 * @param size
	 * @return
	 */
	public static List<List<LsjyExlInfo>> readLsjyExl(File file, int size){
		List<List<LsjyExlInfo>> result = new ArrayList<List<LsjyExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//读取每个sheet的中的数据
				for(int j = 0; j < imgPageNum; j++)
				{
					List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{
							//创建lsjy实例
							LsjyExlInfo lsjy = new LsjyExlInfo();
							lsjy.setShid(cellValueOper(row.getCell(0)));
							lsjy.setShmc(cellValueOper(row.getCell(1)));
							lsjy.setKh(cellValueOper(row.getCell(2)));
							lsjy.setRqsj(cellValueOper(row.getCell(3)));
							lsjy.setJyje(cellValueOper(row.getCell(4)));
							list.add(lsjy);
						}catch(ExlDataException e){
							OtherUtil.say("【警告】:"+file.getName()+" 的"+sheet.getSheetName()+"里第 "+(j+1)+" 行 前五列数据存在空");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("解析【"+ file.getName()+"】文件成功");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("【错误】: 数据量太大,JVM内存溢出! 请减少数据重试。");
			e.printStackTrace();
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("解析【"+ file.getName()+"】文件失败");
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static Map<Integer,Object> readLsjyExl2(File file, int size){
		OtherUtil.say("开始解析excel文件,请稍等...");
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		List<List<LsjyExlInfo>> result = new ArrayList<List<LsjyExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//读取每个sheet的中的数据
				for(int j = 0; j < imgPageNum; j++)
				{
					List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{
							//创建lsjy实例
							LsjyExlInfo lsjy = new LsjyExlInfo();
							lsjy.setShid(cellValueOper(row.getCell(0)));
							lsjy.setShmc(cellValueOper(row.getCell(1)));
							lsjy.setKh(cellValueOper(row.getCell(2)));
							lsjy.setRqsj(cellValueOper(row.getCell(3)));
							lsjy.setJyje(cellValueOper(row.getCell(4)));
							list.add(lsjy);
							map.put(2, lsjy);
							
						}catch(ExlDataException e){
							OtherUtil.say("【警告】:"+file.getName()+"的"+sheet.getSheetName()+"里第 "+(j+1)+" 行前五列数据存在空");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("解析【"+ file.getName()+"】文件成功");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("【错误】: 数据量太大,JVM内存溢出! 请减少数据重试。");
			e.printStackTrace();
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		map.put(1, result);
		return map;
	}
	
	/**
	 * 读取入账流水的签章信息exl
	 * @param exlPath
	 * @return
	 */
	public static Map<String, RzlsQzExlInfo> readRzlsQzExl(String exlPath) {
		Map<String, RzlsQzExlInfo> map = new HashMap<String, RzlsQzExlInfo>();
		try{
			File file = new File(exlPath);
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0 ; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);   
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				//循环row, j = 1 略去表头
				for(int j = 1; j < rowNum; j++)
				{
					Row row = sheet.getRow(j);
					if(row == null)
						continue;
					boolean isIllegal = false;
					String fileName = file.getName();
					//商户号
					if("".equals(cellValueOper2(row.getCell(0))))
					{
						isIllegal = true;
						OtherUtil.say("【错误】:"+file.getName()+" 第 "+ (j+1) +" 行 '商户号'数据为空");
						log.error("【错误】:"+file.getName()+" 第 "+ (j+1) +" 行 '商户号'数据为空");
					}
					
					//签章名称
					if("".equals(cellValueOper2(row.getCell(2))))
					{
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章名称'数据为空");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章名称'数据为空");
					}
					
					//签章位置
					try{
						int wz = Integer.valueOf(cellValueOper2(row.getCell(3)));
						if(wz < 1 || wz > 20)
						{
			        		throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
			        	}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章位置'数据错误");
					}
					
		        	//签章角度
					try{
						double jd = Double.valueOf(cellValueOper2(row.getCell(4)));
						if(jd < 0 || jd >360)
						{
							throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章角度'数据错误");
					}
					
				    //签章占比
					try{
						double zb = Double.valueOf(cellValueOper2(row.getCell(5)));
						if(zb < 0.0 || zb > 1.0)
						{
							throw new ExlDataException("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
						log.error("【错误】:"+fileName+" 第 "+ (j+1) +" 行 '签章占比'数据错误");
					}
					
					if(!isIllegal)
					{
						RzlsQzExlInfo qz = new RzlsQzExlInfo();
						qz.setShid(cellValueOper2(row.getCell(0)));
						qz.setYyzz(cellValueOper2(row.getCell(1)));
						qz.setQzmc(cellValueOper2(row.getCell(2)));
						qz.setQzwz(cellValueOper2(row.getCell(3)));
				        qz.setQzjd(cellValueOper2(row.getCell(4)));
				        qz.setQzzb(cellValueOper2(row.getCell(5)));
				        map.put(cellValueOper2(row.getCell(0)),qz);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * 读取入账流水exl
	 * @param file
	 * @param size
	 * @return
	 */
	public static Map<Integer, Object> readRzlsExl(File file, int size){
		OtherUtil.say("开始解析excel文件,请稍等...");
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<List<RzlsExlInfo>> result = new ArrayList<List<RzlsExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//循环sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//读取每个sheet的中的数据
				for(int j = 0; j < imgPageNum; j++)   
				{  
					List<RzlsExlInfo> list = new ArrayList<RzlsExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{  
							//创建入账流水实例
							RzlsExlInfo rzls = new RzlsExlInfo();
							rzls.setShid(cellValueOper(row.getCell(0)));
							rzls.setShmc(cellValueOper(row.getCell(1)));
							rzls.setQsrq(cellValueOper3(row.getCell(2)));
							rzls.setQfje(cellValueOper(row.getCell(3)));
							rzls.setZhmc(cellValueOper(row.getCell(4)));
							rzls.setZh(cellValueOper(row.getCell(5)));
							rzls.setKhh(cellValueOper(row.getCell(6))); 
							
							list.add(rzls);
							map.put(2, rzls);
						}catch(ExlDataException e){
							//OtherUtil.say("【警告】:"+file.getName()+"的"+sheet.getSheetName()+"里第 "+(j+1)+" 行前7列数据存在空");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("解析【"+ file.getName()+"】文件成功");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("【错误】: 数据量太大,JVM内存溢出! 请减少数据重试。");
			e.printStackTrace();
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		map.put(1, result);
		return map;
	}
	

	/*
	 * 校验没个单元格
	 */
	private static String cellValueOper(Cell cell)throws ExlDataException{
		if(cell == null)
			throw new ExlDataException("数据为空");
		cell.setCellType(CellType.STRING);
		String r = cell.getStringCellValue().trim();
		if("".equals(r))
			throw new ExlDataException("数据为空");
		return r;
	}
	
	private static String cellValueOper2(Cell cell){
		if(cell == null)
			return "";
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue().trim();
	}
	
	private static String cellValueOper3(Cell cell){
		String result = "";
		if(cell == null)
			return result;
		
		if(0 == cell.getCellType()){
			 short format = cell.getCellStyle().getDataFormat();  
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
			    double value = cell.getNumericCellValue();  
			    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
			    result = sdf.format(date);  
		}else{
			cell.setCellType(CellType.STRING);
			result = cell.getStringCellValue().trim();
		}
	return	result;
	}
	
	/*
	 * 校验签购单数据
	 */
	private static void checkQgdData(Row row,int n) throws ExlDataException{
		if("".equals(cellValueOper2(row.getCell(0)))){
			OtherUtil.say("【错误】:excel第 "+ n +" 行'商户号'为空");
			throw new ExlDataException("【错误】：第   "+ n +" 行'商户号'为空");
		}
		if("".equals(cellValueOper2(row.getCell(1)))){
			OtherUtil.say("【错误】:excel第 "+ n +" 行'商户名称'为空");
			throw new ExlDataException("【错误】：第   "+ n +" 行'商户名称'为空");
		}
		if("".equals(cellValueOper2(row.getCell(2)))){
			OtherUtil.say("【错误】:excel第 "+ n +" 行'签章名称'为空");
			throw new ExlDataException("【错误】：第   "+ n +" 行'签章名称'为空");
		}
		
		Integer code = ImgTypeEnum.getCodeByType(cellValueOper2(row.getCell(4)));
		if(null == code){
			OtherUtil.say("【错误】:excel第 "+ n +" 行'图片版本'数据错误 -"+cellValueOper2(row.getCell(4)));
			throw new ExlDataException("【错误】：第   "+ n +" 行'图片版本'数据错误");
		}
		if(3 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ3);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置1'数据错误");
				throw new ExlDataException("【错误】：第   "+ n +" 行'签章位置1'数据错误");
			}
			
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("【错误】:第   "+ n +" 行'签章角度1'数据错误");
				}
			}catch(Exception e){
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章角度'数据有错误");
				throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度'数据有错误");
			}
		}
		else if(2 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ2);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置1'数据错误");
				throw new ExlDataException("【错误】：第   "+ n +" 行'签章位置1'数据错误");
			}
			
			f =  cellValueOper2(row.getCell(6)).matches(REGEX_QZWZ2);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置2'数据错误");
				throw new ExlDataException("【错误】：第   "+ n +" 行'签章位置2'数据错误");
			}
			
			if(cellValueOper2(row.getCell(5)).equals(cellValueOper2(row.getCell(6))))
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置1'与'签章位置2'数据相同");
				throw new ExlDataException("【错误】：第 "+ n +" 行'签章位置1'与'签章位置2'数据相同");
			}
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				double jd2 = Double.valueOf(cellValueOper2(row.getCell(9)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("【错误】:第   "+ n +" 行'签章角度1'数据错误");
				}
				
				if(jd2<0 || jd2>360)
				{	
					throw new ExlDataException("【错误】:第   "+ n +" 行'签章角度2'数据错误");
				}
			}catch(Exception e){
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章角度'数据有错误");
				throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度'数据有错误");
			}
		}else if(1 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置1'数据错误");
				throw new ExlDataException("【错误】:第 "+ n +" 行'签章位置1'数据错误");
			}
			
			f =  cellValueOper2(row.getCell(6)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置2'数据错误");
				throw new ExlDataException("【错误】:第 "+ n +" 行'签章位置2'数据错误");
			}
			
			f =  cellValueOper2(row.getCell(7)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置3'数据错误");
				throw new ExlDataException("【错误】：第 "+ n +" 行'签章位置3'数据错误");
			}
			
			checkQZWZEquals(new String[]{cellValueOper2(row.getCell(5)),cellValueOper2(row.getCell(6)),cellValueOper2(row.getCell(7))},n);
			
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				double jd2 = Double.valueOf(cellValueOper2(row.getCell(9)));
				double jd3 = Double.valueOf(cellValueOper2(row.getCell(10)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度1'数据错误");
				}
				if(jd2<0 || jd2>360)
				{
					throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度2'数据错误");
				}
				if(jd3<0 || jd3>360)
				{
					throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度3'数据错误");
				}
			}catch(Exception e){
				OtherUtil.say("【错误】:excel第 "+ n +" 行'签章角度'数据有错误");
				throw new ExlDataException("【错误】：第 "+ n +" 行'签章角度'数据有错误");
			}
		}
		
		try{
			double zb = Double.valueOf(cellValueOper2(row.getCell(11))); 
			if(zb > 1.0 || zb < 0)
			{
				throw new  ExlDataException("【错误】：第 "+ n +" 行'签章占比'数据错误");
			}
		}catch(Exception e){
			OtherUtil.say("【错误】:excel第 "+ n +" 行'签章占比'数据错误");
			throw new  ExlDataException("【错误】：第 "+ n +" 行'签章占比'数据错误");
		}
	}
	
	//校验签章位置是否重复
	private static void checkQZWZEquals(String[] s, int n){
		int len = s.length;
		for(int i = 0;i<len;i++)
		{
			for(int j = i + 1; j<len;j++)
			{
				if(s[i].equals(s[j]))
				{
					OtherUtil.say("【错误】:excel第 "+ n +" 行'签章位置"+(i+1)+"'与'签章位置"+(j+1)+"'数据相同");
					throw new ExlDataException("【错误】：第 "+ n +" 行'签章位置"+(i+1)+"'与'签章位置"+(j+1)+"'数据相同");
				}
			}
		}
	}
	

	/*public static void main(String[] args)throws Exception{
		String s = "C:\\Users\\Lenovo\\Desktop\\入账流水.xls";
		Map m = readRzlsExl(new File(s),50);
	
	//	String s = "C:\\Users\\Lenovo\\Desktop\\s\\cs\\857210755416289辽宁省锦州市凌海市中旺加油站.xls";
	//	Map m = readLsjyExl2(new File(s),50);
	//	 BufferedImage dataImg = DrawLsjy.createLsjyImg(list, true);
	//		ImgOperateUtil.printImg(dataImg,"e://cs.jpg");
	}*/

	
//	
//	
//	public static void main(String[] args) {
//		try {
//			List<QgdExlInfo> l = ReadExlInfoUtil.readQgdExl("C:\\Users\\Lenovo\\Desktop\\cs\\签购单印章表格模板-测试.xlsx",100);
//			for(QgdExlInfo lsjy:l){
//				System.out.println(lsjy);
//			}
//			System.out.println(l.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
