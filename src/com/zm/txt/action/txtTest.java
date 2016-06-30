package com.zm.txt.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * txt 读取写入
 * 
 * @author Administrator
 *
 */
public class txtTest {

	public static void main(String[] args) throws IOException {
		/*
		 * String filePath = "C:\\test.txt"; String str =
		 * readText(filePath,"utf-8"); System.out.println(str);
		 * System.out.println("将空格替换成你想要的标示符"+replaceKongge(str,";"));
		 * if(writeIntoText(filePath, replaceBlank(str))){
		 * System.out.println("写入成功"); }else{ System.out.println("写入失败"); }
		 */
		txtTest t = new txtTest();
		t.writeIntoText2(null);
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readText(String filePath, String encoding) {
		File file = new File(filePath);
		String line = "";
		String temp = "";
		List<String> lineList = new ArrayList<String>();
		try {
			if (file.exists() && file.isFile()) {
				InputStreamReader reader = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				System.out.println(bufferedReader);
				// 到行数不为就输出
				while ((line = bufferedReader.readLine()) != null) {
					lineList.add(line);
				}
			} else {
				System.out.println("找不到指定文件");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		temp = lineList.toString();
		return temp;
	}

	/**
	 * 写入文件
	 * 
	 * @param filePath
	 *            路径
	 * @param textForWriteInto
	 * @return
	 */
	public static boolean writeIntoText(String filePath, String textForWriteInto) {
		// 写文件之前创建文件
		try {
			File fileName = new File(filePath);
			if (!fileName.exists()) {
				fileName.createNewFile();
			}
			BufferedWriter bufferedWriter;
			bufferedWriter = new BufferedWriter(new FileWriter(fileName));
			bufferedWriter.write(textForWriteInto);
			bufferedWriter.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * txt写入
	 * @param textForWriteInto
	 * @throws IOException
	 */
	public void writeIntoText2(String textForWriteInto) throws IOException {
		String tableName = "MON_DAY_WATER";
		String jsonStr = "[{'PointCode':'HDSZ_TSZRC_CSY_PH_HOUR_AVG','PointValue':7.465001},{'PointCode':'HDSZ_TSZRC_CSY_RJY_HOUR_AVG','Point  Value':7.191},{'PointCode':'HDSZ_TSZRC_CSY_ZD_HOUR_AVG','PointValue':11.531},{'PointCode':'HDSZ_TSZRC_CSY_DDL_HOUR_AVG',  'PointValue':1129.5},{'PointCode':'HDSZ_TSZRC_CSY_SW_HOUR_AVG','PointValue':16.814},{'PointCode':'HDSZ_TSZRC_CSY_TOC_HOU  R_AVG','PointValue':0},{'PointCode':'HDSZ_TSZRC_CSY_TP_HOUR_AVG','PointValue':0},{'PointCode':'HDSZ_TSZRC_CSY_TN_HOUR_AV  G','PointValue':0},{'PointCode':'HDSZ_TSZRC_CSY_COD_HOUR_AVG','PointValue':1},{'PointCode':'HDSZ_TSZRC_CSY_AD_HOUR_AVG',  'PointValue':0.53}]";
		String pointTime = "2015/12/12 3:00:00";
		// 写文件之前创建文件
		try {
			String path = this.getClass().getResource("/").getPath();
			path = URLDecoder.decode(path, "utf-8");// 预防路径中空格乱码问题
			File folder = new File(path + "\\log");
			if (!folder.exists()) {
				folder.mkdirs(); // /如果不存在，创建目录
			}
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String date = fmt.format(new Date());
			// 判断每天是否有一个文本
			File file = new File(folder, date + ".txt");
			if (!file.exists()) { // 判断txt文本是否存在
				file.createNewFile(); // 新建txt文本
			}
			// 判断文件的大小
			InputStream fis = new FileInputStream(file);
			long s = fis.available();
			System.out.println(s);
			fis.close();

			// 在下一行追加内容
			FileOutputStream fo = new FileOutputStream(file, true);
			String str = tableName + " - 日期：" + pointTime + " - json:"
					+ jsonStr + "\r\n";
			fo.write(str.getBytes());
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		String end = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
			end = dest.replaceAll("=", "");
		}
		return end;
	}

	// 将字符串中的数字和字符分开,保留汉字
	public static String replaceNumber(String string) {
		String tempString = string.replaceAll("[0-9]", " ");
		return tempString;
	}

	// 将字符串中的数字留下，去除汉字
	public static String replaceHanzi(String string) {
		String tempString = string.replaceAll("[^0-9]", " ");
		return tempString;
	}

	/**
	 * 将字符串中的空格替换成自己想好的标识符号
	 * 
	 * @param string
	 * @param fuhao
	 * @return
	 */
	public static String replaceKongge(String string, String fuhao) {
		return string.replaceAll(" ", fuhao);
	}

	/**
	 * 多种后取系统路径的方式
	 * @throws IOException
	 */
	public void showURL() throws IOException {

		// 第一种：获取类加载的根路径 D:\git\daotie\daotie\target\classes
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);

		// 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录
		// D:\git\daotie\daotie\target\classes\my
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);

		// 第二种：获取项目路径 D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);

		// 第三种： file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);

		// 第四种： D:\git\daotie\daotie
		System.out.println(System.getProperty("user.dir"));
		/*
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName
		 * 获取当前工程路径
		 */

		// 第五种： 获取所有的类路径 包括jar包的路径
		System.out.println(System.getProperty("java.class.path"));

	}


}
