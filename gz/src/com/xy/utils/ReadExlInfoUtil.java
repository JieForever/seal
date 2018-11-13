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
 * ��ȡExcel������
 * @author Lenovo
 *
 */
public class ReadExlInfoUtil {
	
	private static Logger log = Logger.getLogger(ReadExlInfoUtil.class);
	
	//ǩ����ǩ��λ������
	public static final String REGEX_QZWZ1 = "^[1-9]{1}$";
	public static final String REGEX_QZWZ2 = "^[1-6]{1}$";
	public static final String REGEX_QZWZ3 = "^[1-3]{1}$";
	/**
	 * ��ȡǩ������Excel
	 * @param exlPath
	 * @param maxRow
	 * @return
	 */
	public static List<QgdExlInfo> readQgdExl(String exlPath, int maxRow){
		OtherUtil.say("��ʼ����excel�ļ�,���Ե�...");
		List<QgdExlInfo> list = new ArrayList<QgdExlInfo>();
		try{ 
			Workbook workbook = WorkbookFactory.create(new FileInputStream(exlPath));
			int numCount = 0;
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int oneSheetRowNum = sheet.getPhysicalNumberOfRows();
				//��ȡÿ��sheet���е�����
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
					//����ǩ����ʵ��
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
					OtherUtil.say("�����桿:Excel�ļ���������������������("+ maxRow+"),���µĲ���ȡ,������");
					break;
			}
			OtherUtil.say("����Excel�ļ��ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("����Excel�ļ�ʧ��");
		}
		return list;
	}
	
