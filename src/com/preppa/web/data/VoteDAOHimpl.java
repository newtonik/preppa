package com.preppa.web.data;

import com.preppa.web.entities.Vote;
import java.math.BigDecimal;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
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
    public Integer findVoteByContentId(String contentType, Integer contentId) {
        SQLString sqlString = new SQLString("select sum(v.value) FROM Vote v");
        if(contentType != null && contentId > 0)
        {
            sqlString.addWhereClause("v.contentType  = '" + contentType + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");
            sqlString.addGroupField("v.contentType");
            sqlString.addGroupField("v.contentId");

        }


        List<Vote> result = findBySQLQuery(sqlString.toString());

        BigDecimal s = (BigDecimal) result.toArray()[0];
        Integer sum = Integer.valueOf(s.intValue());

        
//
        //Integer sum = 0;
        //Integer sum = result.get(0).getValue();
//        for(Vote v : all) {
//            sum += v.getValue();
//        }
        return sum;

    }

}
