package com.preppa.web.pages.contribution.article.search;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;

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
 * Index of article search page, will display allArticles
 * @author nwt
 */
public class Index {

    @Property
    @Persist
    private String searchString;
    private List<Article> allArticles;
    @Property
    private Article article;
    @Inject
    private ArticleDAO articleDAO;
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
        String[] fields = new String[]{"title", "body", "taglist.name"};

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
        org.hibernate.Query hiQuery = fullTextSession.createFullTextQuery(query, Article.class);
        List<Article> results = hiQuery.list();

        this.allArticles = results;
        tx.commit();
    }

    String onPassivate()
    {
        return this.searchString;
    }

    public void setSearchResults(List<Article> results, String searcher)
    {
        System.out.println("There are " + results.size() + " results");
        this.searchString = searcher;

        allArticles = results;

    }
    public List<Article> getAllArticles() {

        return this.allArticles;
    }
}

