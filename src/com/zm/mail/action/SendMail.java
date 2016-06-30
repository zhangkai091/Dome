package com.zm.mail.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送 用到的是mail.jar包
 * 
 * @author zm
 */
public class SendMail {

	/**
	 * 邮件发送
	 * 
	 * @param mailService
	 *            发件人邮箱服务器
	 * @param str_from
	 *            发件人邮箱
	 * @param strFrom_pwd
	 *            发件人密码
	 * @param str_to
	 *            收件人
	 * @param str_title
	 *            标题
	 * @param str_content
	 *            内容
	 * @return
	 */
	public boolean Send(String mailService, String str_from,
			String strFrom_pwd, String str_to, String str_title,
			String str_content) {
		try {
			// 建立邮件会话
			Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
			/*
			 * 存储发送邮件服务器的信息
			 * mail.debug 等于‘’ 是可以调试 
			 * mail.smtp.antu 认证
			 * mail.smtp.host 邮箱服务器，端口默认可不写
			 * mail.transport.protocol 设置连接的协议
			 */
			props.put("mail.smtp.host", mailService);// 同时通过验证
			/*
			 * 根据属性新建一个邮件会话，如果设置为false 发送时会报错553 relay check local fail
			 */
			props.put("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			Session s = Session.getInstance(props);
			// s.getDefaultInstance(props)

			MimeMessage message = new MimeMessage(s); // 由邮件会话新建一个消息对象

			InternetAddress from = new InternetAddress(str_from, str_title); // 设置发件人的地址
			message.setFrom(from);

			// 单个邮件接收人
			// InternetAddress to = new InternetAddress(str_to); //设置收件人,并设置其接收类型为TO(pukeyouxintest3@163.com)
			// message.setRecipient(Message.RecipientType.TO, to);
			// 多个邮件接收人
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(str_to));
			// 下边的方式可以设置发送人的昵称
			// message.setRecipients(RecipientType.TO, InternetAddress.parse(MimeUtility.encodeText("张楷") + "<1004379401@qq.com>," + MimeUtility.encodeText("王") +"<951343502@qq.com>"));

			message.setSubject(str_title);// 设置标题

			message.setText(str_content); // 发送文本邮件
			// message.setContent(new Object()); //发送超文本
			message.saveChanges();// 存储邮件信息

			Transport transport = s.getTransport("smtp");// 发送邮件
			// 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数是端口号；第三个参数为用户名,第四个参数为密码
			transport.connect(mailService, 25, str_from, strFrom_pwd);
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		SendMail s = new SendMail();
		
		String mailService = ""; // 发件人邮箱服务器
		String str_from=""; //发件人邮箱
		String strFrom_pwd="";// 发件人密码 
		String str_to = "";// 收件人  写法例子"<123566@qq.com>,<sdas123@qq.com>"
		String str_title="";//  邮件标题
		String str_content=""; //邮件内容
		System.out.println(s.Send(mailService,str_from,strFrom_pwd, str_to, str_title, str_content));
	}

}
