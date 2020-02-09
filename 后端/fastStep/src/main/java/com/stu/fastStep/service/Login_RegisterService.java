package com.stu.fastStep.service;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;
import com.stu.fastStep.dao.UserDao;
import com.stu.fastStep.domain.User;
import com.sun.mail.util.MailSSLSocketFactory;


@Service(value="Login_Register")
public class Login_RegisterService {
	@Resource(name="UserDao")
	private UserDao userDao;
	
	public JSONObject login(String number,String password)  throws Exception{
		User user=userDao.getUserByNumber(number);
		if(user==null)	throw new LogicException(501,"δ�ҵ����û�");
		else if(!user.getPassword().equals(password))	throw new LogicException(502,"�����������");
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}

	public JSONObject register(String number,String password)  throws Exception{
		if(number==null || number.equals(""))	throw new LogicException(502,"���ݸ�ʽ����");
		if(password==null || password.equals(""))	throw new LogicException(502,"���ݸ�ʽ����");
		User testUser=userDao.getUserByNumber(number);
		if(testUser!=null)		throw new LogicException(501,"ע��ʧ��,���˺��Ѵ���");
		
		User user = new User();
		user.setPassword(password);
		user.setNumber(number);
		user=userDao.insertUser(user);
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
	
	public void sendMail(String mail,HttpServletRequest request) throws Exception {
		Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// ����QQ�ʼ�������
        prop.setProperty("mail.transport.protocol", "smtp"); // �ʼ�����Э��
        prop.setProperty("mail.smtp.auth", "true"); // ��Ҫ��֤�û�������
        // ����QQ���䣬��Ҫ����SSL���ܣ��������´��뼴��
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //ʹ��JavaMail�����ʼ���5������
        //������������Ӧ�ó�������Ļ�����Ϣ�� Session ����
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //�������ʼ��û�������Ȩ��
                return new PasswordAuthentication("1099640098@qq.com", "jyqkmkvpacqljccf");
            }
        });
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();
        //3��ʹ��������û�������Ȩ�������ʼ�������
        ts.connect("smtp.qq.com", "1099640098@qq.com", "jyqkmkvpacqljccf");
        //4�������ʼ�
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress("1099640098@qq.com"));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        //�ʼ��ı���
        message.setSubject("fastStep��֤���ʼ�");
        //�ʼ����ı�����
        String code=String.valueOf((int)(Math.random()*9000+1000));
        request.getSession().setAttribute("loginCode", code);
        System.out.println("code:"+code);
        message.setContent("���ã�������ע��fastStep��������֤���ǣ�"+code+",��л����ʹ�ã�", "text/html;charset=UTF-8");
        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
	}
}
