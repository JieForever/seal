package com.xy.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.xy.bean.QgdExlInfo;
import com.xy.bean.Signature;
import com.xy.enumclass.ImgTypeEnum;
import com.xy.service.some.ConfigService;
import com.xy.utils.DrawSign;
import com.xy.utils.FolderOperateUtil;
import com.xy.utils.HSLAdjust;
import com.xy.utils.ImgOperateUtil;
import com.xy.utils.OtherUtil;
import com.xy.utils.ReadExlInfoUtil;
import com.xy.utils.cae.RarComAndExt;
import com.xy.utils.cae.ZipComAndExt;

public class QgdService {
	
	private static Logger logger = Logger.getLogger(QgdService.class);
	
	public static String se = File.separator;
	private static final int FBL_UP = 1525;
	private static final int FBL_DOWN = 1286;
	private static final int FBL_DOWN_ONE = 500;
	
	private static final int MIN_ONE = 200;
	private static final int MIN_OTHER = 500;
	private static final int MAXDATALINE = 100;

	
	
	//��¼�ϴ�ѹ���ļ���·��
	public String orgFilePath;
	//��¼Excel�ļ�·��
	public String exlFilePath;
	//��¼������·��
	public String resultFilePath;
	//��ʱ�ļ���
	private String tempFolder = "temp_c";
	//Ҫ������ļ���
	private String packFolder = "gz_qgd";
	//ӡ��ģ��ͼƬ
	//private static final String SIGNDEMO = "/demo1.png";
	private String model ;
	private int[] rgb = new int[3];
	
	public QgdService(String orgFilePath,String exlFilePath, String resultFilePath){
		this.orgFilePath = orgFilePath;
		this.exlFilePath = exlFilePath;
		this.resultFilePath = resultFilePath;
		getConfigInit();
	}
	
	private void getConfigInit(){
		try{
			model	="/"+ new ConfigService(ConfigService.MODEL_CONFIG).getValue("qgd") +".png";
			String[] s = new ConfigService(ConfigService.FONTCOLOR_CONFIG).getValue("qgd_RGB").split(",");
			rgb[0] = Integer.valueOf(s[0]);
			rgb[1] = Integer.valueOf(s[1]);
			rgb[2] = Integer.valueOf(s[2]);
		}catch(Exception e){
			 model = "/default.png" ;
		     rgb = new int[]{254,41,40};
		     e.printStackTrace();
			 logger.error(e.getMessage());
		}
	}
	
