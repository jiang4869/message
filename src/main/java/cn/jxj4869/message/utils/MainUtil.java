package cn.jxj4869.message.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MainUtil {


    @Autowired
    JavaMailSenderImpl mailSender;


    public void sendReplyRemind(String email,String content){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("留言回复提醒");
        message.setText(content);
        message.setTo(email);
        message.setFrom("1121429190@qq.com");
        System.out.println(email);
        mailSender.send(message);
    }

    public  void sendCheckCodeEmail(String email,String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("验证码信息");
        message.setText(content);
        message.setTo(email);
        message.setFrom("1121429190@qq.com");
        System.out.println(email);
        mailSender.send(message);
    }
}
