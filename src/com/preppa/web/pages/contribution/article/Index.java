/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class Index {
    @Inject
    private ArticleDAO articleDAO;

    @Property
    @Persist
    private Article article;
  

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
