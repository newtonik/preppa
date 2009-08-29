  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components;

import com.preppa.web.data.PromptDAO;
import com.preppa.web.entities.Prompt;
import com.preppa.web.pages.contribution.prompt.ShowPrompt;
import com.preppa.web.pages.contribution.prompt.search.Index;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
/**
 *
 * @author Jan Jan
 */
public class PromptSearch {
    @Property
    private String aName;
    @Parameter
    private String searcher;
    @Component(id = "promptsearch")
    private Form myform;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private HibernateSessionManager sessionManager;
    private Session session;
    @InjectPage
    private ShowPrompt _next;
    @Inject
    private Logger logger;

    @InjectPage
    private Index articleResults;
//        Object onSuccessFromArticleSearch() {
//		_next.set(aName);
//		return _next;
//	}


@SetupRender
void setSearchString()
{
         if(this.searcher == null)
         {
             aName = "";
         }
         else
         {
             aName = searcher;
         }
 }
     Object onSuccessFromArticleSearch() {

         session = sessionManager.getSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        //create Lucene Search query
        String[] fields = new String[]{"title", "body", "article.taglist"};

        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,
                new StandardAnalyzer());
        Query query = null;
        try {
            //Note this is Lucene query
             query = parser.parse(aName);
        } catch (ParseException ex) {
            logger.warn(ex.getMessage());
        }
        //wrap lucene query in Hibernate query
        org.hibernate.Query hiQuery = fullTextSession.createFullTextQuery(query, Prompt.class);
        List<Prompt> results = hiQuery.list();

        tx.commit();

        System.out.println("There are " + results.size() + " results");
        articleResults.setSearchResults(results, aName);
        return articleResults;
     }

    List<String> onProvideCompletionsFromAName(String partial)
    {
        List<Prompt> matches = promptDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();

            for (Prompt p : matches)
            {
                if (!result.contains(p.getQuestion())) {
                    result.add(p.getQuestion());
                }
            }

        return result;
    }
}
