/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class ShortPassageDAOHibImpl extends AbstractHibernateDAO<ShortPassage, Integer> implements ShortPassageDAO {

    public ShortPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public ShortPassage findById(Integer id) {

        SQLString sqlString = new SQLString("FROM ShortPassage lp");
        if(id != null)
        {
             sqlString.addWhereClause("lp.id = '" + id + "'");
        }

        return (ShortPassage) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<ShortPassage> findByUserId(Integer id) {
         SQLString sqlString = new SQLString("FROM ShortPassage lp");
        if(id != null)
        {
             sqlString.addWhereClause("lp.user = '" + id + "'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<ShortPassage> findByUserIds(List<Integer> ids) {
           SQLString sqlString = new SQLString("FROM ShortPassage lp");
        if(ids.size() > 0)
        {
                   String rlist = ids.toString();

             rlist = rlist.replace('[', '(');
             rlist = rlist.replace(']', ')');
             sqlString.addWhereClause("lp.id IN " + rlist);
        }

        return findByQuery(sqlString.toString());
    }

}
