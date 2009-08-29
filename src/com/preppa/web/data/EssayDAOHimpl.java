  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.Essay;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author Jan Jan
 */
public class EssayDAOHimpl extends AbstractHibernateDAO<Essay, Integer> implements EssayDAO  {
    public EssayDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public Essay findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Essay e");
        if(id != null)
        {
             sqlString.addWhereClause("e.id = '" + id + "'");
        }

        List<Essay> temp = findByQuery(sqlString.toString());
        if (temp.isEmpty()) {
            return null;
        }
        return (Essay) findByQuery(sqlString.toString()).get(0);
    }



    @Override
    public List<Essay> findAllByNewest() {
        SQLString sqlString = new SQLString("FROM Essay e");
        sqlString.addOrderField("created_at ASC");
        //sqlString.addWhereClause("e.id = '" + id + "'");

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Essay> findAllByGraded() {
        SQLString sqlString = new SQLString("FROM Essay e");
        sqlString.addWhereClause("e.user IN (SELECT user FROM FeedBack f)");

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Essay> findAllByNonGraded() {
        SQLString sqlString = new SQLString("FROM Essay e");
        sqlString.addWhereClause("e.user NOT IN (SELECT user FROM FeedBack f)");

        return findByQuery(sqlString.toString());
    }
}
