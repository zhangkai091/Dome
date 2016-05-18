package com.zm.xml.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;

/**
 * dom4j xml解析 用到的jar 包dom4j-1.6.1.jar
 * 
 * @author zm
 */
public class Dom4jXml {

	public static void main(String[] args) {
		try {
			String xmlStr = "<?xml version='1.0' encoding='utf-8'?><bookstore><book category='children'><title lang='en'>Harry Potter</title><author>J K. Rowling</author><year>2005</year><price>29.99</price></book><book category='cooking'><title lang='en'>Everyday Italian</title><author>Giada De Laurentiis</author><year>2005</year><price>30.00</price></book><book category='web'><title lang='en'>Learning XML</title><author>Erik T. Ray</author><year>2003</year><price>39.95</price></book><book category='web'><title lang='en'>XQuery Kick Start</title><author>James McGovern</author><author>Per Bothner</author><author>Kurt Cagle</author><author>James Linn</author><author>Vaidyanathan Nagarajan</author><year>2003</year><price>49.99</price></book></bookstore>";
			readStringXml(xmlStr); // xml 字符串解析
			readStringXmlMap(xmlStr); // xml 字符串转Map
			String file = "WebRoot\\WEB-INF\\xml\\books.xml";
			List list = readXML(file); // 根据路径解析xml
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				System.out.println(m.get("title"));
				System.out.println(m.get("author"));
				System.out.println("------------------");
				writeXML(m);// xml写入
			}
			// updateRoot(); 根据父节点属性更新当前节点
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * xml字符串简析
	 * 
	 * @param xmlStr
	 */
	public static void readStringXml(String xmlStr) {

		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element rootElt = doc.getRootElement(); // 获取根节点
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Element normal = rootElt.element("bookstore"); // normal解析
			Iterator iter = rootElt.elementIterator("book"); // 获取根节点下的子节点book
			System.out.println("解析xml字符串");
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("title"); // 拿到book节点下的子节点title值
				String year = recordEle.elementTextTrim("year");
				System.out.println("title:" + title);
				System.out.println("year:" + year);
			}
			System.out.println("------------------------");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将xml字符串转换成map
	 * 
	 * @param xmlStr
	 */
	public static void readStringXmlMap(String xmlStr) {
		Map<String, Map> maplst = new HashMap<String, Map>();
		Map map = new HashMap();
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element rootElt = doc.getRootElement(); // 获取根节点
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

			Element normal = rootElt.element("bookstore"); // normal解析
			Iterator iter = rootElt.elementIterator("book"); // 获取根节点下的子节点book
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("title"); // 拿到book节点下的子节点title值
				String year = recordEle.elementTextTrim("year");
				String author = recordEle.elementTextTrim("author");
				String price = recordEle.elementTextTrim("price");
				// 赋值到Map
				map.put("title", title);
				map.put("year", year);
				map.put("author", author);
				map.put("price", price);
				maplst.put(title, map); // 赋值到Map集合
			}
			// 将xml字符串转换成Map
			System.out.println("将xml字符串转换成Map");
			System.out.println(maplst.toString());
			System.out.println("---------------------");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据父节点属性更新当前节点
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public static void updateRoot(String fileName) throws Exception {
		// 读取文件
		File inputXML = new File(fileName);
		// 使用SAXReader解析xml
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputXML);
		Element orders = document.getRootElement();
		// 根据父节点属性值 查出对应节点
		Element element = parse(orders, "category", "children");
		// 获取当前节点下的所有子节点，判断其值，以进行修改
		String title = element.element("title").getText();
		String author = element.element("author").getText();
		String year = element.element("year").getText();
		String price = element.element("price").getText();
		// 修改节点名称
		// element.element("title").setName("SAFDASFD");
		// 修改属性
		element.element("title").setText("AAAAAAAAAA");
		// 可以给xml 节点添加属性
		// orders.setAttributeValue("title", "safdasfda");
		// System.out.println(document.asXML());
		Writer writer = new FileWriter(inputXML);
		OutputFormat format = OutputFormat.createPrettyPrint();// 格式化
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		xmlWriter.write(document);
		xmlWriter.close();
	}

