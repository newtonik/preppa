/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.data;

import com.preppa.web.entities.Testsubject;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class TestsubjectDAOHibImpl extends AbstractHibernateDAO<Testsubject, Integer> implements TestsubjectDAO {

    public TestsubjectDAOHibImpl(Logger logger, Session session) {
        super(logger, session);
    }
}
