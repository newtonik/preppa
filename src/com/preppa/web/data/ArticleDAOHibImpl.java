/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Article;
import org.slf4j.Logger;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import java.util.List;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;


/**
 *
 * @author newtonik
 */
public class ArticleDAOHibImpl  extends AbstractHibernateDAO<Article, Integer> implements ArticleDAO {

    @Inject
    private HibernateSessionManager sessionManger;
    @Inject
    private AuditReader reader;
    
    public ArticleDAOHibImpl(Logger logger, Session session)
    {
        
        super(logger, session);
        
    }
    @Override
    public Article findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Article articles");
        if(id != null)
        {
             sqlString.addWhereClause("articles.id = '" + id + "'");
        }

        List<Article> returnVal = findByQuery(sqlString.toString());

        if (returnVal.isEmpty()) {
            return null;
        }
        else {
            return returnVal.get(0);
        }

    }

    @Override
    public Article findByTitle(String title) {
        SQLString sqlString = new SQLString("FROM Article articles");
        if(title != null)
        {
             sqlString.addWhereClause("articles.title = '" + title + "'");
        }

        List<Article> returnVal = findByQuery(sqlString.toString());

        if (returnVal.isEmpty()) {
            return null;
        }
        else {
            return returnVal.get(0);
        }

    }

    @Override
    public List<Article>  findBytestsubject_id(Integer id) {
        SQLString sqlString = new SQLString("FROM Article articles");
        if(id != null)
        {
            sqlString.addWhereClause("articles.testsubject = '" + id + "'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Article> findByPartialName(String partialName) {
        SQLString sqlString = new SQLString("FROM Article a");
        if(partialName != null)
        {
             sqlString.addWhereClause("a.title LIKE '" + partialName + "%'");
        }

        return (List <Article>) findByQuery(sqlString.toString());
    }

    @Override
    public List<Article> findAllArticleRevisions(Integer articleId) {
        reader = AuditReaderFactory.get(sessionManger.getSession());

        return null;
    }

    @Override
    public Article findArticleByRevision(Integer articleId, Integer revisionId) {
        //Get Session
        

        
        reader = AuditReaderFactory.get(sessionManger.getSession());

        Article result = reader.find(Article.class, articleId, revisionId);

        return result;
    }

}
