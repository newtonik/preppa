package com.preppa.web.data;

import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nikhariale
 */
public class ImprovingParagraphHimplDAO extends AbstractHibernateDAO<ImprovingParagraph, Long> implements ImprovingParagraphDAO {

    public ImprovingParagraphHimplDAO(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public List<ImprovingParagraph> findAllByAwaiting() {
        ContentType ct = ContentType.ImprovingParagraph;
        SQLString sqlString = new SQLString("FROM ImprovingParagraph lp");

        sqlString.addWhereClause("lp.id NOT IN " + "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '" + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
        return (List<ImprovingParagraph>) findByQuery(sqlString.toString());

    }

    @Override
    public List<ImprovingParagraph> findAllByApproved() {
        ContentType ct = ContentType.ImprovingParagraph;
        SQLString sqlString = new SQLString("FROM ImprovingParagraph lp");

        sqlString.addWhereClause("lp.id IN " + "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '" + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
        return (List<ImprovingParagraph>) findByQuery(sqlString.toString());

    }

    @Override
    public List<ImprovingParagraph> findByUserIds(List<Long> ids) {
        SQLString sqlString = new SQLString("FROM ImprovingParagraph lp");
        if (ids.size() > 0) {
            String rlist = ids.toString();

            rlist = rlist.replace('[', '(');
            rlist = rlist.replace(']', ')');
            sqlString.addWhereClause("lp.id IN " + rlist);
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public ImprovingParagraph findById(Integer quesId) {
        SQLString sqlString = new SQLString("FROM ImprovingParagraph lp");

        sqlString.addWhereClause("lp.id =   '" + quesId + "'");
        return (ImprovingParagraph) findByQuery(sqlString.toString());
    }
}
