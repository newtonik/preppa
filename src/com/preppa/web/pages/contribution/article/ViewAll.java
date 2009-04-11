/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Accordion;

/**
 *
 * @author newtonik
 */
public class ViewAll {
    @Inject
    private ArticleDAO articleDAO;

    @Property
    @Persist
    private Article article;
    @Component(parameters = {"subjects=article.title", "details=article.body"})
    private Accordion accordion1;


    Object onActivate() {
     
        return null;
    }

    /**
     * @return the allarticles
     */
    public List<Article> getAllarticles() {
        return articleDAO.findAll();
    }

}