	/**
	 * ��ȡ��ˮ����ǩ�µ���Ϣ
	 * @param exlPath
	 * @return
	 */
	public static Map<String,LsjyQzExlInfo> readLsjyQzExl(String exlPath){
		Map<String,LsjyQzExlInfo> map = new HashMap<String,LsjyQzExlInfo>();
		try{
			File file = new File(exlPath);
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0 ; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);   
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				//ѭ��row, j = 1 ��ȥ��ͷ
				for(int j = 1; j < rowNum; j++)
				{
					Row row = sheet.getRow(j);
					if(row == null)
						continue;
					boolean isIllegal = false;
					String fileName = file.getName();
					//�̻���
					if("".equals(cellValueOper2(row.getCell(0))))
					{
						isIllegal = true;
						OtherUtil.say("������:"+file.getName()+" �� "+ (j+1) +" �� '�̻���'����Ϊ��");
						log.error("������:"+file.getName()+" �� "+ (j+1) +" �� '�̻���'����Ϊ��");
					}
					
					//ǩ������
					if("".equals(cellValueOper2(row.getCell(2))))
					{
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ������'����Ϊ��");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ������'����Ϊ��");
					}
					
					//ǩ��λ��
					try{
						int wz = Integer.valueOf(cellValueOper2(row.getCell(3)));
						if(wz < 1 || wz > 20)
						{
			        		throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
			        	}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
					}
					
		        	//ǩ�½Ƕ�
					try{
						double jd = Double.valueOf(cellValueOper2(row.getCell(4)));
						if(jd < 0 || jd >360)
						{
							throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
					}
					
				    //ǩ��ռ��
					try{
						double zb = Double.valueOf(cellValueOper2(row.getCell(5)));
						if(zb < 0.0 || zb > 1.0)
						{
							throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
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
	 * ��ȡ��ˮ����Excel�ļ�
	 * @param exlPath
	 * @return
	 */
	public static List<LsjyExlInfo> readLsjyExl(String exlPath) throws FileNotFoundException{
		File file = new File(exlPath);
		return readLsjyExl(file);
	}
	/**
	 * ��ȡ��ˮ����Excel�ļ�
	 * @param file
	 * @return
	 */
	public static List<LsjyExlInfo> readLsjyExl(File file)throws FileNotFoundException{
		List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				//��ȡÿ��sheet���е�����
				for(int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++)
				{
					Row row = sheet.getRow(rowNum);
					if(row == null) 
						continue;				
					try{
						//����lsjyʵ��
						LsjyExlInfo lsjy = new LsjyExlInfo();
						lsjy.setShid(cellValueOper(row.getCell(0)));
						lsjy.setShmc(cellValueOper(row.getCell(1)));
						lsjy.setKh(cellValueOper(row.getCell(2)));
						lsjy.setRqsj(cellValueOper(row.getCell(3)));
						lsjy.setJyje(cellValueOper(row.getCell(4)));
						list.add(lsjy);
					}catch(ExlDataException e){
						OtherUtil.say("�����桿:"+file.getName()+"��   "+(rowNum+1)+" �� ǰ�������ݴ��ڿ�");
						log.error("�����桿:"+file.getName()+"��   "+(rowNum+1)+" �� ǰ�������ݴ��ڿ�");
						continue;
					}
				}
			}
			OtherUtil.say("������"+ file.getName()+"���ļ��ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("������"+ file.getName()+"���ļ�ʧ��");
		}
		return list;
	}
	
	/**
	 * ��ȡ��ˮ����Excel�ļ�
	 * @param exlPath
	 * @param size
	 * @return
	 */
	public static List<List<LsjyExlInfo>> readLsjyExl(File file, int size){
		List<List<LsjyExlInfo>> result = new ArrayList<List<LsjyExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//��ȡÿ��sheet���е�����
				for(int j = 0; j < imgPageNum; j++)
				{
					List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{
							//����lsjyʵ��
							LsjyExlInfo lsjy = new LsjyExlInfo();
							lsjy.setShid(cellValueOper(row.getCell(0)));
							lsjy.setShmc(cellValueOper(row.getCell(1)));
							lsjy.setKh(cellValueOper(row.getCell(2)));
							lsjy.setRqsj(cellValueOper(row.getCell(3)));
							lsjy.setJyje(cellValueOper(row.getCell(4)));
							list.add(lsjy);
						}catch(ExlDataException e){
							OtherUtil.say("�����桿:"+file.getName()+" ��"+sheet.getSheetName()+"��� "+(j+1)+" �� ǰ�������ݴ��ڿ�");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("������"+ file.getName()+"���ļ��ɹ�");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("������: ������̫��,JVM�ڴ����! ������������ԡ�");
			e.printStackTrace();
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			OtherUtil.say("������"+ file.getName()+"���ļ�ʧ��");
			log.error(e.getMessage());
		}
		return result;
	}
	
	public static Map<Integer,Object> readLsjyExl2(File file, int size){
		OtherUtil.say("��ʼ����excel�ļ�,���Ե�...");
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		List<List<LsjyExlInfo>> result = new ArrayList<List<LsjyExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//��ȡÿ��sheet���е�����
				for(int j = 0; j < imgPageNum; j++)
				{
					List<LsjyExlInfo> list = new ArrayList<LsjyExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{
							//����lsjyʵ��
							LsjyExlInfo lsjy = new LsjyExlInfo();
							lsjy.setShid(cellValueOper(row.getCell(0)));
							lsjy.setShmc(cellValueOper(row.getCell(1)));
							lsjy.setKh(cellValueOper(row.getCell(2)));
							lsjy.setRqsj(cellValueOper(row.getCell(3)));
							lsjy.setJyje(cellValueOper(row.getCell(4)));
							list.add(lsjy);
							map.put(2, lsjy);
							
						}catch(ExlDataException e){
							OtherUtil.say("�����桿:"+file.getName()+"��"+sheet.getSheetName()+"��� "+(j+1)+" ��ǰ�������ݴ��ڿ�");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("������"+ file.getName()+"���ļ��ɹ�");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("������: ������̫��,JVM�ڴ����! ������������ԡ�");
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
	 * ��ȡ������ˮ��ǩ����Ϣexl
	 * @param exlPath
	 * @return
	 */
	public static Map<String, RzlsQzExlInfo> readRzlsQzExl(String exlPath) {
		Map<String, RzlsQzExlInfo> map = new HashMap<String, RzlsQzExlInfo>();
		try{
			File file = new File(exlPath);
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0 ; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);   
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				//ѭ��row, j = 1 ��ȥ��ͷ
				for(int j = 1; j < rowNum; j++)
				{
					Row row = sheet.getRow(j);
					if(row == null)
						continue;
					boolean isIllegal = false;
					String fileName = file.getName();
					//�̻���
					if("".equals(cellValueOper2(row.getCell(0))))
					{
						isIllegal = true;
						OtherUtil.say("������:"+file.getName()+" �� "+ (j+1) +" �� '�̻���'����Ϊ��");
						log.error("������:"+file.getName()+" �� "+ (j+1) +" �� '�̻���'����Ϊ��");
					}
					
					//ǩ������
					if("".equals(cellValueOper2(row.getCell(2))))
					{
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ������'����Ϊ��");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ������'����Ϊ��");
					}
					
					//ǩ��λ��
					try{
						int wz = Integer.valueOf(cellValueOper2(row.getCell(3)));
						if(wz < 1 || wz > 20)
						{
			        		throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
			        	}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��λ��'���ݴ���");
					}
					
		        	//ǩ�½Ƕ�
					try{
						double jd = Double.valueOf(cellValueOper2(row.getCell(4)));
						if(jd < 0 || jd >360)
						{
							throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ�½Ƕ�'���ݴ���");
					}
					
				    //ǩ��ռ��
					try{
						double zb = Double.valueOf(cellValueOper2(row.getCell(5)));
						if(zb < 0.0 || zb > 1.0)
						{
							throw new ExlDataException("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
						}
					}catch(Exception e){
						isIllegal = true;
						OtherUtil.say("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
						log.error("������:"+fileName+" �� "+ (j+1) +" �� 'ǩ��ռ��'���ݴ���");
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
	 * ��ȡ������ˮexl
	 * @param file
	 * @param size
	 * @return
	 */
	public static Map<Integer, Object> readRzlsExl(File file, int size){
		OtherUtil.say("��ʼ����excel�ļ�,���Ե�...");
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<List<RzlsExlInfo>> result = new ArrayList<List<RzlsExlInfo>>();
		try{
			Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
			//ѭ��sheet
			int sheetNum = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetNum; i++)
			{
				Sheet sheet = workbook.getSheetAt(i);
				if(sheet == null)
					continue;
				int rowNum = sheet.getPhysicalNumberOfRows();
				int imgPageNum = (int)Math.ceil(rowNum/(size/1.0));
				//��ȡÿ��sheet���е�����
				for(int j = 0; j < imgPageNum; j++)   
				{  
					List<RzlsExlInfo> list = new ArrayList<RzlsExlInfo>();
					for(int t = 1; t <= size; t++)
					{
						Row row = sheet.getRow(t-1 + j*size);
						if(row == null) 
							continue;
						try{  
							//����������ˮʵ��
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
							//OtherUtil.say("�����桿:"+file.getName()+"��"+sheet.getSheetName()+"��� "+(j+1)+" ��ǰ7�����ݴ��ڿ�");
						}
					}
					if(list.size() > 0)
						result.add(list);
				}
			}
			OtherUtil.say("������"+ file.getName()+"���ļ��ɹ�");
		}catch(OutOfMemoryError e){
			result = null;
			OtherUtil.say("������: ������̫��,JVM�ڴ����! ������������ԡ�");
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
	 * У��û����Ԫ��
	 */
	private static String cellValueOper(Cell cell)throws ExlDataException{
		if(cell == null)
			throw new ExlDataException("����Ϊ��");
		cell.setCellType(CellType.STRING);
		String r = cell.getStringCellValue().trim();
		if("".equals(r))
			throw new ExlDataException("����Ϊ��");
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
	 * У��ǩ��������
	 */
	private static void checkQgdData(Row row,int n) throws ExlDataException{
		if("".equals(cellValueOper2(row.getCell(0)))){
			OtherUtil.say("������:excel�� "+ n +" ��'�̻���'Ϊ��");
			throw new ExlDataException("�����󡿣���   "+ n +" ��'�̻���'Ϊ��");
		}
		if("".equals(cellValueOper2(row.getCell(1)))){
			OtherUtil.say("������:excel�� "+ n +" ��'�̻�����'Ϊ��");
			throw new ExlDataException("�����󡿣���   "+ n +" ��'�̻�����'Ϊ��");
		}
		if("".equals(cellValueOper2(row.getCell(2)))){
			OtherUtil.say("������:excel�� "+ n +" ��'ǩ������'Ϊ��");
			throw new ExlDataException("�����󡿣���   "+ n +" ��'ǩ������'Ϊ��");
		}
		
		Integer code = ImgTypeEnum.getCodeByType(cellValueOper2(row.getCell(4)));
		if(null == code){
			OtherUtil.say("������:excel�� "+ n +" ��'ͼƬ�汾'���ݴ��� -"+cellValueOper2(row.getCell(4)));
			throw new ExlDataException("�����󡿣���   "+ n +" ��'ͼƬ�汾'���ݴ���");
		}
		if(3 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ3);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��1'���ݴ���");
				throw new ExlDataException("�����󡿣���   "+ n +" ��'ǩ��λ��1'���ݴ���");
			}
			
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("������:��   "+ n +" ��'ǩ�½Ƕ�1'���ݴ���");
				}
			}catch(Exception e){
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ�½Ƕ�'�����д���");
				throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�'�����д���");
			}
		}
		else if(2 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ2);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��1'���ݴ���");
				throw new ExlDataException("�����󡿣���   "+ n +" ��'ǩ��λ��1'���ݴ���");
			}
			
			f =  cellValueOper2(row.getCell(6)).matches(REGEX_QZWZ2);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��2'���ݴ���");
				throw new ExlDataException("�����󡿣���   "+ n +" ��'ǩ��λ��2'���ݴ���");
			}
			
			if(cellValueOper2(row.getCell(5)).equals(cellValueOper2(row.getCell(6))))
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��1'��'ǩ��λ��2'������ͬ");
				throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ��λ��1'��'ǩ��λ��2'������ͬ");
			}
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				double jd2 = Double.valueOf(cellValueOper2(row.getCell(9)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("������:��   "+ n +" ��'ǩ�½Ƕ�1'���ݴ���");
				}
				
				if(jd2<0 || jd2>360)
				{	
					throw new ExlDataException("������:��   "+ n +" ��'ǩ�½Ƕ�2'���ݴ���");
				}
			}catch(Exception e){
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ�½Ƕ�'�����д���");
				throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�'�����д���");
			}
		}else if(1 == code){
			boolean f = cellValueOper2(row.getCell(5)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��1'���ݴ���");
				throw new ExlDataException("������:�� "+ n +" ��'ǩ��λ��1'���ݴ���");
			}
			
			f =  cellValueOper2(row.getCell(6)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��2'���ݴ���");
				throw new ExlDataException("������:�� "+ n +" ��'ǩ��λ��2'���ݴ���");
			}
			
			f =  cellValueOper2(row.getCell(7)).matches(REGEX_QZWZ1);
			if(!f)
			{
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��3'���ݴ���");
				throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ��λ��3'���ݴ���");
			}
			
			checkQZWZEquals(new String[]{cellValueOper2(row.getCell(5)),cellValueOper2(row.getCell(6)),cellValueOper2(row.getCell(7))},n);
			
			try{
				double jd1 = Double.valueOf(cellValueOper2(row.getCell(8)));
				double jd2 = Double.valueOf(cellValueOper2(row.getCell(9)));
				double jd3 = Double.valueOf(cellValueOper2(row.getCell(10)));
				if(jd1<0 || jd1>360)
				{
					throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�1'���ݴ���");
				}
				if(jd2<0 || jd2>360)
				{
					throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�2'���ݴ���");
				}
				if(jd3<0 || jd3>360)
				{
					throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�3'���ݴ���");
				}
			}catch(Exception e){
				OtherUtil.say("������:excel�� "+ n +" ��'ǩ�½Ƕ�'�����д���");
				throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ�½Ƕ�'�����д���");
			}
		}
		
		try{
			double zb = Double.valueOf(cellValueOper2(row.getCell(11))); 
			if(zb > 1.0 || zb < 0)
			{
				throw new  ExlDataException("�����󡿣��� "+ n +" ��'ǩ��ռ��'���ݴ���");
			}
		}catch(Exception e){
			OtherUtil.say("������:excel�� "+ n +" ��'ǩ��ռ��'���ݴ���");
			throw new  ExlDataException("�����󡿣��� "+ n +" ��'ǩ��ռ��'���ݴ���");
		}
	}
	
	//У��ǩ��λ���Ƿ��ظ�
	private static void checkQZWZEquals(String[] s, int n){
		int len = s.length;
		for(int i = 0;i<len;i++)
		{
			for(int j = i + 1; j<len;j++)
			{
				if(s[i].equals(s[j]))
				{
					OtherUtil.say("������:excel�� "+ n +" ��'ǩ��λ��"+(i+1)+"'��'ǩ��λ��"+(j+1)+"'������ͬ");
					throw new ExlDataException("�����󡿣��� "+ n +" ��'ǩ��λ��"+(i+1)+"'��'ǩ��λ��"+(j+1)+"'������ͬ");
				}
			}
		}
	}
	

	/*public static void main(String[] args)throws Exception{
		String s = "C:\\Users\\Lenovo\\Desktop\\������ˮ.xls";
		Map m = readRzlsExl(new File(s),50);
	
	//	String s = "C:\\Users\\Lenovo\\Desktop\\s\\cs\\857210755416289����ʡ�������躣����������վ.xls";
	//	Map m = readLsjyExl2(new File(s),50);
	//	 BufferedImage dataImg = DrawLsjy.createLsjyImg(list, true);
	//		ImgOperateUtil.printImg(dataImg,"e://cs.jpg");
	}*/

	
//	
//	
//	public static void main(String[] args) {
//		try {
//			List<QgdExlInfo> l = ReadExlInfoUtil.readQgdExl("C:\\Users\\Lenovo\\Desktop\\cs\\ǩ����ӡ�±��ģ��-����.xlsx",100);
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
