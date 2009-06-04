/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Testsubject;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.BeanSelect;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class CreatePassage {

    @Property
    private LongPassage longpassage;
   
    @Inject
    private LongPassageDAO longpassageDAO;
    @Component(parameters = {"value=body"})
    private Editor passeditor;
    @Property
    private String body;
    private int size;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;

    

    void onActivate(LongPassage passage) {
        this.longpassage = passage;
    }

    Object onPassivate() {
        return longpassage;
    }

    @CommitAfter
    Object onSuccess() {
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         longpassage.setCreatedAt(now);
         longpassage.setUpdatedAt(now);
         //longpassage.setUser(userDAO.findById(1));

         longpassageDAO.doSave(longpassage);
        //showlongpassage.setlongpassage(longpassage);
         return this;
    }
    public static String sanitize(String string) {
    return string
     .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
     .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
     .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

        /**
     * @return the testsubjects
     */
    public List<Testsubject> getTestsubjects() {
        this.setTestsubjects(testsubjectDAO.findAll());
        return testsubjects;
    }

    /**
     * @param testsubjects the testsubjects to set
     */
    public void setTestsubjects(List<Testsubject> testsubjects) {
        this.testsubjects = testsubjects;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }


}
