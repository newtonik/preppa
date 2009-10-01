/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Prompt;
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
@Override
    public List<LongPassage> findByUserIds(List<Integer> ids) {
           SQLString sqlString = new SQLString("FROM LongPassage lp");
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
    public List<LongPassage> findAllByAwaiting() {
          ContentType ct = ContentType.LongPassage;
        SQLString sqlString = new SQLString("FROM LongPassage lp");

            sqlString.addWhereClause("lp.id NOT IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<LongPassage>) findByQuery(sqlString.toString());

    }

    @Override
    public List<LongPassage> findAllByApproved() {
          ContentType ct = ContentType.LongPassage;
        SQLString sqlString = new SQLString("FROM LongPassage lp");

            sqlString.addWhereClause("lp.id IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            return (List<LongPassage>) findByQuery(sqlString.toString());

    }
}
