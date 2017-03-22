package com.zm.zxing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * core-2.2.jar 由谷歌提供 通过/Dome/WebRoot/WEB-INF/lib/core-2.2.jar 实现带logo二维码生成
 * @author zhangmin
 * 
 * 如果启动时报如下错误，说明用的jar包的jdk版本不一致
 * Exception in thread "main" java.lang.UnsupportedClassVersionError: 
 * com/google/zxing/EncodeHintType : Unsupported major.minor version 51.0
 * 
 */
public class Zxing {
	private static final int BLACK = 0xFF000000; //用于设置图案的颜色
	private static final int WHITE = 0xFFFFFFFF;//用于背景色

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 根据Logo路径生成带Logo 的二维码图片
	 * @param matrix
	 * @param format
	 * @param file
	 * @param logoPath
	 * @throws Exception
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file, String logoPath)throws Exception {
		BufferedImage matrixImage = toBufferedImage(matrix);
		logoMatrix(matrixImage, logoPath);
		if (!ImageIO.write(matrixImage, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

	/**
	 * 生成二维码图片
	 * @param matrix
	 * @param format
	 * @param stream
	 * @throws IOException
	 */
	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}
	
	/**
	 *  二维码 添加 logo图标 处理的方法, 
	 *  仿微信自动生成二维码效果，有圆角边框，logo和二维码间有空白区，logo带有灰色边框 
	 * @param matrixImage二维码图片对象
	 * @param logoPath logo 路径
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage logoMatrix(BufferedImage matrixImage,String logoPath) throws Exception{
		 //读取二维码图片，并构建绘图对象 
        Graphics2D g2 = matrixImage.createGraphics();  
         //二维码图片的宽高
        int matrixWidth = matrixImage.getWidth();  
        int matrixHeigh = matrixImage.getHeight();  
         //读取Logo图片 
        BufferedImage logo = ImageIO.read(new File(logoPath));  
        //开始绘制图片  
        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制       
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke);// 设置笔画对象  
        //指定弧度的圆角矩形  
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);  
        g2.setColor(Color.white);  
        g2.draw(round);// 绘制圆弧矩形  
          
        //设置logo 有一道灰色边框  
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke2);// 设置笔画对象  
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);  
        g2.setColor(new Color(128,128,128));  
        g2.draw(round2);// 绘制圆弧矩形  
        g2.dispose();  
        matrixImage.flush() ;  
        return matrixImage;
	}

	public static void main(String[] args) throws Exception {
		String logoPath="E:\\Git\\Dome\\WebRoot\\WEB-INF\\img\\logo.png";
		String text = "www.baidu.com"; // 二维码内容
		int width = 300; // 二维码图片宽度
		int height = 300; // 二维码图片高度
		String format = "gif";// 二维码的图片格式
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        // 内容所使用字符集编码  
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//      hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值  
//      hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值  
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数  
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,//要编码的内容  
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,  
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,  
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
				BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		File outputFile = new File("d:" + File.separator + "new.gif");
		Zxing.writeToFile(bitMatrix, format, outputFile,logoPath);
	}
}
