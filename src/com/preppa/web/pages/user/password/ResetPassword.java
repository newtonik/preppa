/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.pages.user.password;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.User;
import com.preppa.web.services.EmailService;
import java.sql.Timestamp;
import org.apache.commons.mail.EmailException;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;

/**
 *
 * @author newtonik
 */
public class ResetPassword {
@Property
private User user;
@Inject
private UserObDAO userDAO;
@Property
private String femail;
@Component
private Form userform;
@Persist
private Integer tries;
@Inject
private SaltSource salt;
@Inject
private EmailService mailer;
@Inject
private Logger logger;


void onActivate() {

}

void onPassviate() {
    
}
@AfterRender
void setDefault() {
    tries = 0;
}
void onValidateForm() {

    this.user = userDAO.findByEmail(femail);
    if(user == null) {
        userform.recordError("Email address not found. Please submit the email address you used for registering your account.");
    }
    tries++;
    if(tries > 5) {
        userform.recordError("You cannot attempt to reset your password, you've been locked out!");
    }
}
@CommitAfter
void onSuccess()
{

    ShaPasswordEncoder enc = new ShaPasswordEncoder();
    Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
       Object salter = salt.getSalt(user);
      String basiccode = now.toString() + user.getUsername() + user.getLastName();
      String resetcode = enc.encodePassword(basiccode, salter);
      user.setPasswordResetCode(resetcode);
      user.setRecentlyreset(true);
      userDAO.doSave(user);
        try {
            mailer.sendPasswordResetEmail(user);
        } catch (EmailException ex) {
           logger.debug(ex.toString());
        }

}
}
