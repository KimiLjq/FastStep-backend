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
		if(user==null)	throw new LogicException(501,"未找到该用户");
		else if(!user.getPassword().equals(password))	throw new LogicException(502,"输入密码错误");
		
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}

	public JSONObject register(String number,String password)  throws Exception{
		if(number==null || number.equals(""))	throw new LogicException(502,"数据格式错误");
		if(password==null || password.equals(""))	throw new LogicException(502,"数据格式错误");
		User testUser=userDao.getUserByNumber(number);
		if(testUser!=null)		throw new LogicException(501,"注册失败,该账号已存在");
		
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
        prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //使用JavaMail发送邮件的5个步骤
        //创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("1099640098@qq.com", "jyqkmkvpacqljccf");
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com", "1099640098@qq.com", "jyqkmkvpacqljccf");
        //4、创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("1099640098@qq.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        //邮件的标题
        message.setSubject("fastStep验证码邮件");
        //邮件的文本内容
        String code=String.valueOf((int)(Math.random()*9000+1000));
        request.getSession().setAttribute("loginCode", code);
        System.out.println("code:"+code);
        message.setContent("您好，您正在注册fastStep，您的验证码是："+code+",感谢您的使用！", "text/html;charset=UTF-8");
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
	}
}
