/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongDualPassage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class LongDualPassageDAOHibImpl extends AbstractHibernateDAO<LongDualPassage, Integer> implements LongDualPassageDAO {

    public LongDualPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public LongDualPassage findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
