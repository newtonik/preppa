  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components;

import java.util.List;
import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.pages.contribution.article.ShowArticle;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ArticleSearch {
    @Property
    private String aName;
	@Component(id = "articlesearch")
	private Form myform;
    @Inject
    private ArticleDAO articleDAO;
	@InjectPage
	private ShowArticle _next;

	Object onSuccessFromArticleSearch() {
		_next.set(aName);
		return _next;
	}

    List<String> onProvideCompletionsFromAName(String partial)
    {
        List<Article> matches = articleDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();

            for (Article a : matches)
            {
                if (!result.contains(a.getTitle())) {
                    result.add(a.getTitle());
                }
            }

        return result;
    }
}
