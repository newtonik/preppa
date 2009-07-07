package com.preppa.web.services;

import com.preppa.web.entities.User;
import org.apache.commons.mail.EmailException; 
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author nwt
 */
public interface EmailService {
 
    public void sendForgottenUsernameAndPassword(String emailTo, String password) throws EmailException;
     public void sendRegularEmail(SimpleEmail email) throws EmailException;
     public void sendHTMLEmail(HtmlEmail email) throws EmailException;
     public void sendSendRegistrationEmail(User user) throws EmailException;
        public void sendSendRegistrationCompleteEmail(User user) throws EmailException;
        public void sendPasswordResetEmail(User user) throws EmailException;
}
