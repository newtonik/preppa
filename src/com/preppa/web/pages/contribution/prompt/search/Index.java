  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt.search;

import com.preppa.web.data.PromptDAO;
import com.preppa.web.entities.Prompt;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
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
public class Index {

    @Property
    @Persist
    private String searchString;
    private List<Prompt> allPrompt;
    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private HibernateSessionManager sessionManager;
    private Session session;
    @Inject
    private Logger logger;

    void onActivate(String toSearch)
    {
        this.searchString = toSearch;
        session = sessionManager.getSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        //create Lucene Search query
        String[] fields = new String[]{"question", "quote", "topic", "taglist.name"};

        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,
                new StandardAnalyzer());
        Query query = null;
        try {
            //Note this is Lucene query
             query = parser.parse(searchString);
        } catch (ParseException ex) {
            logger.warn(ex.getMessage());
        }
        //wrap lucene query in Hibernate query
        org.hibernate.Query hiQuery = fullTextSession.createFullTextQuery(query, Prompt.class);
        List<Prompt> results = hiQuery.list();
        System.out.println("Results is" + results);
        this.allPrompt = results;
        tx.commit();
    }

    String onPassivate()
    {
        return this.searchString;
    }

    public void setSearchResults(List<Prompt> results, String searcher)
    {
        System.out.println("There are " + results.size() + " results");
        this.searchString = searcher;

        allPrompt = results;

    }
    public List<Prompt> getAllPrompt() {

        return this.allPrompt;
    }
}
