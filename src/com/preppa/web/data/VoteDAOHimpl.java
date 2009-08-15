package com.preppa.web.data;

import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.math.BigDecimal;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class VoteDAOHimpl extends AbstractHibernateDAO<Vote, Long> implements VoteDAO {
    public VoteDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public List<Vote> findVoteByContentId(ContentType contentType) {
        SQLString sqlString = new SQLString("FROM Vote v");
        if(contentType != null)
        {
            sqlString.addWhereClause("v.contentTypeId  = '" + contentType + "'");
        }


        //List<Vote> result = findBySQLQuery(sqlString.toString());

//
        //Integer sum = 0;
        //Integer sum = result.get(0).getValue();
//        for(Vote v : all) {
//            sum += v.getValue();
//        }
        return findByCriteria(Restrictions.eq("contentType", contentType));
    }

    public List findQuestionIdByApproved() {
        SQLString sqlString = new SQLString("select contentId FROM Vote v");
        sqlString.addWhereClause("v.contentTypeId  = '" + 6 + "'");
        sqlString.addGroupField("v.contentTypeId");
        sqlString.addGroupField("v.contentId");

        List<Vote> v = findBySQLQuery(sqlString.toString());
        System.out.println("v is " + v);
        return findBySQLQuery(sqlString.toString());
    }

    @Override
    public Integer findSumByGridInId(Integer contentId) {
        SQLString sqlString = new SQLString("select sum(v.value) FROM Vote v");
        if(contentId > 0)
        {
            sqlString.addWhereClause("v.contentTypeId  = '" + 7 + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");
            sqlString.addGroupField("v.contentTypeId");
        }


        List<Vote> result = findBySQLQuery(sqlString.toString());
        System.out.println("Sum is " + result);
        Integer sum = 0;
        if(result != null) {
            if(result.toArray().length > 0) {
                BigDecimal s = (BigDecimal) result.toArray()[0];
                sum = Integer.valueOf(s.intValue());
            }
        }

//
        //Integer sum = 0;
        //Integer sum = result.get(0).getValue();
//        for(Vote v : all) {
//            sum += v.getValue();
//        }
        return sum;

    }

    @Override
    public Integer findSumByQuestionId(Integer contentId) {
        SQLString sqlString = new SQLString("select sum(v.value) FROM Vote v");
        if(contentId > 0)
        {
            sqlString.addWhereClause("v.contentTypeId  = '" + 6 + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");
            sqlString.addGroupField("v.contentTypeId");
        }


        List<Vote> result = findBySQLQuery(sqlString.toString());
        System.out.println("Sum is " + result);
        Integer sum = 0;
        if(result != null) {
            if(result.toArray().length > 0) {
                BigDecimal s = (BigDecimal) result.toArray()[0];
                sum = Integer.valueOf(s.intValue());
            }
        }

//
        //Integer sum = 0;
        //Integer sum = result.get(0).getValue();
//        for(Vote v : all) {
//            sum += v.getValue();
//        }
        return sum;

    }

    @Override
    public Integer findVoteByContentId(ContentType contentType, Integer contentId) {
        SQLString sqlString = new SQLString("select sum(v.value) FROM Vote v");
        if(contentType != null && contentId > 0)
        {
            sqlString.addWhereClause("v.contentTypeId  = '" + contentType + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");
            sqlString.addGroupField("v.contentTypeId");
            sqlString.addGroupField("v.contentId");

        }


        List<Vote> result = findBySQLQuery(sqlString.toString());
        System.out.println("Sum is " + result);
        Integer sum = 0;
        if(result != null) {
            if(result.toArray().length > 0) {
                BigDecimal s = (BigDecimal) result.toArray()[0];
                sum = Integer.valueOf(s.intValue());
            }
        }
        
//
        //Integer sum = 0;
        //Integer sum = result.get(0).getValue();
//        for(Vote v : all) {
//            sum += v.getValue();
//        }
        return sum;

    }

    @Override
    public Boolean checkVoted(ContentType contentType, Integer contentId, User user) {
        SQLString sqlString = new SQLString("select v.value FROM Vote v");
        if(contentType != null && contentId > 0)
        {
            sqlString.addWhereClause("v.contentTypeId  = '" + contentType + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");
            sqlString.addWhereClause("v.user = '" + user.getId() + "'");

        }

            //votes = findBySQLQuery(sqlString.toString());
            Integer result =  0;
            result = (Integer)  countByQuery(sqlString.toString());

            if(result == null)
            {
                return false;
            }
            if(result == 0)
                return false;
            return true;
    }
}
