/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
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
public class LongDualPassageDAOHibImpl extends AbstractHibernateDAO<LongDualPassage, Integer> implements LongDualPassageDAO {

    public LongDualPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public LongDualPassage findById(Integer id) {
        SQLString sqlString = new SQLString("FROM LongDualPassage lp");
        if(id != null)
        {
             sqlString.addWhereClause("lp.id = '" + id + "'");
        }

        return (LongDualPassage) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<LongDualPassage> findByUserId(Integer id) {
        SQLString sqlString = new SQLString("FROM LongDualPassage lp");
        if(id != null)
        {
            if(id > 0) {
                sqlString.addWhereClause("lp.user = '" + id + "'");
            }
        }

        return  findByQuery(sqlString.toString());
    }

    @Override
    public List<LongDualPassage> findByUserIds(List<Integer> ids) {
           SQLString sqlString = new SQLString("FROM LongDualPassage lp");
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
    public List<LongDualPassage> findAllByAwaiting() {
           ContentType ct = ContentType.LongDualPassage;
        SQLString sqlString = new SQLString("FROM LongDualPassage lp");

            sqlString.addWhereClause("lp.id NOT IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<LongDualPassage>) findByQuery(sqlString.toString());

    }

    @Override
    public List<LongDualPassage> findAllByApproved() {
            ContentType ct = ContentType.LongDualPassage;
        SQLString sqlString = new SQLString("FROM LongDualPassage lp");

            sqlString.addWhereClause("lp.id IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<LongDualPassage>) findByQuery(sqlString.toString());
    }
}
