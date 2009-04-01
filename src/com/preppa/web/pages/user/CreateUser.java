/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.user;


import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

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
    private Session session;

    //Registry iocRegistry;
//
    //UserDAO userDAO = iocRegistry.getService(UserDAO.class);

    
    //private Timestamp currentTime;


    @CommitAfter
    Object onSuccess()
    {
    


        session.persist(user);
        //userDAO.doSave(user);
        return index;
    }

}
