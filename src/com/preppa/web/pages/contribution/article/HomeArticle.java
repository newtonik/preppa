  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.entities.Article;
import java.util.List;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author Jan
 */
public class HomeArticle {

    @Inject
    private HibernateSessionManager sessionManager;
    private Session session;
    void onActivate()
    {
        session = sessionManager.getSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        List<Article> articles = session.createQuery("from Article as article").list();

        for(Article a: articles)
        {
            fullTextSession.index(a);
        }

        tx.commit();
        //session.close();
    }
}