	public boolean  doIt(){
		String comFolder = resultFilePath +se+ tempFolder;
		boolean	flag = false;
		String suffix = OtherUtil.getFileSuffix(orgFilePath);
		if("zip".equalsIgnoreCase(suffix)){//��ѹ�ļ�
			flag = new ZipComAndExt().doExtract(orgFilePath, comFolder, null);
		}else{
			flag = new RarComAndExt().doExtract(orgFilePath, comFolder, null);
		}
		//��2�ֽ�ѹȫ��ʧЧ��ֱ�ӷ���
		if(!flag){
			return true;
		}
		try{
			//����Excel
			List<QgdExlInfo> l = ReadExlInfoUtil.readQgdExl(exlFilePath,MAXDATALINE);
			if(l == null) return true;
			int lSize = l.size();
			if(lSize < 2)
			{
				OtherUtil.say("������:Excel�ļ���û������");
				return true;
			}
			boolean hasResult = false;
			for(int i = 1; i < lSize; i++)
			{//����ȥ����ͷ
				 QgdExlInfo qgd = l.get(i);
				 //��ȡ�ļ���,������׺
				 String ff =  OtherUtil.getFN(orgFilePath);
				 //��Excel�е���Ϣ��ȡ��ӦͼƬ
			     File f = new File(comFolder + se + ff + se + qgd.getShmc()+".jpg");
				 try {
					 doIt2(f,qgd);
					 hasResult = true;
					 OtherUtil.say("�����̡�:"+f.getName()+" ���³ɹ���"); 
				} catch (Exception e) {
					 OtherUtil.say("�����̡�:"+f.getName()+" ����ʧ�ܣ�"); 
					 e.printStackTrace();
					 logger.error("��qgd��"+e.getMessage());
				}	
			}
			
			if(hasResult)
			{
				String packname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
				//���
				boolean isSucc = new ZipComAndExt().doCompress(resultFilePath + se + packFolder,resultFilePath+se+ "qgd_"+packname + ".zip");
				if(isSucc){
					OtherUtil.say("�������:�����  = qgd_" + packname + ".zip");
					FolderOperateUtil.deletefile(resultFilePath + se + packFolder);
				}else{
					OtherUtil.say("�������:���ʧ�ܡ�����");
					OtherUtil.say("����� :"+ resultFilePath + se + packFolder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("��qgd��"+e.getMessage());
		}finally{
			//ɾ����ʱ�ļ���
			FolderOperateUtil.deletefile(resultFilePath + se + tempFolder);
			OtherUtil.say("-----------end-----------");
		}
		return true;
	}
	
	private void doIt2(File f,QgdExlInfo qgd) throws Exception{
		double zb = Double.valueOf(qgd.getQzzb());
		//�ָ�ͼƬ
		int r = 1 ,c = 1;
		Integer choose = ImgTypeEnum.getCodeByType(qgd.getTplx());
		if(choose == null){
			OtherUtil.say("������:δ֪ͼƬ�汾 :"+ choose);
			throw new NullPointerException("������:ͼƬ�汾���� :"+ choose);
		}
		switch(choose)
		{
			case 1:
				r = 3;c=3;
				break;
			case 2:
				r = 3;c=2;
				break;
			case 3:
				r = 3;c=1;
				break;
		}
		
		BufferedImage qgdImg = null;
		try{
			qgdImg = ImageIO.read(f);
		}catch(IIOException e){
			OtherUtil.say("������:δ�ҵ� "+f.getName());
			throw new IIOException("������:δ�ҵ�  "+f.getName());
		}
		
		double scalse = 1;
		BufferedImage scaleOfImg = qgdImg;
		if(choose != 3)
		{
			if(MIN_OTHER > ImgOperateUtil.getMinBorder(qgdImg)){
				OtherUtil.say("������:"+f.getName()+"��һ�߷ֱ���С��"+MIN_OTHER+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
				throw new Exception("������:"+f.getName()+"��һ�߷ֱ���С��"+MIN_OTHER+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
			}
			scalse = ImgOperateUtil.getImgScale(qgdImg,FBL_UP, FBL_DOWN);
			scaleOfImg = ImgOperateUtil.scaleImg(qgdImg, scalse, scalse, false);
		}else{
			if(MIN_ONE > ImgOperateUtil.getMinBorder(qgdImg)){
				OtherUtil.say("������:"+f.getName()+"��һ�߷ֱ���С��"+MIN_ONE+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
				throw new Exception("������:"+f.getName()+"��һ�߷ֱ���С��"+MIN_ONE+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
			}
			scalse = ImgOperateUtil.getImgScale(qgdImg,FBL_DOWN_ONE, FBL_DOWN_ONE);
			scaleOfImg = ImgOperateUtil.scaleImg(qgdImg, scalse, scalse, false);
		}
		BufferedImage[][] i = new ImgOperateUtil(c,r).cutImg(scaleOfImg);
		for(int k = 0; k < c; k++){
			int temp = 0;
			temp = Integer.valueOf(qgd.getQzwz()[k]); 
			//ѡ��Ҫ���ƵĲ���
			int imgRowIndex = temp /c; 
			int imgColIndex = temp % c - 1; 
			if(temp % c == 0){
				imgRowIndex --;
				imgColIndex += c;
			}
			//�Ƕ�
			double jd = Double.valueOf(qgd.getQzjd()[k]);
			Signature s = new Signature(new Color(rgb[0],rgb[1],rgb[2],255), qgd.getQzmc(), qgd.getYyzzh(), zb, 26, 20, 0, jd);//28
			//����
			DrawSign ds = new DrawSign(s);
			BufferedImage signMode = ImgOperateUtil.readModeImg(model);
			i[imgRowIndex][imgColIndex] = ds.drawSign(signMode, i[imgRowIndex][imgColIndex]);
		}
		//ƴ��
		BufferedImage bb = ImgOperateUtil.jointImg(i);
		//����ͼƬhsl
		double du = Math.random() * 15;
		bb = new HSLAdjust(du, du, 1).filter(bb, null);
		//���ɽ��·��
		FolderOperateUtil.getFolder(resultFilePath, packFolder + se + qgd.getShid() + qgd.getShmc());
		//��ͼƬ��������·��
		String finallyImgPath = resultFilePath + se + packFolder + se + qgd.getShid() + qgd.getShmc() + se + qgd.getShmc()+".jpg";
		ImgOperateUtil.printImg(bb, finallyImgPath);
	}
	
	
	
//	public static void main(String[] args) {
//		
//		String s1 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\���Ե���ǩ����.zip";
//		String s2 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\cs_qgd.xls";
//		new QgdService(s1,s2,"e:\\").doIt();
//	}
	
}
