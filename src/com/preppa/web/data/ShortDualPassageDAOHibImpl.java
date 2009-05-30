/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortDualPassage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class ShortDualPassageDAOHibImpl extends AbstractHibernateDAO<ShortDualPassage, Integer> implements ShortDualPassageDAO {

    public ShortDualPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public ShortDualPassage findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
