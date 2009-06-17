/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.UserOb;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

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

    //private Timestamp currentTime;
    void onActivate(UserOb user) {
        this.user = user;
    }

    Object onPassivate() {
        return user;
    }

    void onValidate() {
     
    }
    @CommitAfter
    Object onSuccess() {


        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        userDAO.doSave(user);
        //session.persist(user);
        return index;
    }
}
