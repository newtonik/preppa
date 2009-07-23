package com.preppa.web.data;

import com.preppa.web.entities.Vote;
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
        SQLString sqlString = new SQLString(" FROM Vote v");
        if(contentType != null && contentId > 0)
        {
            sqlString.addWhereClause("v.contentType  = '" + contentType + "'");
            sqlString.addWhereClause("v.contentId = '" + contentId + "'");

        }

        List<Vote> all = findByQuery(sqlString.toString());
        Integer sum = 0;
        for(Vote v : all) {
            sum += v.getValue();
        }
        return sum;

    }

}
