package com.zm.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZIP {

	private static String encode = "utf-8";// "ISO-8859-1"

	public static void main(String[] args) {

		String json = "[{'fid':1,'linkObj':'','pastureunitid':'1525020107003','slugno':'45672D7373632D3030','userid':'admin','sortcode':1,'twodimensionno':'','linkObjId':'','version':0,'unid':'','createtime':{'nanos':0,'time':1419298864000,'minutes':41,'seconds':4,'hours':9,'month':11,'year':114,'timezoneOffset':-480,'day':2,'date':23},'id':1,'innercode':'','isnewobj':true,'systimestamp':null,'linkObjName':''},{'fid':1,'linkObj':'','pastureunitid':'1525020107003','slugno':'626A2D626A622D3031','userid':'admin','sortcode':1,'twodimensionno':'','linkObjId':'','version':0,'unid':'','createtime':{'nanos':0,'time':1419298864000,'minutes':41,'seconds':4,'hours':9,'month':11,'year':114,'timezoneOffset':-480,'day':2,'date':23},'id':2,'innercode':'','isnewobj':true,'systimestamp':null,'linkObjName':''},{'fid':1,'linkObj':'','pastureunitid':'1525020107003','slugno':'E20020194906013904','userid':'admin','sortcode':1,'twodimensionno':'','linkObjId':'','version':0,'unid':'','createtime':{'nanos':0,'time':1419298864000,'minutes':41,'seconds':4,'hours':9,'month':11,'year':114,'timezoneOffset':-480,'day':2,'date':23},'id':3,'innercode':'','isnewobj':true,'systimestamp':null,'linkObjName':''},{'fid':1,'linkObj':'','pastureunitid':'1525020107003','slugno':'E20020194906014304','userid':'admin','sortcode':1,'twodimensionno':'','linkObjId':'','version':0,'unid':'','createtime':{'nanos':0,'time':1419298864000,'minutes':41,'seconds':4,'hours':9,'month':11,'year':114,'timezoneOffset':-480,'day':2,'date':23},'id':4,'innercode':'','isnewobj':true,'systimestamp':null,'linkObjName':''},{'fid':1,'linkObj':'','pastureunitid':'1525020107003','slugno':'E20020194906015903','userid':'admin','sortcode':1,'twodimensionno':'','linkObjId':'','version':0,'unid':'','createtime':{'nanos':0,'time':1419298864000,'minutes':41,'seconds':4,'hours':9,'month':11,'year':114,'timezoneOffset':-480,'day':2,'date':23},'id':5,'innercode':'','isnewobj':true,'systimestamp':null,'linkObjName':''}]";
		System.out.println("----------------压缩---------------------");
		System.out.println(compressToByte(json).length);
		System.out.println("----------------减压缩---------------------");
		System.out.println(uncompressToString(compressToByte(json)).length());
	}

	/*
	 * 字符串压缩为字节数组
	 */
	public static byte[] compressToByte(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encode));
			gzip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	/*
	 * 字节数组解压缩后返回字符串
	 */
	public static String uncompressToString(byte[] b) {
		if (b == null || b.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);

		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	/*
	 * 字节数组解压缩后返回字符串
	 */
	public static String uncompressToString(byte[] b, String encoding) {
		if (b == null || b.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);

		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString(encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
