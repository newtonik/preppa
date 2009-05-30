/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Passage;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class PassageDAOHibImpl extends AbstractHibernateDAO<Passage, Integer> implements PassageDAO {

    public PassageDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public Passage findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
