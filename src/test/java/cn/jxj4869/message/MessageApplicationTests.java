//package cn.jxj4869.message;
//
//import cn.jxj4869.message.utils.Util;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.io.File;
//
//@SpringBootTest
//class MessageApplicationTests {
//
//    @Autowired
//    JavaMailSenderImpl mailSender;
//
//
//
//
//
//    @Test
//    public void test(){
//        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/img/avatars";
//        projectPath.replace("\\","\\\\");
//        File file=new File(projectPath);
//
//        String[] list = file.list();
//        int idx = Util.getRandomNumber(0, list.length-1);
//        for (String s : list) {
//            System.out.println(s);
//        }
//        System.out.println(file.isDirectory());
//        System.out.println(file.exists());
//        String avatars="/img/avatars/"+list[idx];
//        System.out.println(avatars);
//        System.out.println(projectPath);
//    }
//
//}
