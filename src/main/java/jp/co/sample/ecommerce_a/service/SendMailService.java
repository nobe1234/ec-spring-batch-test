package jp.co.sample.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * メールを送信する操作を行うクラス.
 * 
 * @author yume.hirata
 *
 */
@Component
public class SendMailService{
	 
    @Autowired
    public JavaMailSender emailSender;
 
    /**
     * 引数を元にメールを作成・送信する.
     * 
     * @param to	宛先メールアドレス
     * @param subject	メールタイトル
     * @param text	メール内容
     */
    public void sendMail(
      String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage(); 

        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        
     // FIXME:インデントがおかしい
        try {
        emailSender.send(message);
        }catch (MailException e) {
        	e.printStackTrace();
		}
    }

}