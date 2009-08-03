  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import java.util.Calendar;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class FeaturedArticle {

    private int index = 1;
    final private static int bodysize = 2000;

    @Inject
    private ArticleDAO articleDAO;
    @Property
    private Article article = articleDAO.findById(index);

    
    void FeaturedArticle() {
        Calendar calendar = Calendar.getInstance();
        index = calendar.get(Calendar.DAY_OF_MONTH);
    }

    void onActivate() {
        this.index = 1;
        article = articleDAO.findById(index);
        System.out.println(article.getBody());
    }

    public String getBodyParse() {
        String returnVal = article.getBody();
        if (bodysize > returnVal.length()) {
            return returnVal;
        }
        else {
            return returnVal.substring(0, bodysize);
        }
    }

}
