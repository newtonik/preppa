package com.preppa.web.data;

import com.preppa.web.entities.Vote;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
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

}
