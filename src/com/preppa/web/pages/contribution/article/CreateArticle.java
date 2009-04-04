/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class CreateArticle {

    @Property
    private Article article;
    @InjectPage
    private ShowArticle showarticle;
    @Inject
    private ArticleDAO articleDAO;

    void onActivate(Article article) {
        this.article = article;
    }

    Object onPassivate() {
        return article;
    }

    @CommitAfter
    Object onSuccess() {

         article.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         article.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         //article.setUser(userDAO.findById(1));

         articleDAO.doSave(article);
         return showarticle;
    }
}
