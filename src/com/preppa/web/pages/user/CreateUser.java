/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.Gender;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.UserOb;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.DateSelector;

/**
 *
 * @author newtonik
 */
public class CreateUser {

    @Property
    private UserOb user;
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
    @Property
    @Persist
    private String fpass1;
    @Property
    @Persist
    private String fpass2;
    
    @Component(parameters = {"value=fdob", "firstYear=1930"})
    private DateSelector datefield;
    @Component(id="fpass1")
    private PasswordField passwordField;
    @Component(id="userform")
    private Form userform;
    //private Timestamp currentTime;
    void onActivate(UserOb user) {
        this.user = user;
    }

    Object onPassivate() {
        return user;
    }

//    void onValidate() {
//        System.out.println(fpass1);
//        System.out.println(fpass2);
//
//        if(!fpass1.equals(fpass2)) {
//            fpass1 = null;
//            fpass2 = null;
//
//            userform.recordError(passwordField, messages.get("passwords-dont-match"));
//        }
//    }
    @CommitAfter
    Object onSuccess() {
        user = new UserOb();
        user.setLoginId(fLogin);
        user.setEmail(femail);
        user.setDob(fdob);
        user.setLastName(flName);
        user.setFirstName(ffName);
        user.setPassword(fpass1);
        user.setPassword_confirmation(fpass2);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        userDAO.doSave(user);
        //session.persist(user);
        return index;
    }

}
