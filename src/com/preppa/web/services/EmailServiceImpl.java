package com.preppa.web.services;


import com.preppa.web.entities.User;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
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
            email.setTextMsg("Welcome " + user.getFirstName() + " to preppa!" + " Your username is " + user.getUsername());

            String aHtml = "<html><body><h2> Welcome " + user.getFirstName() +" </h2> Your username is " +  user.getUsername() + " </body><html>";
            email.setHtmlMsg(aHtml);
            smtpService.sendEmail(email);
        }
    }


}
