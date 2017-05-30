package com.impetus.commons.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.impetus.commons.exception.ServiceException;
import com.impetus.domain.Request;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class MailToUser.
 */
public class MailToUser {

    /** The property. */
    private Properties property = new Properties();

    /** The property file name. */
    private String propertyFileName = "credentials.properties";

    /** The input stream. */
    private InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(propertyFileName);

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The message. */
    private Message message;

    /** The subject. */
    private String subject;

    /** The message body. */
    private String messageBody;

    /** The props. */
    private Properties props = new Properties();

    /** The mail session. */ 
    private Session mailSession;

    /** The hello email message. */
    private String helloEmailMessage;

    /** The thank you message. */
    private String thankYouMessage;

    
    /**
     * Shoot mail.
     * 
     * @param messageCode
     *            the message code
     * @param user
     *            the user
     * @param request
     *            the request
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    public void shootMail(String messageCode, Users user, Request request)
            throws IOException, ServiceException {
        String bookTitleForEmail;
        String userId = setProperty(user);
        helloEmailMessage = "Hello " + user.getUserDetails().getName() + ",\n";
        thankYouMessage = "\n\nThank you for choosing BooksMania.\n\n\n\n This email is computer generated, Kindly do not reply on this mail.";
        bookTitleForEmail = request.getBook().getTitle();
        try {
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userId));

            if (messageCode.equalsIgnoreCase("REGISTRATION")) {
                messageBody = helloEmailMessage
                        + "You are Successfully Registered on BooksMania.You have choosen \""
                        + user.getSubscription().getSubscriptionName()
                        + "\" Subscription and as per the subscription policy you are allowed to request for "
                        + user.getSubscription().getMaxBook() + " books."
                        + thankYouMessage;
                subject = "Successfully registered on BooksMania";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("USER_DELIEVERY_REQUEST")) {
                messageBody = helloEmailMessage
                        + "You request for Book \""
                        + request.getBook().getTitle()
                        + "\" has been recorded.Our team will get you back ASAP.";
                messageBody += thankYouMessage;
                subject = "DelieveryRequest for Book \"" + bookTitleForEmail
                        + "\"";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("ADMIN_DELIEVERY_CLOSE_REQUEST")) {
                messageBody = helloEmailMessage
                        + "Your request for Book \""
                        + request.getBook().getTitle()
                        + "\" has been recorded and book will be delievered to \""
                        + request.getDelieveryAddress()
                        + "\" within 1 day.\nThe Expected Return date for requested book is \""
                        + request.getExpectedReturnedDate() + "\"";
                messageBody += thankYouMessage;
                subject = "DelieveryRequest for Book \"" + bookTitleForEmail
                        + "\"";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("USER_DELIEVERY_CANCEL_REQUEST")) {
                messageBody = helloEmailMessage
                        + "You've cancel the request for Book \""
                        + request.getBook().getTitle()
                        + "\" has been recorded.The book has been cancelled";
                messageBody += thankYouMessage;
                subject = "Cancel Request for Book \"" + bookTitleForEmail
                        + "\"";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("USER_RETURN_CANCEL_REQUEST")) {
                messageBody = helloEmailMessage
                        + "You've Cancelled thr request for returning the book , \""
                        + request.getBook().getTitle()
                        + "\".\n The last date to return this book is:"
                        + request.getExpectedReturnedDate();
                messageBody += thankYouMessage;
                subject = "Cancel Return Request for Book \""
                        + bookTitleForEmail + "\"";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("USER_RETURN_REQUEST")) {
                messageBody = helloEmailMessage
                        + "You've registered a return request for book , \""
                        + request.getBook().getTitle()
                        + "\".We will get back to you ASAP.\n The last date to return this book is:"
                        + request.getExpectedReturnedDate();
                messageBody += thankYouMessage;
                subject = "Cancel Return Request for Book \""
                        + bookTitleForEmail + "\"";
                setSubjectAndMessageBody(messageBody, subject);
            }
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ServiceException(
                    "In MailToUser Exception occur in message body and subject setting:::",
                    e);
        }
    }

    /**
     * Shoot mail.
     * 
     * @param messageCode
     *            the message code
     * @param user
     *            the user
     * @param daysDuration
     *            the days duration
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public String shootMail(String messageCode, Users user, int daysDuration)
            throws IOException, ServiceException {
        String userId = setProperty(user);
        helloEmailMessage = "Hello " + user.getUserDetails().getName() + ",\n";
        thankYouMessage = "\n\nThank you for choosing BooksMania.\n\n\n\n This email is computer generated, Kindly do not reply on this mail.";
        try {
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userId));

            if (messageCode.equalsIgnoreCase("SUBSCRIPTION_ALERT")) {
                messageBody = helloEmailMessage
                        + "Your Subscription plan \""
                        + user.getSubscription().getSubscriptionName()
                        + "\" will be end in next "
                        + daysDuration
                        + " days.The expiry date of your subscription plan is:"
                        + user.getUserDetails().getSubscriptionEndDate()
                        + ". Please Renew you subscription PLan to utilise the services provided by us."
                        + thankYouMessage;
                subject = "Subscription Plan end Alert";
                setSubjectAndMessageBody(messageBody, subject);
            }

            if (messageCode.equalsIgnoreCase("SUBSCRIPTION_END")) {
                messageBody = "Hello "
                        + user.getUserDetails().getName()
                        + ",\nYour Subscription plan \""
                        + user.getSubscription().getSubscriptionName()
                        + "\"has been ended. Please Renew you subscription Plan from BOOKSMANIA to utilise the services provided by us."
                        + thankYouMessage;
                subject = "Subscription Plan ended";
                setSubjectAndMessageBody(messageBody, subject);
            }
            Transport.send(message);
            return "success";
        }

        catch (Exception e) {
            throw new ServiceException(
                    "Exception in MailToUser Class,Sending mail Failed to \""
                            + user.getEmail() + "\"", e);
        }
    }

    /**
     * Sets the property.
     * 
     * @param user
     *            the user
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private String setProperty(Users user) throws IOException {
        property.load(inputStream);
        String userId = user.getEmail();
        username = property.getProperty("username");
        password = property.getProperty("password");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        return userId;
    }

    /**
     * Sets the subject and message body.
     * 
     * @param messageBody
     *            the message body
     * @param subject
     *            the subject
     */
    public void setSubjectAndMessageBody(String messageBody, String subject)
            throws ServiceException {
        try {
            message.setSubject(subject);
            message.setText(messageBody);
        } catch (MessagingException e) {
            throw new ServiceException(
                    "In MailToUser Class Setting Message Body failed", e);
        }
    }

    public String shootMail(Users user) throws IOException, ServiceException {
        String userId = setProperty(user);
        helloEmailMessage = "Hello " + user.getUserDetails().getName() + ",\n";
        thankYouMessage = "\n\nThank you for choosing BooksMania.\n\n\n\n This email is computer generated, Kindly do not reply on this mail.";
        
        try {
            message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userId));
        
            messageBody=helloEmailMessage+"Your password is: \""+user.getUserPassword()+"\"."+thankYouMessage;
            subject = "Forget Password";
            setSubjectAndMessageBody(messageBody, subject);
            Transport.send(message);
            return "success";
        }
        catch (Exception e) {
           throw new ServiceException(
            "Exception in MailToUser Class,Sending mail Failed to \""
                    + user.getEmail() + "\"", e);
       }
    }
        
        
}

