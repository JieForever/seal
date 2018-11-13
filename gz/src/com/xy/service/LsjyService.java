package com.xy.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xy.bean.LsjyExlInfo;
import com.xy.bean.LsjyQzExlInfo;
import com.xy.bean.Signature;
import com.xy.bean.TableXY;
import com.xy.service.some.ConfigService;
import com.xy.utils.DrawLine;
import com.xy.utils.DrawLsjy;
import com.xy.utils.DrawRzls;
import com.xy.utils.DrawSign;
import com.xy.utils.DrawTable;
import com.xy.utils.FolderOperateUtil;
import com.xy.utils.ImgOperateUtil;
import com.xy.utils.OtherUtil;
import com.xy.utils.ReadExlInfoUtil;
import com.xy.utils.cae.RarComAndExt;
import com.xy.utils.cae.ZipComAndExt;

public class LsjyService {
	
	private static Logger logger = Logger.getLogger(LsjyService.class);

	public static String se = File.separator;
	public String orgFilePath ;//Դ�ļ�·��
	public String resultFilePath ;//���·��
	public String qzFilePath;//��ˮǩ����Ϣ�ļ�·��
	public String tempFolder = "temp_c";//��ʱ�ļ���
	public String packFolder = "gz_lsjy";
	//private static final String SIGNDEMO = "/demo1.png"; //ǩ��ģ��

	private String model;
    private int[] rgb = new int[3];
	
	public LsjyService(String orgFilePath, String qzFilePath, String resultFilePath){
		this.orgFilePath = orgFilePath;
		this.qzFilePath = qzFilePath;
		this.resultFilePath = resultFilePath;
		getConfigInit();
	}
	
	private void getConfigInit(){
		try{
			model	="/"+ new ConfigService(ConfigService.MODEL_CONFIG).getValue("lsjy") +".png";
			String[] s = new ConfigService(ConfigService.FONTCOLOR_CONFIG).getValue("lsjy_RGB").split(",");
			rgb[0] = Integer.valueOf(s[0]);
			rgb[1] = Integer.valueOf(s[1]);
			rgb[2] = Integer.valueOf(s[2]);
		}catch(Exception e){
			 model = "/default.png" ;
		     rgb = new int[]{254,29,28};
		     e.printStackTrace();
			 logger.error(e.getMessage());
		}
	}
	
