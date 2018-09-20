package com.imooc.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;
    @Resource
    TemplateEngine templateEngine;
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

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        mailService.sendAttachmentsMail("18375310856@163.com","file  spring boot","spring_boot_first_mail","/Users/ouakira/go/src/learninggo/src/job/Pointer.go");
    }

    @Test
    public void sendInlineResourceMailTest() throws MessagingException {
        String id="001";
        String filePath="/Users/ouakira/Downloads/Collection.jpg";
        String content="<html><body><img src=\'cid:"+id+"\'></img></body></html>";
        mailService.sendInlineResourceMail("18375310856@163.com","picture  spring boot",content,filePath,id);
    }
    @Test
    public void testTemplatesMailTest() throws MessagingException {
        Context context=new Context();
        context.setVariable("id","007");
        String emailContent=templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("18375310856@163.com","html mail spring boot",emailContent);
    }


}
