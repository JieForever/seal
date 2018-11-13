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
	 * �����µ�ԭͼ
	 * @param w ͼ�Ŀ�
	 * @param h ͼ�ĸ�
	 * @return
	 */
	public BufferedImage drawSign(int w, int h){
		BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2  = bimg.createGraphics();
		//���������Ϊ��ɫ
		g2.setColor(Color.white);
		g2.fillRect(0,0,w,h);
		//������ת�Ƕ�
		g2.rotate(sign.rotationRadian, w/2, h/2);
		//������ɫ
		g2.setColor(sign.getsColor());
		//�����
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(sign.getHuanW()));
		//����һЩԲ�Ĳ���
		int [] circleParm = computeCircle(bimg); 
		//��Բ
		drawCircle(g2,circleParm);
		//������ͼ
		drawCenterShap(g2,circleParm);
		//�涥������
		drawTopStr(g2,circleParm,1);
		//���Ƶײ�����
	    drawBottomStr(g2,circleParm,1);
		g2.dispose();
		return bimg;
	}
	
	/**
	 * �������ͼƬ���л���,����ʹ���Դ�����ģ��
	 * ʹ���Զ�������
	 * @param bimg Ҫ���µ�ͼƬ
	 * @return ���ػ��ƺ�Ľ��ͼƬ
	 */
	public BufferedImage drawSign(BufferedImage bimg){
		Graphics2D g2 = (Graphics2D)bimg.getGraphics();
		//������ת�Ƕ�
		g2.rotate(sign.rotationRadian, bimg.getWidth()/2, bimg.getHeight()/2);
		//��������ɫ
		g2.setColor(sign.getsColor());
		//�����
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//����һЩԲ�Ĳ���
		int [] circleParm = computeCircle(bimg); 
		//��Բ
		drawCircle(g2,circleParm);
		//������ͼ
		drawCenterShap(g2,circleParm);
		//�涥������
		drawTopStr(g2,circleParm,1);
		//��ײ�����
		drawBottomStr(g2,circleParm,1);
		g2.dispose();
		return bimg;
	}
	
	/**
	 * ��һ��ͼƬ���Ƶ���һ��ͼƬ��
	 * @param demoSign
	 * @param targetImg
	 * @return
	 */
	public BufferedImage drawSign(BufferedImage demoSign, BufferedImage targetImg){
		//����һЩ����                                                        
		int [] circleParm = computeCircle(targetImg); 
		int demoW = demoSign.getWidth(); int helfDemoW = demoW/2;  
		int demoH = demoSign.getHeight();int helfDemoH = demoH/2;
		//������С�Ŵ���
		double multiple = (circleParm[4]/(helfDemoW/1.0))*sign.getsPercent(); 
		//�Ŵ�ͼƬ
		demoSign = ImgOperateUtil.scaleImg(demoSign, multiple, multiple,true);
		//������
		demoSign = createDemoSign(demoSign,multiple);
		//demoSign = ImgOperateUtil.scaleImg(demoSign, multiple, multiple,true);
		
		
		Graphics2D g2 = (Graphics2D)targetImg.getGraphics();
		//͸����
		AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	    g2.setComposite(alphaComposite);
		//�����
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		//��ת
		g2.rotate(sign.rotationRadian,targetImg.getWidth()/2,targetImg.getHeight()/2);
        //���ͼƬʧ��
        g2.drawImage(demoSign.getScaledInstance(-1,-1,Image.SCALE_SMOOTH),(int)((circleParm[2])-helfDemoW*multiple),(int)(circleParm[3]-helfDemoH*multiple),null);
        g2.dispose();
		return targetImg;
	}
	/**
	 * ����һ�Ŵ���Բ��������ͼ���Բ��ͼƬ����������ͼ���Ƴ�һ��Բ��
	 * @param bimg
	 * @return
	 */
	public BufferedImage createDemoSign(BufferedImage bimg, double mu){
		Graphics2D g2 = (Graphics2D)bimg.getGraphics();
		//��������ɫ
		g2.setColor(sign.getsColor());
		//�����(����)
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		/*
		 * �����(ͼ��)
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 */
		//����һЩԲ�Ĳ���
		int [] circleParm = computeCircle(bimg);
		//�涥������
		drawTopStr(g2,circleParm,mu);   
		//��ײ�����
		drawBottomStr(g2,circleParm,mu);
	    g2.dispose();
		return bimg;
	}
	
	/**
	 * ����Բ��
	 * @param g2
	 * @param bimg
	 */
	private void drawCircle(Graphics2D g2, int[] circleParm){
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrame(circleParm[0], circleParm[1], circleParm[4]*2 , circleParm[4]*2);
		g2.draw(circle);
	}
	
	/**
	 * ��������ͼ��
	 * @param g2
	 * @param bimg
	 */
	private void drawCenterShap(Graphics2D g2,int []circleParm){
		//����ͼ��ռ���趨��С
		int starSize = (int)(sign.getCenterShapSize() * sign.getsPercent()); 
		Font starFont = new Font("����",Font.BOLD,starSize);
		g2.setFont(starFont);
		g2.drawString(sign.getsCenterShap(), circleParm[2]-starSize/2,circleParm[3]+starSize/2);
		
	}
	
	/**
	 * ����ǩ���ϱ�����
	 * @param g2
	 * @param circleParm
	 */
	private void drawTopStr(Graphics2D g2,int []circleParm, double s){
		//�������ֿ����
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		double mu = Math.sqrt(s) + 0.3;
		//���������ַ����õ��ַ�����
        String[] temp_messages = sign.getsTopStr().split("",0);
        int ilength = temp_messages.length - 1;
        String [] messages = new String[ilength];
        System.arraycopy(temp_messages, 1, messages, 0, ilength);
       
        //���ִ�С
        int fontSize = (int)(sign.getTopStrFontSize() *mu); 
        //�ֵ���������
        Font f = new Font("����", Font.BOLD, fontSize);  
		FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(sign.getsTopStr(), context);
        //�ַ���ȣ��ַ�������/�ַ���
        double char_interval = (bounds.getWidth() / ilength); 
        //���¶�
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
        //��ת�Ƕ�
        //double a = 2*Math.asin(char_interval/(2*radius2)); 
        double a = Signature.topAreaAngle / ilength ; 
        
        if (odd)
        {
        	//�����м��ֵ�ʵ�ʿ��
        	FontRenderContext context2 = g2.getFontRenderContext();
            Rectangle2D bounds2 = f.getStringBounds(messages[first], context2);
            AffineTransform transform = new AffineTransform();
            transform.scale(1, sign.fontScaleY);
            Font f_c = f.deriveFont(transform);
            g2.setFont(f_c);
            //g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawString(messages[first], (float)(x0 - bounds2.getWidth()/2), (float)y0);
            
            //���ĵ���ұ�
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
            //���ĵ�����
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
            //���ĵ���ұ�
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
            //���ĵ�����
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
	 * ����ǩ���±ߵ�����
	 * @param g2
	 * @param circleParm
	 */
	private void drawBottomStr(Graphics2D g2,int []circleParm, double s){
		//�������ֿ����
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//���������ַ����õ��ַ�����
        String[] temp_messages = sign.getsBottomStr().split("",0);
        int ilength = temp_messages.length - 1;
        String[] messages = new String[ilength];
        System.arraycopy(temp_messages,1,messages,0,ilength);
        
        //���ִ�С
        int fontSize = (int)(sign.getBottomStrFontSize()* s);
        //�������ֲ���
        Font f = new Font("΢���ź�", Font.PLAIN, fontSize);
		FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(sign.getsBottomStr(), context);  
        //�ַ���ȣ��ַ�������/�ַ���
        double char_interval = (bounds.getWidth() / ilength);
        //���¶�
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
        //����Χ�ư뾶: Բ�뾶 - ascent
        double radius2 = circleParm[4] - ascent - sign.getHuanW()*s;
        double x0 = circleParm[2];
        double y0 = circleParm[3] + radius2 + ascent - sign.getHuanW()*s;
        //��ת�Ƕ�
        double a = 2*Math.asin(char_interval/(2*radius2)); 
        if (odd)
        {
            g2.setFont(f);
            g2.drawString(messages[first], (float)(x0 - char_interval/2), (float)y0 );
            //���ĵ���ұ�
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
            //���ĵ�����
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
            //���ĵ���ұ�
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
            //���ĵ�����
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
	 * ����������Բ�����Ͻ�����(xy)��Բ������(xy)�Լ��뾶
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
			BufferedImage i2 = ImgOperateUtil.readModeImg2("C:\\Users\\Lenovo\\Desktop\\����ʡ�����а��������쳬��.jpg");
			Signature s = new Signature(new Color(254,41,40,255),"����ʡ��������ʡ������", "123123123123",0.7,28,20,0,0);
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
			Signature s = new Signature(Color.red,"0","����ʡ��������ʡ��������ʡ��������ʡ��������ʡ����", "350724333456",0.8,0,0,0,0,0);
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
