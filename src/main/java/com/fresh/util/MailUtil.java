package com.fresh.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送的工具类
 * Create by ygh on 2018/3/17
 */
public class MailUtil {
    //发件人
    public static String myEmailAccount = "1502841998@qq.com";
    //授权码
    public static String myEmailPassword = "siardbengabtidib";


    /**
     *
     * @param receiveMailAccount 收件人邮箱
     * @param text
     * @throws Exception
     */
    public static void sendMail(String receiveMailAccount,  String text) throws Exception {

        Properties prop = System.getProperties();

        //开启身份验证
        prop.put("mail.smtp.auth", "true");
        //设置邮件服务器主机
        prop.put("mail.host", "smtp.qq.com");
        //发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        //SSL认证
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        //根据配置创建会话，用于和邮件服务器交互
        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);//设置为debug,可以查看详细的log

        //根据session获取邮件传输对象
        Transport transport = session.getTransport();

        //创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, text);

        transport.connect(myEmailAccount, myEmailPassword);

        transport.sendMessage(message, message.getAllRecipients());

        //关闭连接
        transport.close();

    }

    private static MimeMessage createMimeMessage(Session session, String myEmailAccount, String receiveMailAccount, String text)
            throws UnsupportedEncodingException, MessagingException {
        //1.创建一封邮件
        MimeMessage message = new MimeMessage(session);

        //2.From:发件人
        message.setFrom(new InternetAddress(myEmailAccount, "Hello", "UTF-8"));

        //3.To:收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount,
                "XXX", "UTF-8"));

        //4.Subject:邮件主题
        message.setSubject("激活您的账号","UTF-8");

        //5.Content:邮件正文(使用HTML标签)
        message.setContent(text, "text/html;charset=UTF-8");

        //6.设置发送时间
        message.setSentDate(new Date());

        //7.保存设置
        message.saveChanges();

        return message;
    }


}
