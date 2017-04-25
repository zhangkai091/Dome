package com.zm.cvs.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CVSTest {

	public static void main(String[] args) {
		//writerCVS();
		readCvs();
	}
	
	public static void readCvs(){
		try {
			File inFile = new File("D://out.csv"); // 读取的CSV文件
			//解决中文乱码问题
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile),"GBK"));
			String line;
			while(reader.readLine()!=null && reader.readLine().length()>0){
				line =reader.readLine();
				String str = CVSUtil.readLine(line);
				System.out.println(str);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出cvs
	 */
	public static void writerCVS(){
		try {
			File outFile = new File("D://out.csv");
			//解决中文乱码问题
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(outFile),"GBK");
			//生成文件
			BufferedWriter writer = new BufferedWriter(writerStream);
			String[] str = new String[3];
			str[0] = "序号";
			str[1] = "名称";
			str[2] = "数值";
			String cvsStr1 = CVSUtil.toCSVLine(str);
			writer.write(cvsStr1);//写入文件
			for (int i = 0; i < 100000; i++) {
				writer.newLine();//换行
				String[] strArray = new String[3];
				strArray[0] = "ID" + i;
				strArray[1] = "中文" + i;
				strArray[2] = "22" + i;
				String cvsStr = CVSUtil.toCSVLine(strArray);
				writer.write(cvsStr);//写入文件
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
