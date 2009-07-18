/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.testsubject;

import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.pages.Index;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@Secured("ROLE_USER")
public class NewTestsubject {
    @Property
    private Testsubject testsubject;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @InjectPage
    private Index index;

    void onActivate(Testsubject testsubject) {
        this.testsubject = testsubject;
    }

    Object onPassivate() {
        return testsubject;
    }

    @CommitAfter
    Object onSuccess() {


         testsubject.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         testsubject.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         //article.setUser(userDAO.findById(1));

         testsubjectDAO.doSave(testsubject);
         return index;
    }


}
