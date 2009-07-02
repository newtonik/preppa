  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewArticle {

@Property
private Article cursor;
@Inject
private ArticleDAO articleDAO;

private String input;

private final static int bodysize = 100;

    Object onActivate(String input) {
        this.input = input;
        return null;
    }

    public String getsamplebody() {
        String returnVal = cursor.getBody();

        if (bodysize < returnVal.length()) {
            returnVal = returnVal.substring(0, bodysize);
        }

        return returnVal;
    }

    public List<Article> getArticleList() {
        List<Article> returnVal;

        returnVal = articleDAO.findAll();

        return returnVal;
    }

}
