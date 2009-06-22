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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
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
import org.chenillekit.tapestry.core.components.DateSelector;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;

/**
 *
 * @author newtonik
 */
public class CreateUser {

    @Property
    private User user;
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
    private PasswordEncoder encoder;
    @Inject
    private SaltSource salt;
    @Inject
    private HibernateSessionManager sessionManager;

    @Component(parameters = {"value=fdob", "firstYear=1930"})
    private DateSelector datefield;
    @Component(id="fpass1")
    private PasswordField passwordField;
    @Component
    private Form userform;
    //private Timestamp currentTime;
    void onActivate() {
        this.user = new User();
    }

    Object onPassivate() {
        return user;
    }
    
    void onValidateForm() {
    
           if(!fpass1.equals(fpass2)) {
            fpass1 = null;
            fpass2 = null;

            userform.recordError(passwordField, messages.get("passwords-dont-match"));

        }
    }
   
    @CommitAfter
    @Log
    Object onSuccess() {
        //user = new User();
        user.setPassword(fpass1);
        user.setLoginId(fLogin);
        user.setUsername(fLogin);
        user.setEmail(femail);
        user.setDob(fdob);
        user.setLastName(flName);
        user.setFirstName(ffName);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        //userDAO.doSave(user);
        RegisterUser(user);
        //session.persist(user);
        return index;
    }

   public void RegisterUser(User user) {
        if( user != null)
        {
            userDAO.doSave(user);
            Object salter = salt.getSalt(user);
            String passwordToencode = user.getPassword() + user.getUsername() + user.getId();
            String encodpassword = encoder.encodePassword(passwordToencode, salter);
            System.out.println(encodpassword);
            user.setPassword(encodpassword);
            userDAO.doSave(user);
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


            user.setRoles(new HashSet<Role>());

            user.getRoles().add(r);

            //session.saveOrUpdate(u);
            userDAO.doSave(user);
        }

        //logger.debug("returning user " + user.getUsername());

        //sessionManager.commit();




    }

}
