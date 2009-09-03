  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.Prompt;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author Jan Jan
 */
public class PromptDAOHimpl extends AbstractHibernateDAO<Prompt, Integer> implements PromptDAO  {
    public PromptDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public Prompt findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Prompt p");
        if(id != null)
        {
             sqlString.addWhereClause("p.id = '" + id + "'");
        }

        return (Prompt) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<Prompt> findByPartialName(String partialName) {
        SQLString sqlString = new SQLString("FROM Prompt p");
        if(partialName != null)
        {
             sqlString.addWhereClause("p.question LIKE '" + partialName + "%'");
        }

        return (List <Prompt>) findByQuery(sqlString.toString());
    }

    @Override
    public List<Prompt> findByTopic(String topic) {
        SQLString sqlString = new SQLString("FROM Prompt p");
        if(topic != null)
        {
             sqlString.addWhereClause("p.topic = '" + topic + "'");
        }
        return (List <Prompt>) findByQuery(sqlString.toString());
    }
}