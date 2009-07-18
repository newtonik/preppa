/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.topic;

import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
public class NewTopic {
    @Property
    private Topic topic;
    @Inject
    private TopicDAO topicDAO;
    @Property
    private String fName;
    @InjectPage
    private Index index;
     @Property
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Component
    private Form topicform;
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

    void onActivate() {
      topic = new Topic();

    }
    void onValidateForm() {

         topic.setName(fName);
         topic.setTestsubject(testsubject);

         if(topicDAO.findSizeByPartialName(fName, testsubject) > 0) {
             String error = "There is already a " + fName + " topic in " + testsubject.getName() + "." ;
             topicform.recordError(error);
         }
         else
         {
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

         topic.setCreatedAt(now);
         topic.setUpdatedAt(now);

            topicDAO.doSave(topic);
         }
    }
    @CommitAfter
    Object onSuccess() {
      
         return index;
    }

}
