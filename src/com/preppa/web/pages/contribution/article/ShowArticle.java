/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowArticle {
@Persist
@Property
private Article article;
@Inject
private ArticleDAO articleDAO;


Object onActivate(int id) {
        this.article = articleDAO.findById(id);

        return this;
    }



void setarticle(Article article) {
        this.article = article;
    }

}
