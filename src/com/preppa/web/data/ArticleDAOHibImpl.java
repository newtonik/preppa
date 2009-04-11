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

        return (Article) findByQuery(sqlString.toString()).get(0);
    }

}
