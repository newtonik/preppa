/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongDualPassage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class LongDualPassageDAOHibImpl extends AbstractHibernateDAO<LongDualPassage, Integer> implements LongDualPassageDAO {

    public LongDualPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public LongDualPassage findById(Integer id) {
        SQLString sqlString = new SQLString("FROM LongDualPassage lp");
        if(id != null)
        {
             sqlString.addWhereClause("lp.id = '" + id + "'");
        }

        return (LongDualPassage) findByQuery(sqlString.toString()).get(0);
    }

}
