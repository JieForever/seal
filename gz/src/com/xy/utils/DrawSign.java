package com.xy.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.xy.bean.Signature;

public class DrawSign {
	
	private Signature sign;
	
	public DrawSign(Signature sign){
		this.sign = sign;
	}
	
	/**
	 * 返回章的原图
	 * @param w 图的宽
	 * @param h 图的高
	 * @return
	 */
	public BufferedImage drawSign(int w, int h){
		BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2  = bimg.createGraphics();
		//将背景填充为白色
		g2.setColor(Color.white);
		g2.fillRect(0,0,w,h);
		//设置旋转角度
		g2.rotate(sign.rotationRadian, w/2, h/2);
		//设置颜色
		g2.setColor(sign.getsColor());
		//抗锯齿
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(sign.getHuanW()));
		//计算一些圆的参数
		int [] circleParm = computeCircle(bimg); 
		//绘圆
		drawCircle(g2,circleParm);
		//绘中心图
		drawCenterShap(g2,circleParm);
		//绘顶部文字
		drawTopStr(g2,circleParm,1);
		//绘制底部文字
	    drawBottomStr(g2,circleParm,1);
		g2.dispose();
		return bimg;
	}
	
	/**
	 * 给传入的图片进行绘章,并不使用自传入章模型
	 * 使用自动生成章
	 * @param bimg 要绘章的图片
	 * @return 返回绘制后的结果图片
	 */
	public BufferedImage drawSign(BufferedImage bimg){
		Graphics2D g2 = (Graphics2D)bimg.getGraphics();
		//设置旋转角度
		g2.rotate(sign.rotationRadian, bimg.getWidth()/2, bimg.getHeight()/2);
		//设置章颜色
		g2.setColor(sign.getsColor());
		//抗锯齿
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//计算一些圆的参数
		int [] circleParm = computeCircle(bimg); 
		//绘圆
		drawCircle(g2,circleParm);
		//绘中心图
		drawCenterShap(g2,circleParm);
		//绘顶部文字
		drawTopStr(g2,circleParm,1);
		//绘底部文字
		drawBottomStr(g2,circleParm,1);
		g2.dispose();
		return bimg;
	}
	
	/**
	 * 将一张图片绘制到另一张图片里
	 * @param demoSign
	 * @param targetImg
	 * @return
	 */
	public BufferedImage drawSign(BufferedImage demoSign, BufferedImage targetImg){
		//计算一些数据                                                        
		int [] circleParm = computeCircle(targetImg); 
		int demoW = demoSign.getWidth(); int helfDemoW = demoW/2;  
		int demoH = demoSign.getHeight();int helfDemoH = demoH/2;
		//计算缩小放大倍数
		double multiple = (circleParm[4]/(helfDemoW/1.0))*sign.getsPercent(); 
		//放大图片
		demoSign = ImgOperateUtil.scaleImg(demoSign, multiple, multiple,true);
		//创建章
		demoSign = createDemoSign(demoSign,multiple);
		//demoSign = ImgOperateUtil.scaleImg(demoSign, multiple, multiple,true);
		
		
		Graphics2D g2 = (Graphics2D)targetImg.getGraphics();
		//透明度
		AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	    g2.setComposite(alphaComposite);
		//抗锯齿
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		//旋转
		g2.rotate(sign.rotationRadian,targetImg.getWidth()/2,targetImg.getHeight()/2);
        //解决图片失真
        g2.drawImage(demoSign.getScaledInstance(-1,-1,Image.SCALE_SMOOTH),(int)((circleParm[2])-helfDemoW*multiple),(int)(circleParm[3]-helfDemoH*multiple),null);
        g2.dispose();
		return targetImg;
	}
	/**
	 * 传入一张带有圆环和中心图标的圆章图片，依据这张图绘制成一个圆章
	 * @param bimg
	 * @return
	 */
	public BufferedImage createDemoSign(BufferedImage bimg, double mu){
		Graphics2D g2 = (Graphics2D)bimg.getGraphics();
		//设置章颜色
		g2.setColor(sign.getsColor());
		//抗锯齿(文字)
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		/*
		 * 抗锯齿(图像)
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 */
		//计算一些圆的参数
		int [] circleParm = computeCircle(bimg);
		//绘顶部文字
		drawTopStr(g2,circleParm,mu);   
		//绘底部文字
		drawBottomStr(g2,circleParm,mu);
	    g2.dispose();
		return bimg;
	}
	
	/**
	 * 绘制圆形
	 * @param g2
	 * @param bimg
	 */
	private void drawCircle(Graphics2D g2, int[] circleParm){
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrame(circleParm[0], circleParm[1], circleParm[4]*2 , circleParm[4]*2);
		g2.draw(circle);
	}
	
	/**
	 * 绘制中心图形
	 * @param g2
	 * @param bimg
	 */
	private void drawCenterShap(Graphics2D g2,int []circleParm){
		//中心图像按占比设定大小
		int starSize = (int)(sign.getCenterShapSize() * sign.getsPercent()); 
		Font starFont = new Font("宋体",Font.BOLD,starSize);
		g2.setFont(starFont);
		g2.drawString(sign.getsCenterShap(), circleParm[2]-starSize/2,circleParm[3]+starSize/2);
		
	}
	
	/**
	 * 绘制签章上边文字
	 * @param g2
	 * @param circleParm
	 */
	private void drawTopStr(Graphics2D g2,int []circleParm, double s){
		//消除文字抗锯齿
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		double mu = Math.sqrt(s) + 0.3;
		//根据输入字符串得到字符数组
        String[] temp_messages = sign.getsTopStr().split("",0);
        int ilength = temp_messages.length - 1;
        String [] messages = new String[ilength];
        System.arraycopy(temp_messages, 1, messages, 0, ilength);
       
        //文字大小
        int fontSize = (int)(sign.getTopStrFontSize() *mu); 
        //字的其他参数
        Font f = new Font("宋体", Font.BOLD, fontSize);  
		FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(sign.getsTopStr(), context);
        //字符宽度＝字符串长度/字符数
        double char_interval = (bounds.getWidth() / ilength); 
        //上坡度
        double ascent = -bounds.getY(); 
        int first = 0,second = 0;
        boolean odd = false;
        if (ilength%2 == 1){
        	first = (ilength-1)/2;
            odd = true;
        }else{
            first = (ilength)/2-1;
            second = (ilength)/2;
            odd = false;
        }
        double radius2 = circleParm[4] - ascent*sign.fontScaleY - sign.getHuanW()*s;//-(fontSize*(sign.fontScaleY-1));
        double x0 = circleParm[2];     
        double y0 = circleParm[3] - circleParm[4] + ascent*sign.fontScaleY + sign.getHuanW()*s;//+(fontSize*(sign.fontScaleY-1));
        //旋转角度
        //double a = 2*Math.asin(char_interval/(2*radius2)); 
        double a = Signature.topAreaAngle / ilength ; 
        
        if (odd)
        {
        	//计算中间字的实际宽度
        	FontRenderContext context2 = g2.getFontRenderContext();
            Rectangle2D bounds2 = f.getStringBounds(messages[first], context2);
            AffineTransform transform = new AffineTransform();
            transform.scale(1, sign.fontScaleY);
            Font f_c = f.deriveFont(transform);
            g2.setFont(f_c);
            //g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawString(messages[first], (float)(x0 - bounds2.getWidth()/2), (float)y0);
            
            //中心点的右边
            for (int i=first+1;i<ilength;i++)
            {
                double aa = (i - first) * a;    		          
                double ax = radius2 * Math.sin(aa);               
                double ay = radius2 - radius2 * Math.cos(aa);	  	
              //  AffineTransform transform = AffineTransform.getRotateInstance(aa);
              //  Font f2 = f.deriveFont(transform);
                AffineTransform transform2 = new AffineTransform();
                transform2.rotate(aa);
                transform2.scale(1,sign.fontScaleY);
                Font f3 = f.deriveFont(transform2);
                g2.setFont(f3);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));      
            }
            //中心点的左边
            for (int i=first-1;i>-1;i--)
            {
                double aa = (first - i) * a;  
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                //AffineTransform transform = AffineTransform.getRotateInstance(-aa);
               // Font f2 = f.deriveFont(transform);
                AffineTransform transform2 = new AffineTransform();
                transform2.rotate(-aa);
                transform2.scale(1, sign.fontScaleY);
                Font f2 = f.deriveFont(transform2);  
                g2.setFont(f2);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
            }
        }
        else
        {
            //中心点的右边
            for (int i=second;i<ilength;i++)
            {
                double aa = (i - second + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
               // AffineTransform transform = AffineTransform.getRotateInstance(aa);
               // Font f2 = f.deriveFont(transform);
                AffineTransform transform = new AffineTransform();
                transform.rotate(aa);
                transform.scale(1,sign.fontScaleY);
                Font f2 = f.deriveFont(transform);
                g2.setFont(f2);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay - char_interval/2* Math.sin(aa)));
            }
            //中心点的左边
            for (int i=first;i>-1;i--)
            {
                double aa = (first - i + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
               // AffineTransform transform = AffineTransform.getRotateInstance(-aa);
               // Font f2 = f.deriveFont(transform);
                AffineTransform transform = new AffineTransform();
                transform.rotate(-aa);
                transform.scale(1, sign.fontScaleY);
                Font f2 = f.deriveFont(transform);
                g2.setFont(f2);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 + ay + char_interval/2* Math.sin(aa)));
            }
        }
	}
	
	
	/**
	 * 绘制签章下边的文字
	 * @param g2
	 * @param circleParm
	 */
	private void drawBottomStr(Graphics2D g2,int []circleParm, double s){
		//消除文字抗锯齿
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//根据输入字符串得到字符数组
        String[] temp_messages = sign.getsBottomStr().split("",0);
        int ilength = temp_messages.length - 1;
        String[] messages = new String[ilength];
        System.arraycopy(temp_messages,1,messages,0,ilength);
        
        //文字大小
        int fontSize = (int)(sign.getBottomStrFontSize()* s);
        //设置文字参数
        Font f = new Font("微软雅黑", Font.PLAIN, fontSize);
		FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(sign.getsBottomStr(), context);  
        //字符宽度＝字符串长度/字符数
        double char_interval = (bounds.getWidth() / ilength);
        //下坡度
        double ascent = -bounds.getY() ;
        int first = 0,second = 0;
        boolean odd = false;
        if (ilength%2 == 1){
        	first = (ilength-1)/2;
            odd = true;
        }else{
            first = (ilength)/2-1;
            second = (ilength)/2;
            odd = false;
        }
        //文字围绕半径: 圆半径 - ascent
        double radius2 = circleParm[4] - ascent - sign.getHuanW()*s;
        double x0 = circleParm[2];
        double y0 = circleParm[3] + radius2 + ascent - sign.getHuanW()*s;
        //旋转角度
        double a = 2*Math.asin(char_interval/(2*radius2)); 
        if (odd)
        {
            g2.setFont(f);
            g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0 );
            //中心点的右边
            for (int i=first+1;i<ilength;i++)
            {
            	  double aa = (i - first) * a;
                  double ax = radius2 * Math.sin(aa);
                  double ay = radius2 - radius2 * Math.cos(aa);
                  AffineTransform transform = AffineTransform.getRotateInstance(-aa);
                  Font f2 = f.deriveFont(transform);
                  g2.setFont(f2);
                  g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 - ay + char_interval/2* Math.sin(aa))); 
            }
            //中心点的左边
            for (int i=first-1;i>-1;i--)
            {
            	double aa = (first - i) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa);
                AffineTransform transform = AffineTransform.getRotateInstance(aa);
                Font f2 = f.deriveFont(transform);
                g2.setFont(f2);
                g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 - ay - char_interval/2* Math.sin(aa)));
            }
        }
        else
        {
            //中心点的右边
            for (int i=second;i<ilength;i++)
            {
            	 double aa = (i - second + 0.5) * a;
                 double ax = radius2 * Math.sin(aa);
                 double ay = radius2 - radius2 * Math.cos(aa) ;
                 AffineTransform transform = AffineTransform.getRotateInstance(-aa);
                 Font f2 = f.deriveFont(transform);
                 g2.setFont(f2);
                 g2.drawString(messages[i], (float)(x0 + ax - char_interval/2* Math.cos(aa)), (float)(y0 - ay + char_interval/2* Math.sin(aa)));
            }
            //中心点的左边
            for (int i=first;i>-1;i--)
            {
            	double aa = (first - i + 0.5) * a;
                double ax = radius2 * Math.sin(aa);
                double ay = radius2 - radius2 * Math.cos(aa) ;
                AffineTransform transform = AffineTransform.getRotateInstance(aa);
                Font f2 = f.deriveFont(transform);
                g2.setFont(f2);
                g2.drawString(messages[i], (float)(x0 - ax - char_interval/2* Math.cos(aa)), (float)(y0 - ay - char_interval/2* Math.sin(aa)));
            }
        } 
	}
	
	/**
	 * 计算居中最大圆的左上角坐标(xy)、圆心坐标(xy)以及半径
	 * @param drawImg
	 * @return
	 */
	public int[] computeCircle (BufferedImage bimg){
		int iH = bimg.getHeight();      
		int iW = bimg.getWidth();	    
		int maxRadius = iH > iW ? iW/2 : iH/2;
		return new int[]{iW/2-maxRadius,iH/2-maxRadius,iW/2,iH/2,maxRadius};	
	}
	
	/*
	public static void main(String[] args) {
		try {
			BufferedImage i = ImgOperateUtil.readModeImg("/demo1.png");
			BufferedImage i2 = ImgOperateUtil.readModeImg2("C:\\Users\\Lenovo\\Desktop\\河南省安阳市安阳县蓝天超市.jpg");
			Signature s = new Signature(new Color(254,41,40,255),"辽宁省锦州辽宁省锦州辽", "123123123123",0.7,28,20,0,0);
			System.out.println(s);
			DrawSign ds = new DrawSign(s);
			//BufferedImage r = ds.createDemoSign(i);
		
			BufferedImage [][] bb = new ImgOperateUtil(3,3).cutImg(i2);
			for(int t = 0; t<3;t++){
				bb[1][t] = ds.drawSign(i, bb[1][t]);
				//ImgOperateUtil.printImg(bb[1][t],"E:\\"+t+".jpg");
			}

			ImgOperateUtil.printImg(ImgOperateUtil.jointImg(bb), "E:\\aaa.jpg");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/*
	public static void main(String[] args) {
	
		try {
			BufferedImage i = ImgOperateUtil.readModeImg("/demo1.png");
			Signature s = new Signature(Color.red,"0","辽宁省锦州辽宁省锦州辽宁省锦州辽宁省锦州辽宁省锦州", "350724333456",0.8,0,0,0,0,0);
			System.out.println(s);
			DrawSign ds = new DrawSign(s);
			BufferedImage r = ds.createDemoSign(ImgOperateUtil.scaleImg(i,2,2),2);
			
			ImgOperateUtil.printImg(r,"E:\\ccc.jpg");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
}
