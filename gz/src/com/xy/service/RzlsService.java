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

import com.xy.bean.RzlsExlInfo;
import com.xy.bean.RzlsQzExlInfo;
import com.xy.bean.Signature;
import com.xy.bean.TableXY;
import com.xy.service.some.ConfigService;
import com.xy.utils.DrawLine;
import com.xy.utils.DrawRzls;
import com.xy.utils.DrawSign;
import com.xy.utils.DrawTable;
import com.xy.utils.FolderOperateUtil;
import com.xy.utils.ImgOperateUtil;
import com.xy.utils.OtherUtil;
import com.xy.utils.ReadExlInfoUtil;
import com.xy.utils.cae.RarComAndExt;
import com.xy.utils.cae.ZipComAndExt;

public class RzlsService {

	private static Logger logger = Logger.getLogger(RzlsService.class);

	public static String se = File.separator;
	public String orgFilePath ;//Դ�ļ�·��
	public String resultFilePath ;//���·��
	public String qzFilePath;//��ˮǩ����Ϣ�ļ�·��
	private int lineNum;
	private int type;//ͼƬ��ʽ
	
	public String tempFolder = "temp_c";//��ʱ�ļ���
	public String packFolder = "gz_rzls";
	//private static final String SIGNDEMO = "/demo1.png"; //ǩ��ģ��
	private String model ;
	private int rgb[] = new int[3];
	
	public RzlsService(String orgFilePath, String qzFilePath, String resultFilePath, int type){
		this.orgFilePath = orgFilePath;
		this.qzFilePath = qzFilePath;
		this.resultFilePath = resultFilePath;
		this.type = type;
		this.lineNum = type == 1 ? 35 : 50;
		getConfigInit();
	}
	
	private void getConfigInit(){
		try{
			model	="/"+ new ConfigService(ConfigService.MODEL_CONFIG).getValue("rzls") +".png";
			String[] s = new ConfigService(ConfigService.FONTCOLOR_CONFIG).getValue("rzls_RGB").split(",");
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
			Map<String,RzlsQzExlInfo> qzMap = ReadExlInfoUtil.readRzlsQzExl(qzFilePath);
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
				Map<Integer,Object> dataMap =  ReadExlInfoUtil.readRzlsExl(ff, lineNum);
				@SuppressWarnings("unchecked")
				List<List<RzlsExlInfo>> dataList = (List<List<RzlsExlInfo>>)dataMap.get(1);
				if(dataList == null) return true;
				if(dataList.size() <= 0)
				{
					OtherUtil.say("�����桿:�� "+ff.getName() + " �ļ�����Ϊ�ջ�ÿ�����ݾ��д���,���ļ��Ѻ���");
					continue;
				}
				String shid = ((RzlsExlInfo)dataMap.get(2)).getShid();//�̻�id
				String shmc = ((RzlsExlInfo)dataMap.get(2)).getShmc();//�̻�����
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
						int[] cs = DrawRzls.sumMaxWidth2(dataList.get(k));
						int[] rs = new int[]{DrawRzls.fontSize + DrawRzls.rowSpacing};
					
						DrawRzls.setCs(cs);
					
						img = DrawRzls.createRzlsImg(dataList.get(k), type);
						
						dl = new DrawLine(2, 128, 128, 128);
						table = new TableXY(7, autoRowNum, DrawRzls.getCs(), rs, dl);
						dt = new DrawTable(DrawRzls.pointX0-20, DrawRzls.pointY0-30, DrawRzls.pointX0+OtherUtil.sumArr(DrawRzls.getCs())-20, DrawRzls.pointY0+OtherUtil.sumArr(rs,autoRowNum)-30);
						//���Ʊ��
						dt.initTable(table).createDiffSpaceTable(img);
						RzlsQzExlInfo qzInfo = qzMap.get(shid);
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
						img = DrawRzls.createRzlsImg(dataList.get(k), type);
						dt.setY2(dataList.get(k).size() * (DrawRzls.fontSize+DrawRzls.rowSpacing)+DrawRzls.pointY0-30);
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
				boolean isSuccPack =  new ZipComAndExt().doCompress(resultFilePath+se+packFolder,resultFilePath+se+"rzls_"+packname+".zip");
				if(isSuccPack)
				{
					OtherUtil.say("�������:������,����� = rzls_" + packname +".zip");
					//ɾ��ѹ��ǰ�ļ���
					FolderOperateUtil.deletefile(resultFilePath + se + packFolder);
				}else{
					OtherUtil.say("�������:���ʧ�ܡ�����");
					OtherUtil.say("����� :"+ resultFilePath + se + packFolder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("��rzls��"+e.getMessage());
		}finally{
			//ɾ����ʱ�ļ���
			FolderOperateUtil.deletefile(resultFilePath + se + tempFolder);
			OtherUtil.say("----------end----------");
		}
		return true;
	}
	
	private BufferedImage doIt2(BufferedImage img, RzlsQzExlInfo qzInfo) throws Exception{
		//ռ��
		double zb = Double.valueOf(qzInfo.getQzzb());
		//��ת�Ƕ�
		double jd = Double.valueOf(qzInfo.getQzjd());
		//�ָ�ͼƬ
		int c = 4, r = 5;
		if(type == 1){
			c = 5;
			r = 4;
		}
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
	
	
	public static void main(String[] args) {
		
		String s1 = "C:\\Users\\Lenovo\\Desktop\\������ˮ.zip";
		String s2 = "C:\\Users\\Lenovo\\Desktop\\q.xls";
		String s3 = "e:\\";
		new RzlsService(s1,s2,s3, 0).doIt();
	}
	
}