	/**
	 * 根据父节点属性取出当前父节点所有信息
	 * 
	 * @throws Exception
	 */
	public static void readRoot(String fileName) throws Exception {
		// 读取文件
		File inputXML = new File(fileName);
		// 使用SAXReader解析xml
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputXML);
		Element orders = document.getRootElement();
		Element element = parse(orders, "category", "children");
		Element title = element.element("title");
		Element author = element.element("author");
		Element year = element.element("year");
		Element price = element.element("price");
		System.out.println(title.getText());
		System.out.println(author.getText());
		System.out.println(year.getText());
		System.out.println(price.getText());

	}

	/*
	 * 获得X属性结果是X值的整个标签
	 */
	public static Element parse(Element node, String type, String val) {
		for (Iterator iter = node.elementIterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Attribute name = element.attribute(type);
			if (name != null) {
				String value = name.getValue();
				if (value != null && val.equals(value))
					return element;
				else
					parse(element, type, val);
			}
		}
		return null;
	}

	/**
	 * xml 写入
	 * 
	 * @param map
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void writeXML(Map map) throws Exception {
		String filename="e:/orderList.xml";
		if (map != null) {// 判断map是否为空
			File inputXML = new File(filename);
			if (!inputXML.exists()) { // 判断文件是否存在
				inputXML.createNewFile();
				String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?><bookstore></bookstore>";
				BufferedWriter bufferedWriter;
				bufferedWriter = new BufferedWriter(new FileWriter(filename));
				bufferedWriter.write(str); //为空报的文件写入标识
				bufferedWriter.close();
			}
			
			SAXReader saxReader = new SAXReader(); // 使用 SAXReader 解析 XML 
			Document document = saxReader.read(inputXML);
			System.out.println("ddd" + document.getRootElement());
			Element orders = document.getRootElement();// 根节点

			Element order = orders.addElement("book");// 书名
			order.addAttribute("category", map.get("category").toString()); // 给父节点添加属性

			Element merchantId = order.addElement("title");// 标题
			merchantId.setText(map.get("title").toString());

			Element transType = order.addElement("author");// 作者
			transType.setText(map.get("author").toString());
			// 判断数据
			// transType.setText(map.get("author") == null ?
			// "00":map.get("author").toString());

			Element merchantOrderId = order.addElement("year");// 出书年份
			merchantOrderId.setText(map.get("year").toString());

			Element merchantOrderTime = order.addElement("price");// 价格
			merchantOrderTime.setText(map.get("price").toString());

			Writer writer = new FileWriter(inputXML);
			OutputFormat format = OutputFormat.createPrettyPrint();// 格式化
			XMLWriter xmlWriter = new XMLWriter(writer, format);
			xmlWriter.write(document);
			xmlWriter.close();
		}
	}

	/**
	 * 读取xml文件 放入map，
	 * 
	 * @param fileName
	 *            xml 文件路径
	 * @return
	 * @throws DocumentException
	 */
	private static List readXML(String fileName) throws DocumentException {
		List orderList = new ArrayList();
		// 读取文件
		File inputXML = new File(fileName);
		// 使用SAXReader解析xml
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputXML);
		// document.getXMLEncoding() 获取xml编码格式
		// System.out.println(document.getXMLEncoding());
		System.out.println("xml转字符串");
		System.out.println(document.asXML());
		System.out.println("------------------------------");
		System.out.println("xml的绝对路径" + document.getName());
		Element orders = document.getRootElement();
		for (Iterator i = orders.elementIterator(); i.hasNext();) {
			Element order = (Element) i.next();
			// 获取每个节点的数据
			List list = order.attributes();// 获取每个父节点的属性
			DefaultAttribute e = (DefaultAttribute) list.get(0);
			System.out.println("父节点属性" + e.getName() + ":" + e.getText());
			Element title = order.element("title");
			Element author = order.element("author");
			Element year = order.element("year");
			Element price = order.element("price");
			Map map = new HashMap();
			map.put(e.getName(), e.getText());
			map.put("title", title.getText());
			map.put("author", author.getText());
			map.put("year", year.getText());
			map.put("price", price.getText());
			orderList.add(map);
		}

		System.out.println("根据路径解析xml并封装到Map");
		for (int i = 0; i < orderList.size(); i++) {
			Map m = (Map) orderList.get(i);
			System.out.println(m.get("title"));
			System.out.println(m.get("author"));
		}
		System.out.println("------------------");
		return orderList;
	}
}
