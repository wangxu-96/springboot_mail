package com.imooc.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;
    @Test
    public void sayHelloTest(){
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("18375310856@163.com","one test spring boot","spring_boot_first_mail");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+
                "<body>\n"+"<h3> hello world </h3>\n"+"</body>"+
                "</html>";
        mailService.sendHtmlMail("18375310856@163.com","html mail spring boot",content);
    }
}
