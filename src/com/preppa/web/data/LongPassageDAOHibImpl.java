/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class LongPassageDAOHibImpl extends AbstractHibernateDAO<LongPassage, Integer> implements LongPassageDAO {

    public LongPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public LongPassage findById(Integer id) {
        SQLString sqlString = new SQLString("FROM LongPassage lp");
        if(id != null)
        {
             sqlString.addWhereClause("lp.id = '" + id + "'");
        }

        return (LongPassage) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<LongPassage> findByUserId(Integer id) {
          SQLString sqlString = new SQLString("FROM LongPassage lp");
        if(id != null)
        {
            if(id > 0) {
                sqlString.addWhereClause("lp.user = '" + id + "'");
            }
        }

        return findByQuery(sqlString.toString());
    }

}
