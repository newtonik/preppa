/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Questiontype;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class QuestiontypeDAOHibImpl extends AbstractHibernateDAO<Questiontype, Integer> implements  QuestiontypeDAO{


    public QuestiontypeDAOHibImpl(Logger logger, Session session) {
        super(logger, session);
    }
}