	public boolean doIt(){
		boolean	flag = false;
		String suffix = OtherUtil.getFileSuffix(orgFilePath);
		if("zip".equalsIgnoreCase(suffix))
		{//zip��ѹ
			flag = new ZipComAndExt().doExtract(orgFilePath, resultFilePath+ se + tempFolder, null);
		}
		else
		{//rar��ѹ
			flag = new RarComAndExt().doExtract(orgFilePath, resultFilePath+se + tempFolder, null);
		}
		//��2�ֽ�ѹȫ��ʧЧ��ֱ�ӷ���	
		if(!flag)
			return true;
		
		boolean hasResult = false;
		try{
			//��ȡ��ˮǩ����Ϣ
			Map<String,LsjyQzExlInfo> qzMap = ReadExlInfoUtil.readLsjyQzExl(qzFilePath);
			if(qzMap.size() <= 0)
			{
				OtherUtil.say("������:�� "+qzFilePath+" �ļ�����Ϊ�ջ�ÿ����Ϣ���д���");
				return true;
			}  	
			File  f = new File(resultFilePath + se + tempFolder );
			List<String> allFilePath = new ArrayList<String>();
			//��ѹ���ļ�����������ļ�·����������allFilePath
			OtherUtil.getFiles(f,allFilePath);
			lable:for(int j = 0; j < allFilePath.size(); j++)
			{
				//��Excel�ж�ȡ����
				File ff = new File(allFilePath.get(j)); 
				Map<Integer,Object> dataMap =  ReadExlInfoUtil.readLsjyExl2(ff, 50);
				@SuppressWarnings("unchecked")
				List<List<LsjyExlInfo>> dataList = (List<List<LsjyExlInfo>>)dataMap.get(1);
				if(dataList == null) return true;
				if(dataList.size() <= 0)
				{
					OtherUtil.say("�����桿:�� "+ff.getName() + " �ļ�����Ϊ�ջ�ÿ�����ݾ��д���,���ļ��Ѻ���");
					continue;
				}
				String shid = ((LsjyExlInfo)dataMap.get(2)).getShid();//�̻�id
				String shmc = ((LsjyExlInfo)dataMap.get(2)).getShmc();//�̻�����
				BufferedImage img = null;
				boolean hasFolder = false;
				DrawLine dl = null;
				TableXY table = null;
				DrawTable dt = null;
				
				for(int k = 0; k < dataList.size(); k++)
				{
					if( k == 0 )
					{
						int autoRowNum = dataList.get(k).size();
						int[] cs = DrawLsjy.sumMaxWidth(dataList.get(k));
						int[] rs =  new int[]{DrawRzls.fontSize + DrawRzls.rowSpacing};
						DrawLsjy.setCs(cs);
						
						img = DrawLsjy.createLsjyImg(dataList.get(k));
						
						dl = new DrawLine(2, 128, 128, 128);
						table = new TableXY(5, autoRowNum, cs, rs, dl);
						dt = new DrawTable(DrawLsjy.pointX0-20, DrawLsjy.pointY0-30, DrawLsjy.pointX0+OtherUtil.sumArr(cs)-20, DrawLsjy.pointY0+OtherUtil.sumArr(rs,autoRowNum)-30);
						//���Ʊ��
						dt.initTable(table).createDiffSpaceTable(img);
						
						LsjyQzExlInfo qzInfo = qzMap.get(shid);
						if(qzInfo == null)
						{
							OtherUtil.say("�����桿:û���ҵ����̻��� "+ shid +" ƥ���ǩ����Ϣ,�ļ� "+ ff.getName() +"�Ѻ���");
							continue lable;
						}
						//�Ե�һ��ͼƬ����
						img = doIt2(img, qzInfo);
					}
					else
					{
						img = DrawLsjy.createLsjyImg(dataList.get(k));
						dt.setY2(dataList.get(k).size() * (DrawLsjy.fontSize+DrawLsjy.rowSpacing)+DrawLsjy.pointY0-30);
						table.setRowNum(dataList.get(k).size());
						dt.createDiffSpaceTable(img);
					}
					
					if(!hasFolder)
					{
						String r_pack = packFolder + se + shid + shmc;
						FolderOperateUtil.getFolder(resultFilePath,r_pack);
						hasFolder = true;
					}
					ImgOperateUtil.printImg(img, resultFilePath + se + packFolder + se + shid + shmc + se + (k+1) +".jpg");
					OtherUtil.say("�����̡�:����ͼƬ  "+ (k+1) +".jpg"); 
					img = null;
				}
				dataMap = null ; dataList = null;
				OtherUtil.say("��"+ff.getName() + "��ͼƬ������ɣ�");
				hasResult = true;
			}
			
			if(hasResult)
			{	 
				String packname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
				 //���
				OtherUtil.say("���Ե�,����Ϊ������...");
				boolean isSuccPack =  new ZipComAndExt().doCompress(resultFilePath+se+packFolder,resultFilePath+se+"lsjy_"+packname+".zip");
				if(isSuccPack)
				{
					OtherUtil.say("�������:������,����� = lsjy_" + packname +".zip");
					//ɾ��ѹ��ǰ�ļ���
					FolderOperateUtil.deletefile(resultFilePath + se + packFolder);
				}else{
					OtherUtil.say("�������:���ʧ�ܡ�����");
					OtherUtil.say("����� :"+ resultFilePath + se + packFolder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("��lsjy��"+e.getMessage());
		}finally{
			//ɾ����ʱ�ļ���
			FolderOperateUtil.deletefile(resultFilePath + se + tempFolder);
	
			OtherUtil.say("----------end----------");
		}
		return true;
	}
	
	private BufferedImage doIt2(BufferedImage img, LsjyQzExlInfo qzInfo) throws Exception{
		//ռ��
		double zb = Double.valueOf(qzInfo.getQzzb());
		//��ת�Ƕ�
		double jd = Double.valueOf(qzInfo.getQzjd());
		//�ָ�ͼƬ
		int c = 4, r = 5;
		BufferedImage[][] i =  new ImgOperateUtil(c,r).cutImg(img);
		//����ǩ����Ϣ����
		Signature s = new Signature(new Color(rgb[0],rgb[1],rgb[2]), qzInfo.getQzmc(), qzInfo.getYyzz(), zb, 25, 17, 10, jd);
		DrawSign ds = new DrawSign(s);
		int temp = Integer.valueOf(qzInfo.getQzwz()); 
		//ѡ��Ҫ���ƵĲ���
		int imgRowIndex = temp /c; 
		int imgColIndex = temp % c - 1; 
		if(temp % c == 0)
		{
			imgRowIndex --;
			imgColIndex += c;
		}
		//����
		BufferedImage signMode = ImgOperateUtil.readModeImg(model);
		ds.drawSign(signMode, i[imgRowIndex][imgColIndex]);
		//ƴ��
		return ImgOperateUtil.jointImg(i);
	}
	
	
	
	
	
//	public static void main(String[] args) {
//		
//		String s1 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\857210755416287����ʡ�����к�ɽ�ظ��˼���վ.zip";
//		String s2 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\q.xls";
//		String s3 = "e:\\";
//		new LsjyService(s1,s2,s3).doIt();
//	}
	
}
