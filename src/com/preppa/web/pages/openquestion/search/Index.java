package com.preppa.web.pages.openquestion.search;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
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
 * @author nwt
 */
public class Index {

    @Property
    @Persist
    private String searchString;
    @Property
    private List<OpenQuestion> allquestions;
    @Property
    private OpenQuestion question;
    @Inject
    private OpenQuestionDAO openquestionDAO;
    @Inject
    private HibernateSessionManager sessionManager;
    private Session session;
    @Inject
    private Logger logger;
    //link to get search tags.
    @Property
    private String slink;

    void onActivate(String toSearch) {
        slink = "openquestion/search";
        this.searchString = toSearch;
        session = sessionManager.getSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        //create Lucene Search query
        String[] fields = new String[]{"title", "question", "taglist.name", "answers.answer"};

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
        org.hibernate.Query hiQuery = fullTextSession.createFullTextQuery(query, OpenQuestion.class);
        List<OpenQuestion> results = hiQuery.list();

        this.allquestions = results;
        tx.commit();
    }

    String onPassivate() {
        return this.searchString;
    }

    public void setSearchResults(List<OpenQuestion> results, String searcher) {
        System.out.println("There are " + results.size() + " results");
        this.searchString = searcher;

        allquestions = results;

    }

    public void setSearchPageString(String searchterm) {
        if(searchterm != null)
            search(searchterm);
    }

    void search(String toSearch) {
        this.searchString = toSearch;
        session = sessionManager.getSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        //create Lucene Search query
        String[] fields = new String[]{"title", "question", "taglist.name", "answers.answer"};

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
        org.hibernate.Query hiQuery = fullTextSession.createFullTextQuery(query, OpenQuestion.class);
        List<OpenQuestion> results = hiQuery.list();

        this.allquestions = results;
        tx.commit();
    }
}
