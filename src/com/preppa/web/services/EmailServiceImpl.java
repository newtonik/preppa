package com.preppa.web.services;


import com.preppa.web.entities.User;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.ClasspathResource;
import org.chenillekit.core.services.ConfigurationService;
import org.chenillekit.mail.services.SmtpService;
import org.chenillekit.template.services.TemplateService;
import org.chenillekit.template.services.Velocity;

/**
 *
 * @author nwt
 */
public class EmailServiceImpl implements EmailService {
    @Inject
    private SmtpService smtpService;
    private Configuration configuration;
    @Inject
    @Velocity
    private TemplateService _templateService;


    public EmailServiceImpl(SmtpService smtpService, ConfigurationService configurationService) {
        this.smtpService = smtpService;

        Resource configResource = new ClasspathResource("identity-server.properties");
        if(configResource == null) {
            System.out.println("Config Resource is null");
        }
        this.configuration = configurationService.getConfiguration(configResource);
    }

    @Override
    public void sendForgottenUsernameAndPassword(String emailTo, String password) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("utf8");
        String subject =   configuration.getString("email-forgotten-password-subject");
        email.setSubject(subject);
        email.addTo(emailTo);
        String emailFrom = configuration.getString("email-confirm-account-from");
        email.setFrom(emailFrom);
        String message = "Plain text message.";
        email.setMsg(message);

        smtpService.sendEmail(email);

    }
    
    @Override
    public void sendRegularEmail(SimpleEmail email) {
        if(email != null) {
            smtpService.sendEmail(email);
        }
        
    }

    @Override
    public void sendHTMLEmail(HtmlEmail email) throws EmailException {
        if(email != null) {
            smtpService.sendEmail(email);
        }
    }

    @Override
    public void sendSendRegistrationEmail(User user) throws EmailException {
        if(user != null) {
            HtmlEmail email = new HtmlEmail();
            email.addTo(user.getEmail());
            email.setFrom("email@preppa.com");
            email.setSubject("Welcome to Preppa" + user.getFirstName());
            //email.setTextMsg("Welcome " + user.getFirstName() + " to preppa!" + " Your username is " + user.getUsername());
            String activate = "<p> To activate account go to " + "http://test.preppa.com/user/activate/" + user.getActivationcode() + " </p>";
            String aHtml = "<html><body><h2> Welcome " + user.getFirstName() +" </h2> <p> Your username is " +  user.getUsername() + "</p> " + activate + " </body><html>";

            email.setHtmlMsg(aHtml);
            
            smtpService.sendEmail(email);
        }
    }

    @Override
    public void sendSendRegistrationCompleteEmail(User user) throws EmailException {
         if(user != null) {
            HtmlEmail email = new HtmlEmail();
            email.addTo(user.getEmail());
            email.setFrom("email@preppa.com");
            email.setSubject("Welcome to Preppa" + user.getFirstName());
            //email.setTextMsg("Welcome " + user.getFirstName() + " to preppa!" + " Your username is " + user.getUsername());
            String activate = "<p> Your registration is complete, go to " + "http://test.preppa.com/ to start prepping </p>";
            String aHtml = "<html><body><h2> Welcome " + user.getFirstName() +" </h2> <p> Your username is " +  user.getUsername() + "</p> " + activate + " </body><html>";

            email.setHtmlMsg(aHtml);

            smtpService.sendEmail(email);
        }
    }

    @Override
    public void sendPasswordResetEmail(User user) throws EmailException {
              if(user != null) {
            HtmlEmail email = new HtmlEmail();
            email.addTo(user.getEmail());
            email.setFrom("email@preppa.com");
            email.setSubject("Your Password has been reset");
            //email.setTextMsg("Welcome " + user.getFirstName() + " to preppa!" + " Your username is " + user.getUsername());
            String activate = "<p> Your password has been reset, follow this link to create a new one " + "http://test.preppa.com/user/password/activatenew/" + user.getPasswordResetCode() + " </p>";
            String warning = "<p> If you didn't not perform this request, send an email right away to email@preppa.com </p>";
            String aHtml = "<html><body><h3> Reset Password</h3> " + activate + warning + " </body><html>";

            email.setHtmlMsg(aHtml);

            smtpService.sendEmail(email);
        }
    }


}
