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

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;

/**
 *
 * @author newtonik
 */
public class ActivateNewPassword {
  private String acode;
    @Property
    private User user;
    @Inject
    private UserObDAO userDAO;
    @Property
    private String errorMessage;
    @Property
    private String fpass1;
    @Property
    private String fpass2;
    @Property
    private String fuser;
    @Component
    private Form userform;
    private DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    private UsernamePasswordAuthenticationToken authtoken;
    @Inject
    private UserDetailsService userserve;
    @Inject
    private PasswordEncoder encoder;
    @Inject
    private SaltSource salt;
    @Inject
    private EmailService emailservice;
    @Inject
    private Logger logger;


    void onActivate(String code) {
        if(code != null)
        {
            acode = code;
            user = userDAO.findByPasswordResetCode(code);

            if(user == null) {
                errorMessage = "The user does not exist";
            }
            else
            {
                fuser = user.getUsername();
            }
        }
    }
    String onPassivate() {
        return acode;
    }


    void onValidateForm() {

      if(!fpass1.equals(fpass2)) {
            fpass1 = null;
            fpass2 = null;

            userform.recordError("The passwords don't match");

        }
      else
      {
           ShaPasswordEncoder enc = new ShaPasswordEncoder();
           Object salter = salt.getSalt(user);
           String encpaswd = enc.encodePassword(fpass1, salter);
           user.setPassword(encpaswd);

      }
    }
    @CommitAfter
    String onSuccess() {

        
        user.setEnabled(true);
        user.setPasswordResetCode(null);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setUpdatedAt(now);
        userDAO.doSave(user);
   

        return "loginpage";
    }
}
