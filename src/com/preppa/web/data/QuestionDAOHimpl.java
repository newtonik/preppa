/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.data;

import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.utils.Constants;
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
public class QuestionDAOHimpl extends AbstractHibernateDAO<Question, Integer> implements QuestionDAO {

    @Inject
    private VoteDAO voteDAO;
    private Logger slogger;

    public QuestionDAOHimpl(Logger logger, Session session) {
        super(logger, session);
        slogger = logger;
    }

    @Override
    public Question findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Question q");
        if (id != null) {
            sqlString.addWhereClause("q.id = '" + id + "'");
        }

        return (Question) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<Question> findAllNoRepeat() {
        SQLString sqlString = new SQLString("FROM Question q");
        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Question> findAllByNonApproved(Questiontype questiontype) {
         ContentType ct = ContentType.Question;
          SQLString sqlString = new SQLString("FROM Question q");
          sqlString.addWhereClause("q.questiontype = '" + questiontype.getId() + "'");

            sqlString.addWhereClause("q.id NOT IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            
            return (List<Question>) findByQuery(sqlString.toString());

    }

    @Override
    public List<Question> findAllByApproved(Questiontype questiontype) {
           ContentType ct = ContentType.Question;
          SQLString sqlString = new SQLString("FROM Question q");
            sqlString.addWhereClause("q.questiontype = '" + questiontype.getId() + "'");
            sqlString.addWhereClause("q.id IN "+ "(Select v.contentId FROM Vote v WHERE v.contentTypeId = '"
                                    + ct.ordinal() + "' GROUP BY v.contentId Having sum(v.value) >= '" + Constants.getApprovalThreshhold() + "')");
            
            return (List<Question>) findByQuery(sqlString.toString());
    }

    @Override
    public List<Question> findAllByQuestionType(String qType) {
        SQLString sqlString = new SQLString("FROM Question q");
        sqlString.addWhereClause("q.questiontype.name = '" + qType + "')");
        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Question> findByQuestiontype(Questiontype q) {
        SQLString sqlString = new SQLString("FROM Question q");
        if (q.getId() > 0) {
            sqlString.addWhereClause("q.questiontype_id = '" + q.getId() + "'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Question> findByQuestiontype(Integer q) {
        SQLString sqlString = new SQLString("FROM Question q");
        if (q > 0) {
            sqlString.addWhereClause("q.questiontype_id = '" + q + "'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public List<Question> findByTag(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Question> findByUserIds(List<Integer> ids) {
        SQLString sqlString = new SQLString("FROM Question q");
        if (ids.size() > 0) {

            String rlist = ids.toString();

            rlist = rlist.replace('[', '(');
            rlist = rlist.replace(']', ')');
            sqlString.addWhereClause("q.id IN " + rlist);
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public void preDoSave(Question question) {
        if (question.getId() != null) {
            Integer vote = voteDAO.findVoteByContentId(ContentType.Question, question.getId());
            question.setVoteScore(vote);

            if (vote >= 1) {
                question.setApproval(Boolean.TRUE);
            } else {
                question.setApproval(Boolean.FALSE);
            }
        } else {
            question.setApproval(Boolean.FALSE);
            question.setVoteScore(0);
        }
    }

    @Override
    public void postDoRetrieve(Integer id) {

        Question question = findById(id);
        if (question != null) {
            Integer vote = voteDAO.findVoteByContentId(ContentType.Question, question.getId());
            question.setVoteScore(vote);

            if (vote >= 1) {
                question.setApproval(Boolean.TRUE);
            } else {
                question.setApproval(Boolean.FALSE);
            }
            doSave(question);
        }

        slogger.debug("do post retreive");
    }
}
