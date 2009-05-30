/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.pages.contribution.article.ShowArticle;
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
    private Article article;
    @InjectPage
    private ShowArticle showarticle;
    @Inject
    private ArticleDAO articleDAO;
    @Component(parameters = {"value=article.body"})
    private Editor body;
    private int size;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;


    void onActivate(Article article) {
        this.article = article;
    }

    Object onPassivate() {
        return article;
    }

    @CommitAfter
    Object onSuccess() {
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         article.setCreatedAt(now);
         article.setUpdatedAt(now);
         article.setBody(sanitize(article.getBody()));
         if(article.getBody().length() < 100 )
             size = article.getBody().length();
         else
             size = 100;


         article.setTeaser(article.getBody().substring(0, size));
         //article.setUser(userDAO.findById(1));

         articleDAO.doSave(article);
        //showarticle.setarticle(article);
         return showarticle;
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
