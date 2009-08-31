/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortDualPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class ShortDualPassageDAOHibImpl extends AbstractHibernateDAO<ShortDualPassage, Integer> implements ShortDualPassageDAO {

    public ShortDualPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public ShortDualPassage findById(Integer id) {
        SQLString sqlString = new SQLString("FROM ShortDualPassage sh");
        if(id != null)
        {
             sqlString.addWhereClause("sh.id = '" + id + "'");
        }

        return (ShortDualPassage) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<ShortDualPassage> findByUserId(Integer id) {
            SQLString sqlString = new SQLString("FROM ShortDualPassage sh");
        if(id != null)
        {
             sqlString.addWhereClause("sh.user = '" + id + "'");
        }

        return findByQuery(sqlString.toString());
    }

}
