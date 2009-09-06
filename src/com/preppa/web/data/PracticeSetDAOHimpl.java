/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.data;

import com.preppa.web.entities.PracticeSet;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class PracticeSetDAOHimpl  extends AbstractHibernateDAO<PracticeSet, Long> implements PracticeSetDAO {
 public  PracticeSetDAOHimpl(Logger logger, Session session)
    {

        super(logger, session);

    }
}
