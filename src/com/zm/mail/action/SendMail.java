package com.zm.mail.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * �ʼ����� �õ�����mail.jar��
 * 
 * @author zm
 */
public class SendMail {

	/**
	 * �ʼ�����
	 * 
	 * @param mailService
	 *            ���������������
	 * @param str_from
	 *            ����������
	 * @param strFrom_pwd
	 *            ����������
	 * @param str_to
	 *            �ռ���
	 * @param str_title
	 *            ����
	 * @param str_content
	 *            ����
	 * @return
	 */
	public boolean Send(String mailService, String str_from,
			String strFrom_pwd, String str_to, String str_title,
			String str_content) {
		try {
			// �����ʼ��Ự
			Properties props = new Properties(); // ������һ���ļ��д洢��-ֵ�Եģ����м���ֵ���õȺŷָ��ģ�
			/*
			 * �洢�����ʼ�����������Ϣ
			 * mail.debug ���ڡ��� �ǿ��Ե��� 
			 * mail.smtp.antu ��֤
			 * mail.smtp.host ������������˿�Ĭ�Ͽɲ�д
			 * mail.transport.protocol �������ӵ�Э��
			 */
			props.put("mail.smtp.host", mailService);// ͬʱͨ����֤
			/*
			 * ���������½�һ���ʼ��Ự���������Ϊfalse ����ʱ�ᱨ��553 relay check local fail
			 */
			props.put("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			Session s = Session.getInstance(props);
			// s.getDefaultInstance(props)

			MimeMessage message = new MimeMessage(s); // ���ʼ��Ự�½�һ����Ϣ����

			InternetAddress from = new InternetAddress(str_from, str_title); // ���÷����˵ĵ�ַ
			message.setFrom(from);

			// �����ʼ�������
			// InternetAddress to = new InternetAddress(str_to); //�����ռ���,���������������ΪTO(pukeyouxintest3@163.com)
			// message.setRecipient(Message.RecipientType.TO, to);
			// ����ʼ�������
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(str_to));
			// �±ߵķ�ʽ�������÷����˵��ǳ�
			// message.setRecipients(RecipientType.TO, InternetAddress.parse(MimeUtility.encodeText("�ſ�") + "<1004379401@qq.com>," + MimeUtility.encodeText("��") +"<951343502@qq.com>"));

			message.setSubject(str_title);// ���ñ���

			message.setText(str_content); // �����ı��ʼ�
			// message.setContent(new Object()); //���ͳ��ı�
			message.saveChanges();// �洢�ʼ���Ϣ

			Transport transport = s.getTransport("smtp");// �����ʼ�
			// ��smtp��ʽ��¼����,��һ�������Ƿ����ʼ��õ��ʼ�������SMTP��ַ,�ڶ��������Ƕ˿ںţ�����������Ϊ�û���,���ĸ�����Ϊ����
			transport.connect(mailService, 25, str_from, strFrom_pwd);
			// �����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
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
		System.out.println(s.Send("mail.sinobpo.com.cn",
				"min.zhang@sinobpo.com.cn", "1234%^&*", "<1004379401@qq.com>,<951343502@qq.com>", "�������⴦��", "����һ������"));
	}

}
