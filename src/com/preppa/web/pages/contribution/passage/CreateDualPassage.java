/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.Passage;
import com.preppa.web.entities.Testsubject;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author nwt
 */
public class CreateDualPassage {
 @Property
    private LongDualPassage longDualpassage;

    @Inject
    private LongDualPassageDAO longDualpassageDAO;
    @Inject
    private PassageDAO passageDAO;
    @Component(parameters = {"value=fbodyone"})
    private Editor passeditorone;
     @Component(parameters = {"value=fbodytwo"})
    private Editor passeditortwo;
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
    private String fTitle2;
    @Property
    private String fBodyone;
    @Property
    private String fBodytwo;
    @Property
    private String fSource;
    @Property
    private String fTag;




    void onActivate() {
        this.longDualpassage = new LongDualPassage();
    }

    Object onPassivate() {
        return this;
    }

    @CommitAfter
    Object onSuccess() {
        Passage p = new Passage();
        Passage p2 = new Passage();
        p.setPassage(fBodyone);
        p.setTitle(fTitle);
        p2.setPassage(fBodytwo);
        p2.setTitle(fTitle2);


         Timestamp now = new Timestamp(System.currentTimeMillis());
         p.setCreatedAt(now);
         p.setUpdatedAt(now);
         p2.setCreatedAt(now);
         p2.setUpdatedAt(now);

         //passageDAO.doSave(p);
         //passageDAO.doSave(p2);
         longDualpassage.setPassageone(p);
         longDualpassage.setPassagetwo(p2);
         longDualpassage.setSource(fSource);
         longDualpassage.setTags(fTag);


         now = new java.sql.Timestamp(System.currentTimeMillis());

         longDualpassage.setCreatedAt(now);
         longDualpassage.setUpdatedAt(now);



         longDualpassageDAO.doSave(longDualpassage);
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


}
