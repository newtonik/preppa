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

/**
 *
 * @author newtonik
 */
public class ArticleDAOHibImpl  extends AbstractHibernateDAO<Article, Integer> implements ArticleDAO {

    public ArticleDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }
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

    public List<Article>  findBytestsubject_id(Integer id) {
        SQLString sqlString = new SQLString("FROM Article articles");
        if(id != null)
        {
            sqlString.addWhereClause("articles.testsubject = '" + id + "'");
        }

        return findByQuery(sqlString.toString());
    }

    public List<Article> findByPartialName(String partialName) {
        SQLString sqlString = new SQLString("FROM Article a");
        if(partialName != null)
        {
             sqlString.addWhereClause("a.title LIKE '" + partialName + "%'");
        }

        return (List <Article>) findByQuery(sqlString.toString());
    }

}
