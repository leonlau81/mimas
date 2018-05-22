/**
 * 
 */
package com.vanseed.mimas.common.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author leon
 *
 */
public class GraphicCodeUtils {

    public static final String RANDOMCODEKEY = "RANDOM_VALIDATE_CODEKEY";//放到session中的key
    private static Random random = new Random();
    private static String randString = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";//随机产生的字符串
    
    private static int width = 320;//图片宽
    private static int height = 100;//图片高
    private static int lineSize = 50;//干扰线数量
    private static int stringNum = 4;//随机产生字符数量
    /*
     * 获得字体
     */
    private static Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,72);
    }
    /*
     * 获得颜色
     */
    private static Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
     
        return new Color(r,g,b);
    }
    
    /**
     * 生成随机图片
     */
    public static GraphicCode getRandcode() {
        
        String randomString = "";
        for(int i=1;i<=stringNum;i++){
        	String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
            randomString +=rand;
        }
        
        return getCode(randomString);
    }
    
    /**
     * 生成指定图片
     */
    public static GraphicCode getCode(String code) {
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,180));
        g.setColor(getRandColor(110, 133));
        
        //绘制随机字符
        drawString(g, code);
        
        //绘制干扰线
        for(int i=0;i<=lineSize;i++){
        	drowLine(g);
        }
        
        g.dispose();
        
        return new GraphicCode(code, image);
    }
    
    /*
     * 绘制字符串
     */
    private static void drawString(Graphics g,String randomString){
        g.setFont(getFont());        
        for(int i=0; i<randomString.length(); i++) {
        	g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        	String c = randomString.substring(i, i+1);
        	g.translate(random.nextInt(3), random.nextInt(3));
        	g.drawString(c, 80*i, 64);
        }
    }
    
    /*
     * 绘制字符串
     */
    private static String drowString(Graphics g,String randomString, int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
    
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13*i, 16);
        
        return randomString;
    }
    
    /*
     * 绘制干扰线
     */
    private static void drowLine(Graphics g){
        int x = random.nextInt(Math.round(width/2));
        int y = random.nextInt(Math.round(height/2));
        int xl = random.nextInt(width);
        int yl = random.nextInt(height);
        Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象  
        g2.setStroke(new BasicStroke(1.5f));           
        g2.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
       
        g.drawLine(x, y, x+xl, y+yl);
    }
    /*
     * 获取随机的字符
     */
    public static String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
    
    
    /*
     * 图片识别码VO
     */
    public static class GraphicCode {
    	private String code;
    	private BufferedImage image;

    	public GraphicCode(String code, BufferedImage image) {
    		this.code = code;
    		this.image = image;
    	}
    	
    	public String getCode() {
    		return code;
    	}

    	public void setCode(String code) {
    		this.code = code;
    	}

    	public BufferedImage getImage() {
    		return image;
    	}

    	public void setImage(BufferedImage image) {
    		this.image = image;
    	}
    }
}
