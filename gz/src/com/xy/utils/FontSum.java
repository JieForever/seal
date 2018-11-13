package com.xy.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

public class FontSum {

	private Graphics2D g2;
	private Font f;
	private FontRenderContext context2;
	
	public FontSum(){
		g2 = new BufferedImage(1, 1,  BufferedImage.TYPE_INT_RGB).createGraphics();
		if(g2 != null)
			context2 = g2.getFontRenderContext();
	}
	
	public FontSum(Graphics2D g2){
		if(g2 == null)
			throw new NullPointerException("g2不能等于null");
		this.g2 = g2;
		context2 = g2.getFontRenderContext();
	}
	
	public int sumMaxWidthFont(String fontStyle,int fontSize,String str1, String str2){
		if(f == null){
			 f = new Font(fontStyle, Font.PLAIN, fontSize);
			 g2.setFont(f);
		}
        Double w1 = f.getStringBounds(str1, context2).getWidth();
        Double w2 = f.getStringBounds(str2, context2).getWidth();
        
        return w1 > w2 ? w1.intValue() : w2.intValue();
	}

	public void dispose(){
		f = null;
		context2 = null;
		if(g2 != null)
			g2.dispose();
	}
	
	public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}
	
	/*public static void main(String[] args) {
		long b = System.currentTimeMillis();
		FontSum fs = new FontSum();
		double w = fs.sumMaxWidthFont("宋体", 25, "123", "你好");
		System.out.println(System.currentTimeMillis() - b);
		System.out.println(w);
		
	}*/
}
