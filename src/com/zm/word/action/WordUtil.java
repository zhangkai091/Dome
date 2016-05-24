package com.zm.word.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * word操作工具类 生成word文件 用到的是freemarker-2.3.23的jar包；
 * word 另存xml 时选word XML 文档，不要选其他的
 * 
 * @author zm
 */
public class WordUtil {
	/**
	 * 生成word文件 用到的是freemarker-2.3.23的jar包
	 * 
	 * @param dataMap
	 *            word中需要展示的动态数据，用map集合来保存
	 * @param templateName
	 *            word模板名称，例如：test.ftl
	 * @param filePath
	 *            文件生成的目标路径，例如：D:/wordFile/
	 * @param fileName
	 *            生成的文件名称，例如：test.doc
	 */
	public void createWord(Map dataMap, String templateName, String filePath,
			String fileName) {
		try {
			// 创建配置实例
			Configuration configuration = new Configuration();
			// 设置编码
			configuration.setDefaultEncoding("UTF-8");
			// ftl模板文件统一放至 com.lun.template 包下面
			configuration.setClassForTemplateLoading(WordUtil.class,
					"/com/zm/document/template");
			// 获取模板
			Template template = configuration.getTemplate(templateName);
			// 输出文件
			File outFile = new File(filePath + File.separator + fileName);
			// 如果输出目标文件夹不存在，则创建
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));

			// 生成文件
			template.process(dataMap, out);
			// 关闭流
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * word 读取
	 * 
	 * @param filepath
	 * @return
	 */
	public boolean readWord(String filepath) {

		return true;
	}

	private String filePath; // 文件路径
	private String fileName; // 文件名称
	private String fileOnlyName; // 文件唯一名称

	/**
	 * 模板必须放到这个包下面/com/havenliu/document/template word测试
	 * 
	 * @param args
	 *            注意 不要用eclipse或myeclipse快捷格式化 那样导出的文件会提示以损坏
	 * 
	 */
	public static void main(String[] args) {
		WordUtil w = new WordUtil();
		String str = w.createWord2();
		System.out.println(str);
	}

	/**
	 * word 数据封装
	 * 
	 * @return
	 */
	public String createWord2() {
		/** 用于组装word页面需要的数据 */
		Map<String, Object> dataMap = new HashMap<String, Object>();

		/** 组装数据 */
		dataMap.put("pumpname", "张三");
		dataMap.put("pumptept1", "11111");
		dataMap.put("pumptept2", "2222");
		dataMap.put("pumptept3", "3333");
		dataMap.put("pumptept4", "4444");

		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		for (int i = 1; i <= 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "标题" + i);
			map.put("content", "内容" + (i * 2));
			map.put("author", "作者" + (i * 3));
			map.put("price", "价格" + (i * 4));
			newsList.add(map);
		}
		dataMap.put("books", newsList);

		/** 文件名称，唯一字符串 */
		Random r = new Random();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		StringBuffer sb = new StringBuffer();
		sb.append(sdf1.format(new Date()));
		sb.append("_");
		sb.append(r.nextInt(100));

		// 文件路径
		filePath = "F:\\test";

		// 文件唯一名称
		fileOnlyName = "用freemarker导出的Word文档_" + sb + ".doc";
		/** 生成word */
		WordUtil w = new WordUtil();
		w.createWord(dataMap, "test.xml", filePath, fileOnlyName);

		return "createWordSuccess";
	}
}
