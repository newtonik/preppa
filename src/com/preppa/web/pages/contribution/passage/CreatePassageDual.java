/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Passage;
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
public class CreatePassageDual {

    @Property
    private Passage passage;
    /*@InjectPage
    private ShowArticle showarticle;*/
    @Inject
    private PassageDAO passageDAO;
    @Component(parameters = {"value=article.body"})
    private Editor body;
    private int size;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;


    void onActivate(Passage passage) {
        this.passage = passage;
    }

    Object onPassivate() {
        return passage;
    }

    @CommitAfter
    Object onSuccess() {
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         passage.setCreatedAt(now);
         passage.setUpdatedAt(now);
         passage.setPassage(sanitize(passage.getPassage()));
         if(passage.getPassage().length() < 100 )
             size = passage.getPassage().length();
         else
             size = 100;


         /*Come back to this */
         //passage.setTeaser(passage.getPassage().substring(0, size));
         //article.setUser(userDAO.findById(1));

         passageDAO.doSave(passage);
         /*Come back to this*/
         //showarticle.setarticle(article);
         //return showarticle;
         return null;
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
