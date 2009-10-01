/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentType;
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

        @Override
    public List<ShortDualPassage> findByUserIds(List<Integer> ids) {
           SQLString sqlString = new SQLString("FROM ShortDualPassage lp");
        if(ids.size() > 0)
        {
                   String rlist = ids.toString();

             rlist = rlist.replace('[', '(');
             rlist = rlist.replace(']', ')');
             sqlString.addWhereClause("lp.id IN " + rlist);
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<ShortDualPassage> findAllByAwaiting() {
          ContentType ct = ContentType.ShortDualPassage;
        SQLString sqlString = new SQLString("FROM ShortDualPassage lp");

            sqlString.addWhereClause("lp.id NOT IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<ShortDualPassage>) findByQuery(sqlString.toString());
    }

    @Override
    public List<ShortDualPassage> findAllByApproved() {
              ContentType ct = ContentType.ShortDualPassage;
        SQLString sqlString = new SQLString("FROM ShortDualPassage lp");

            sqlString.addWhereClause("lp.id IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<ShortDualPassage>) findByQuery(sqlString.toString());
    }

}
