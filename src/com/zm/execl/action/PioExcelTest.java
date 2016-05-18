package com.zm.execl.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zm.execl.model.XlsDto;


/**
 * pio读写excel
 * 用到的jar报 放到lib-pio 下
 * @author zm
 */
public class PioExcelTest {

	public static void main(String[] args) {
		try {
			// 读取excel2003-2007
			//new PioTest().readXlsExcel();
			List<XlsDto> list =new PioExcelTest().readExcel();
			//导出
			CreateExcel(list);
			//zipFile("E:/测试.xls");
			//删除文件
			File f =new File("E:/测试.xls");
			dropFolderOrFile(f);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * excel导出功能
	 * @param list
	 * @throws Exception
	 */
	public static void CreateExcel(List<XlsDto> list) throws Exception{
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("测试一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
    	// 0时间 1骆驼桥 2黄杨桥 3广源桥 4 吴家桥
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("时间");  
        cell.setCellStyle(style);  
        cell = row.createCell(1);  
        cell.setCellValue("骆驼桥 ");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("黄杨桥");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("广源桥");  
        cell.setCellStyle(style);  
        cell = row.createCell(4); 
        cell.setCellValue("吴家桥");  
        cell.setCellStyle(style);  
        //循环把数据写入到excel
        for (int i = 0; i < list.size(); i++) {
	    	row = sheet.createRow((int) i + 1); 
	    	XlsDto xls = list.get(i);
	    	row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(xls.getTime()));  
	    	row.createCell(1).setCellValue(xls.getLtq());  
	    	row.createCell(2).setCellValue(xls.getHyq());  
	    	row.createCell(3).setCellValue(xls.getGyq());  
	    	row.createCell(4).setCellValue(xls.getWjq());  
		}
        //将文件放到指定位置
        FileOutputStream out = new FileOutputStream("E:/测试.xls");  
        wb.write(out);
        out.close();  
        System.out.println("导出成功！");
	}
	 
	
	/**
	 * 根据文件后缀名的格式  实现不同的对象 读取
	 * 读取excel内容 版本--excel 2003 - 2013
	 * double 转换为字符串
	 * DecimalFormat df = new DecimalFormat("0"); 
	 * df.format(hyq.getNumericCellValue())
	 * @throws Exception
	 */
	public List<XlsDto> readExcel() throws Exception {
		//实际运用中需要动态获取文件的后缀名
		String str = "xlsx";
		// 加载一个excel       2010excel测试.xlsx
		InputStream is = new FileInputStream("WebRoot\\WEB-INF\\excel\\2010excel测试.xlsx");
		// 创建一个excel 文件
		Workbook wb =null;
		if (str.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (str.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			System.out.println("您输入的excel格式不正确");
		}
		// 定义一个集合接收数据
		List<XlsDto> list = new ArrayList<XlsDto>();
		XlsDto xlsDto = null;
		// 循环工作表
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = (Sheet) wb.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
				Row row= sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				xlsDto = new XlsDto();
				// 循环列Cell 根据下标取出买一列的值
				// 0时间 1骆驼桥 2黄杨桥 3广源桥 4 吴家桥
				Cell time = row.getCell(0);
				if (time == null) {
					continue;
				}
				xlsDto.setTime(time.getDateCellValue());
				Cell ltq = row.getCell(1);
				if (ltq == null) {
					continue;
				}
			
				xlsDto.setLtq(ltq.getNumericCellValue());
				Cell hyq = row.getCell(2);
				if (hyq != null) {
					xlsDto.setHyq(hyq.getNumericCellValue());
				}

				Cell gyq = row.getCell(3);
				if (gyq == null) {
					continue;
				}
				xlsDto.setGyq(gyq.getNumericCellValue());
				Cell wjq = row.getCell(3);
				if (wjq == null) {
					continue;
				}
				xlsDto.setWjq(wjq.getNumericCellValue());

				list.add(xlsDto);
			}
		}
/*		System.out.println("list:" + list.size());
		for (XlsDto xlsdto : list) {
			System.out.println("时间:" + xlsdto.getTime() + "------------1:"
					+ xlsdto.getHyq());
		}
		*/
		return list;
	}
	/** 
     * 删除文件夹 
     * @param folder 
     */  
    private static void dropFolderOrFile(File file) {  
        if (file.isDirectory()) {  
            File[] fs = file.listFiles();  
            for (File f : fs) {  
                dropFolderOrFile(f);  
            }  
        }  
        file.delete();  
    }  

	/**
	 * 读取excel的内容 版本 excel2003-2007
	 */
	public void readXlsExcel() throws Exception {
		// 加载一个excel
		InputStream is = new FileInputStream("WebRoot\\WEB-INF\\excel\\测试11.xls");
		// 创建一个excel 文件
		Workbook wb = new HSSFWorkbook(is);

		// 定义一个集合接收数据
		List<XlsDto> list = new ArrayList<XlsDto>();
		XlsDto xlsDto = null;
		// 循环工作表
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			HSSFSheet hssfSheet = (HSSFSheet) wb.getSheetAt(i);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				xlsDto = new XlsDto();
				// 循环列Cell 根据下标取出买一列的值
				// 0时间 1骆驼桥 2黄杨桥 3广源桥 4 吴家桥
				HSSFCell time = hssfRow.getCell(0);
				if (time == null) {
					continue;
				}
				xlsDto.setTime(time.getDateCellValue());
				HSSFCell ltq = hssfRow.getCell(1);
				if (ltq == null) {
					continue;
				}
				xlsDto.setLtq(ltq.getNumericCellValue());
				HSSFCell hyq = hssfRow.getCell(2);
				if (hyq == null) {
					continue;
				}
				xlsDto.setHyq(hyq.getNumericCellValue());

				HSSFCell gyq = hssfRow.getCell(3);
				if (gyq == null) {
					continue;
				}
				xlsDto.setGyq(gyq.getNumericCellValue());
				HSSFCell wjq = hssfRow.getCell(3);
				if (wjq == null) {
					continue;
				}
				xlsDto.setWjq(wjq.getNumericCellValue());

				list.add(xlsDto);
			}
		}

		System.out.println("list:" + list.size());
		for (XlsDto xlsdto : list) {
			System.out.println("时间:" + xlsdto.getTime() + "------------1:"
					+ xlsdto.getHyq());
		}
	}
	
	/** 
     * 将指定文件夹打包成zip 
     * @param folder 
	 * @throws Exception 
     * @throws IOException  
     */  
    private static void zipFile(String folder) throws Exception{  
        File zipFile = new File(folder + ".zip");  
        if (zipFile.exists()) {  
            zipFile.delete();  
        }  
        ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(zipFile));  
        File dir = new File(folder);  
        File[] fs = dir.listFiles();  
        byte[] buf = null;  
        if(fs!=null){  
            for (File f : fs) {  
                zipout.putNextEntry(new ZipEntry(f.getName()));  
                FileInputStream fileInputStream = new FileInputStream(f);  
                buf = new byte[2048];  
                BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);  
                int len;  
                while ((len = origin.read(buf,0,2048))!=-1) {  
                    zipout.write(buf,0,len);  
                }  
                zipout.flush();  
                origin.close();  
                fileInputStream.close();   
            }  
        }  
        zipout.flush();  
        zipout.close();  
    }  
}
