/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortPassage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class ShortPassageDAOHibImpl extends AbstractHibernateDAO<ShortPassage, Integer> implements ShortPassageDAO {

    public ShortPassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public ShortPassage findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
