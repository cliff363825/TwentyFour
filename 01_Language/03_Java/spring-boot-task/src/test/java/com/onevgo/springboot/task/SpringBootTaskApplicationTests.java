package com.onevgo.springboot.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTaskApplicationTests {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test01() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7：30开会");
        message.setTo("176462329@qq.com");
        message.setFrom("fage@chujiax.com");
        mailSender.send(message);
    }

    @Test
    public void test02() throws MessagingException {
        // 1. 创建一个复杂的消息福建
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("通知-今晚开会");
        mimeMessageHelper.setText("<b>今晚7：30开会</b>", true);
        mimeMessageHelper.setTo("176462329@qq.com");
        mimeMessageHelper.setFrom("fage@chujiax.com");

        // 上传文件
        mimeMessageHelper.addAttachment("1.jpg", new File("doge.jpg"));
        mimeMessageHelper.addAttachment("2.jpg", new File("doge1.jpg"));
        mailSender.send(mimeMessage);
    }
}
