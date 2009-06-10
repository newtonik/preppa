/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Testsubject;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
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
    @Inject
    private PassageDAO passageDAO;
    @Component(parameters = {"value=fbody"})
    private Editor passeditor;
    private int size;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Property
    private String fTitle;
    @Property
    private String fBody;
    @Property
    private String fSource;
    @Property
    private String fTag;
    @InjectPage
    private ShowPassage showpassage;

    

    void onActivate() {
        this.longpassage = new LongPassage();
    }

  
    @CommitAfter
    Object onSuccess() {
        
        
        
        
        // passageDAO.doSave(p);
         longpassage.setPassage(fBody);
         longpassage.setSources(fSource);
         longpassage.setTags(fTag);
         longpassage.setTitle(fTitle);


         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

         longpassage.setCreatedAt(now);
         longpassage.setUpdatedAt(now);


      
         longpassageDAO.doSave(longpassage);
         showpassage.setPassagePage(longpassage);
         return showpassage;
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



}
