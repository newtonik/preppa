/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
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
public class EditArticle {

    @Persist
    @Property
    private Article article;
    @Inject
    private ArticleDAO articleDAO;
    @Component(parameters = {"value=article.body"})
    private Editor body;
    @InjectPage
    private ShowArticle showarticle;

    private int size;

    Object onActivate(int id) {
        this.article = articleDAO.findById(id);

        return this;
    }
        @CommitAfter
    Object onSuccess() {

         article.setBody(sanitize(article.getBody()));
         if(article.getBody().length() < 100 )
             size = article.getBody().length();
         else
             size = 100;


         article.setTeaser(article.getBody().substring(0, size));
      
        
         //set the updated at tag to current time
         article.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

         articleDAO.doSave(article);
         showarticle.setarticle(article);
         return showarticle;
    }

           public static String sanitize(String string) {
    return string
     .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
     .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
     .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

}
