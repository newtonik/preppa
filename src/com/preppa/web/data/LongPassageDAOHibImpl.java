/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongPassage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class LongPassageDAOHibImpl extends AbstractHibernateDAO<LongPassage, Integer> implements LongPassageDAO {

    public LongPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public LongPassage findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
