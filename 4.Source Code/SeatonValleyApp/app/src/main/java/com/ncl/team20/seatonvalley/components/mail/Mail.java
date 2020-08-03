package com.ncl.team20.seatonvalley.components.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.Log;

/**
 * @author Stelios Ioannou,Olivija Guzelyte
 * @since 20/02/2018
 * Last edited : 22/03/2018 by Stelios Ioannou
 * <p>
 **/

//Class used to create a Mail Object.Creates the Send Mail object and the Verification Mail Object.
//Used Gmail as an SMPT provider,and used a 2-pass verification then generated an app password for this application
//in order to avoid suspicious activities when other devices from various places.

class Mail {

    //Mail fieldss
    private final String fromEmail;
    private final String fromPassword;
    private final String toEmail;
    private final String emailSubject;
    private final String emailBody;
    //Mail Properties
    private final Properties emailProperties;
    //Mail Session
    private Session mailSession;

    //Mail message for the send message.
    private MimeMessage emailMessage;
    //Mail message for the verification message.
    private MimeMessage verificationMessage;

    //Mail Constructor
    public Mail(String fromEmail, String fromPassword,
                String toEmail, String emailSubject, String emailBody) {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmail = toEmail;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        emailProperties = System.getProperties();
        String emailPort = "587";
        emailProperties.put("mail.smtp.port", emailPort);
        String smtpAuth = "true";
        emailProperties.put("mail.smtp.auth", smtpAuth);
        String starttls = "true";
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        String auth = "true";
        emailProperties.put("mail.smtp.auth", auth);
        Log.i("Mail", "Mail server properties set.");
    }

    //Creates both eMailMessage and Verification Message.
    public void createEmailMessage() throws
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");
        verificationMessage = new MimeMessage(mailSession);
        verificationMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        verificationMessage.setSubject("Seaton Valley Council: Query Successfully submitted.");
        verificationMessage.setContent("Your query has been successfully submitted. We will review it as soon as possible.\n" +
                "<br>The Seaton Valley Community Council . \n" + "  \n", "text/html");
    }

    //Sends the mail and the verification mail.
    public void sendEmail() throws MessagingException {
        Transport transport = mailSession.getTransport("smtp");
        String emailHost = "smtp.gmail.com";
        transport.connect(emailHost, fromEmail, fromPassword);
        transport.sendMessage(emailMessage, new InternetAddress[]{new InternetAddress(fromEmail)});
        transport.sendMessage(verificationMessage, new InternetAddress[]{new InternetAddress(toEmail)});
        transport.close();
    }

}