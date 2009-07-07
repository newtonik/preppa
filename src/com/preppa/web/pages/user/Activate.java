package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.User;
import com.preppa.web.services.EmailService;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.apache.commons.mail.EmailException;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;


/**
 *
 * @author nwt
 */
public class Activate {

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
            user = userDAO.findByActivationCode(code);

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
           if(!encpaswd.equals(user.getPassword())) {
               userform.recordError("Password Incorrect!");
           }

      }
    }
    @CommitAfter
    String onSuccess() {

        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setActivatedAt(now);
        user.setUpdatedAt(now);
        user.setRecentlyactivated(true);
        user.setActivationcode(null);
        user.setEnabled(true);

        userDAO.doSave(user);
       provider.setUserDetailsService(userserve);
       provider.setPasswordEncoder(new ShaPasswordEncoder());
       authtoken = new UsernamePasswordAuthenticationToken(fuser, fpass1);
       provider.setSaltSource(salt);
        try {
            emailservice.sendSendRegistrationCompleteEmail(user);
        } catch (EmailException ex) {
           logger.debug(ex.toString());
        }

       Authentication token = provider.authenticate(authtoken);

      if(token.isAuthenticated())
      {
          SecurityContextHolder.getContext().setAuthentication(token);

      }

        return "index";
    }
}
