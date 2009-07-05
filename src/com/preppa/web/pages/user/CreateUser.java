/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.Gender;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.Role;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import com.preppa.web.services.EmailService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import org.apache.commons.mail.EmailException;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.chenillekit.tapestry.core.components.DateSelector;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;

/**
 *
 * @author newtonik
 */
public class CreateUser {

    
    private User user;
    @Property
    private User auser;
    @InjectPage
    private Index index;
    @Inject
    private UserObDAO userDAO;
    @Inject
    private Messages messages;
    @Property
    private String fLogin;
    @Property
    private String ffName;
    @Property
    private String flName;
    @Property
    private Gender fgender;
    @Property
    private Date fdob;
    @Property
    private String femail;
    @Persist
    @Property
    private String fpass1;
    @Persist
    @Property
    private String fpass2;
    @Inject
    private SaltSource salt;
    @Inject
    private HibernateSessionManager sessionManager;
    @Inject
    private EmailService emailer;
    

    @Component(parameters = {"value=fdob", "firstYear=1930"})
    private DateSelector datefield;
    @Component
    private PasswordField passwordField;
    @Component
    private Form userform;
    @Property
    private Boolean passKap;


    //private Timestamp currentTime;
    void onActivate() {
        this.auser = new User();
    }

    Object onPassivate() {
        return auser;
    }
    
    void onValidateForm() {
    
      if(!fpass1.equals(fpass2)) {
            fpass1 = null;
            fpass2 = null;

            userform.recordError(passwordField, messages.get("passwords-dont-match"));

        }
      if(!passKap) {
          userform.recordError("failed kaptcha, try again");
      }
    }
   
    @CommitAfter
    @Log
    Object onSuccess() throws EmailException {
        //user = new User();

        auser.setPassword(fpass1);
        auser.setLoginId(fLogin);
        auser.setUsername(fLogin);
        auser.setEmail(femail);
        auser.setDob(fdob);
        auser.setLastName(flName);
        auser.setFirstName(ffName);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        auser.setCreatedAt(now);
        auser.setUpdatedAt(now);
        userDAO.doSave(auser);
      
        RegisterUser(auser);
        //session.persist(user);
       emailer.sendSendRegistrationEmail(auser);

        this.user = auser;
        return "loginpage";
    }

   public void RegisterUser(User user) {
        if( user != null)
        {
           // userDAO.doSave(user);
            Object salter = salt.getSalt(auser);
            //String passwordToencode = user.getPassword() + user.getUsername();
             ShaPasswordEncoder enc = new ShaPasswordEncoder();
             System.out.println(salter);
            String encodpassword = enc.encodePassword(user.getPassword(), salter);
            System.out.println(encodpassword);
            user.setPassword(encodpassword);

            //find a the default role, create it if it doesn't exist
            Session session = sessionManager.getSession();
            Role r = (Role) session.createCriteria(Role.class).add(
                    Restrictions.eq("authority", "ROLE_USER")).uniqueResult();

            if (r == null)
            {
//                logger.debug("role not found, creating");

                r = new Role();

                r.setAuthority("ROLE_USER");

                session.saveOrUpdate(r);
            }

            auser.setRoles(new HashSet<Role>());

            auser.getRoles().add(r);

            //session.saveOrUpdate(u);
            userDAO.doSave(auser);
        }

        //logger.debug("returning user " + user.getUsername());

        //sessionManager.commit();




    }

}
