/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.data;

import com.preppa.web.entities.OpenAnswer;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class OpenAnswerDAOHimpl extends AbstractHibernateDAO<OpenAnswer,Long> implements OpenAnswerDAO {

    public OpenAnswerDAOHimpl(Logger logger, Session session) {
        super(logger, session);
    }
}
