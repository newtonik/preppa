  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.FeedBack;
import org.slf4j.Logger;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;

/**
 *
 * @author Jan Jan
 */
public class FeedBackDAOHimpl extends AbstractHibernateDAO<FeedBack, Integer> implements FeedBackDAO {
    public FeedBackDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public FeedBack findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
