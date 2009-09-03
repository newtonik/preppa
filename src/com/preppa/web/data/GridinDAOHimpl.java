package com.preppa.web.data;

import com.preppa.web.entities.Gridin;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class GridinDAOHimpl extends AbstractHibernateDAO<Gridin, Integer> implements GridinDAO {

    @Inject
    private VoteDAO voteDAO;

    public GridinDAOHimpl(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public Gridin findById(Long id) {
        SQLString sqlString = new SQLString("FROM Gridin g");
        if (id != null) {
            sqlString.addWhereClause("g.id = '" + id + "'");
        }

        return (Gridin) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<Gridin> findByUserIds(List<Long> ids) {
        SQLString sqlString = new SQLString("FROM Gridin g");
        if (ids.size() > 0) {
            String rlist = ids.toString();
            rlist = rlist.replace("[", "(");
            rlist = rlist.replace("]", ")");
            sqlString.addWhereClause("g.id IN " + rlist);
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public void preDoSave(Gridin question) {
        if(question.getId() != null) {
        Integer vote = voteDAO.findVoteByContentId(ContentType.GridIn, question.getId().intValue());
        question.setVoteScore(vote);

        if (vote >= 1) {
            question.setApproval(Boolean.TRUE);
        } else {
            question.setApproval(Boolean.FALSE);
        }

        }
        else {
            question.setApproval(Boolean.FALSE);
            question.setVoteScore(0);
        }
    }

    @Override
    public void preDoRetrieve(Gridin question) {
        if (question != null) {
            Integer vote = voteDAO.findVoteByContentId(ContentType.GridIn, question.getId().intValue());
            question.setVoteScore(vote);

            if (vote >= 1) {
                question.setApproval(Boolean.TRUE);
            } else {
                question.setApproval(Boolean.FALSE);
            }
            doSave(question);
        }
        
    }
}
