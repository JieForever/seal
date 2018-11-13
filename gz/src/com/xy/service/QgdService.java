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

	
	
	//记录上传压缩文件的路径
	public String orgFilePath;
	//记录Excel文件路径
	public String exlFilePath;
	//记录结果存放路径
	public String resultFilePath;
	//临时文件夹
	private String tempFolder = "temp_c";
	//要打包的文件夹
	private String packFolder = "gz_qgd";
	//印章模型图片
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
		if("zip".equalsIgnoreCase(suffix)){//解压文件
			flag = new ZipComAndExt().doExtract(orgFilePath, comFolder, null);
		}else{
			flag = new RarComAndExt().doExtract(orgFilePath, comFolder, null);
		}
		//当2种解压全部失效，直接返回
		if(!flag){
			return true;
		}
		try{
			//解析Excel
			List<QgdExlInfo> l = ReadExlInfoUtil.readQgdExl(exlFilePath,MAXDATALINE);
			if(l == null) return true;
			int lSize = l.size();
			if(lSize < 2)
			{
				OtherUtil.say("【错误】:Excel文件中没有数据");
				return true;
			}
			boolean hasResult = false;
			for(int i = 1; i < lSize; i++)
			{//这里去掉表头
				 QgdExlInfo qgd = l.get(i);
				 //获取文件名,不含后缀
				 String ff =  OtherUtil.getFN(orgFilePath);
				 //由Excel中的信息获取对应图片
			     File f = new File(comFolder + se + ff + se + qgd.getShmc()+".jpg");
				 try {
					 doIt2(f,qgd);
					 hasResult = true;
					 OtherUtil.say("【过程】:"+f.getName()+" 盖章成功！"); 
				} catch (Exception e) {
					 OtherUtil.say("【过程】:"+f.getName()+" 盖章失败！"); 
					 e.printStackTrace();
					 logger.error("【qgd】"+e.getMessage());
				}	
			}
			
			if(hasResult)
			{
				String packname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
				//打包
				boolean isSucc = new ZipComAndExt().doCompress(resultFilePath + se + packFolder,resultFilePath+se+ "qgd_"+packname + ".zip");
				if(isSucc){
					OtherUtil.say("【结果】:结果包  = qgd_" + packname + ".zip");
					FolderOperateUtil.deletefile(resultFilePath + se + packFolder);
				}else{
					OtherUtil.say("【结果】:打包失败。。。");
					OtherUtil.say("结果在 :"+ resultFilePath + se + packFolder);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("【qgd】"+e.getMessage());
		}finally{
			//删除临时文件夹
			FolderOperateUtil.deletefile(resultFilePath + se + tempFolder);
			OtherUtil.say("-----------end-----------");
		}
		return true;
	}
	
	private void doIt2(File f,QgdExlInfo qgd) throws Exception{
		double zb = Double.valueOf(qgd.getQzzb());
		//分割图片
		int r = 1 ,c = 1;
		Integer choose = ImgTypeEnum.getCodeByType(qgd.getTplx());
		if(choose == null){
			OtherUtil.say("【错误】:未知图片版本 :"+ choose);
			throw new NullPointerException("【错误】:图片版本错误 :"+ choose);
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
			OtherUtil.say("【错误】:未找到 "+f.getName());
			throw new IIOException("【错误】:未找到  "+f.getName());
		}
		
		double scalse = 1;
		BufferedImage scaleOfImg = qgdImg;
		if(choose != 3)
		{
			if(MIN_OTHER > ImgOperateUtil.getMinBorder(qgdImg)){
				OtherUtil.say("【错误】:"+f.getName()+"有一边分辨率小于"+MIN_OTHER+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
				throw new Exception("【错误】:"+f.getName()+"有一边分辨率小于"+MIN_OTHER+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
			}
			scalse = ImgOperateUtil.getImgScale(qgdImg,FBL_UP, FBL_DOWN);
			scaleOfImg = ImgOperateUtil.scaleImg(qgdImg, scalse, scalse, false);
		}else{
			if(MIN_ONE > ImgOperateUtil.getMinBorder(qgdImg)){
				OtherUtil.say("【错误】:"+f.getName()+"有一边分辨率小于"+MIN_ONE+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
				throw new Exception("【错误】:"+f.getName()+"有一边分辨率小于"+MIN_ONE+" ["+qgdImg.getWidth()+"*"+qgdImg.getHeight()+"]");
			}
			scalse = ImgOperateUtil.getImgScale(qgdImg,FBL_DOWN_ONE, FBL_DOWN_ONE);
			scaleOfImg = ImgOperateUtil.scaleImg(qgdImg, scalse, scalse, false);
		}
		BufferedImage[][] i = new ImgOperateUtil(c,r).cutImg(scaleOfImg);
		for(int k = 0; k < c; k++){
			int temp = 0;
			temp = Integer.valueOf(qgd.getQzwz()[k]); 
			//选择要绘制的部分
			int imgRowIndex = temp /c; 
			int imgColIndex = temp % c - 1; 
			if(temp % c == 0){
				imgRowIndex --;
				imgColIndex += c;
			}
			//角度
			double jd = Double.valueOf(qgd.getQzjd()[k]);
			Signature s = new Signature(new Color(rgb[0],rgb[1],rgb[2],255), qgd.getQzmc(), qgd.getYyzzh(), zb, 26, 20, 0, jd);//28
			//绘制
			DrawSign ds = new DrawSign(s);
			BufferedImage signMode = ImgOperateUtil.readModeImg(model);
			i[imgRowIndex][imgColIndex] = ds.drawSign(signMode, i[imgRowIndex][imgColIndex]);
		}
		//拼接
		BufferedImage bb = ImgOperateUtil.jointImg(i);
		//调整图片hsl
		double du = Math.random() * 15;
		bb = new HSLAdjust(du, du, 1).filter(bb, null);
		//生成结果路径
		FolderOperateUtil.getFolder(resultFilePath, packFolder + se + qgd.getShid() + qgd.getShmc());
		//将图片输出到结果路径
		String finallyImgPath = resultFilePath + se + packFolder + se + qgd.getShid() + qgd.getShmc() + se + qgd.getShmc()+".jpg";
		ImgOperateUtil.printImg(bb, finallyImgPath);
	}
	
	
	
//	public static void main(String[] args) {
//		
//		String s1 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\测试单张签购单.zip";
//		String s2 = "C:\\Users\\Lenovo\\Desktop\\c\\csyl\\cs_qgd.xls";
//		new QgdService(s1,s2,"e:\\").doIt();
//	}
	
}
