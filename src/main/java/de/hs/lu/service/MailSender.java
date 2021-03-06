package de.hs.lu.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

	public static void sendMail(String to, String from, String text)
   {    
      String host = "localhost";
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);

      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("eHotel Nachricht");
         message.setText(text);
         Transport.send(message);
         logger.info("Message erfolgreich verschickt");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}