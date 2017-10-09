package com.zm.mac;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据ip获取mac地址
 * @author zhangmin
 *
 */
public class getMac {
	
	public static void main(String[] args) throws Exception {
		//System.out.println(getOsIp()+"对应网卡的MAC是:");
		System.out.println(getMACAddress("192.168.8.157"));
		HttpServletRequest request = null;
		/*System.out.println(getOsName());
		System.out.println(getMACAddressAll());*/
		System.out.println(getIpAddress(request));
		
	}
	/**
	 * 返回一个字节的十六进制字符串
	 * @param b
	 * @return
	 */
	private static String hexByte(byte b) {
		String s="000000"+Integer.toHexString(b);
	    return s.substring(s.length()-2);
	}
	
	/**
	 * 根据IP获取Mac地址]
	 * 	获取访问者IP通过如下代码
	 * 		 if (req.getHeader("x-forwarded-for") == null) {  
			       req.getRemoteAddr();
			       System.out.println(req.getRemoteAddr());
			    }  
			 req.getHeader("x-forwarded-for");  
			    System.out.println(req.getHeader("x-forwarded-for"));
	 * @param ip
	 * @return
	 */
	public static String getMACAddress(String ip){
		NetworkInterface ne;
		try {
			ne = NetworkInterface.getByInetAddress(InetAddress.getByName(ip));
			byte[]mac=ne.getHardwareAddress();
			String mac_s=hexByte(mac[0])+":"+hexByte(mac[1])+":"+hexByte(mac[2])+":"+hexByte(mac[3])+":"+hexByte(mac[4])+":"+hexByte(mac[5]);
			return mac_s;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 获取操作系统名称和IP
	 * @return
	 */
	public static String getOsIp(){
		 InetAddress ia=null;
	        try {
	            ia=ia.getLocalHost();
	            String localname=ia.getHostName();//系统名称
	            String localip=ia.getHostAddress();//IP
	            return localip;
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	     return null;
	}
	
	/**
	 * 获取本机器所有网卡的Mac
	 * @return
	 */
	public static String getMACAddressAll(){
		Enumeration<NetworkInterface> el;
		try {
			//首先获取到机器的所有网络接口
			el = NetworkInterface.getNetworkInterfaces();
			while(el.hasMoreElements())
			{
				NetworkInterface ni = el.nextElement();
				if(!ni.isLoopback() && ni.isUp() && !ni.isVirtual()){
					//网卡的物理mac地址二进制格式
					byte[] mac = ni.getHardwareAddress();
					String mac_s=hexByte(mac[0])+":"+
			                 hexByte(mac[1])+":"+ 
			                 hexByte(mac[2])+":"+
			                 hexByte(mac[3])+":"+ 
			                 hexByte(mac[4])+":"+
			                 hexByte(mac[5])
			                 ;
			        System.out.println("====="+mac_s);   
					
				}
			}//while
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/** 
     * 获取操作系统名称 
     */  
    public static String getOsName() {  
        String os = "";  
        os = System.getProperty("os.name");  
        return os;  
    }  
    
	public final static String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
}
