package com.imooc.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sayHello(){
        System.out.println("Hello World!");
    }

    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
    }

    public void sendHtmlMail(String to,String subject,String content) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        helper.setFrom(from);
        mailSender.send(message);
    }

    public void sendAttachmentsMail(String to,String subject,String content,String filePath) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        FileSystemResource file =new FileSystemResource(new File(filePath));
        String fileName=file.getFilename();

        helper.addAttachment(fileName,file);
        mailSender.send(message);
    }

    public void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId)  {

        logger.info("send starting....");
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            helper.setFrom(from);
            FileSystemResource file=new FileSystemResource(new File(rscPath));

            helper.addInline(rscId,file);
            logger.info("send successful");
        } catch (MessagingException e) {
            logger.error("send failed....");
        }
        mailSender.send(message);

    }
}
