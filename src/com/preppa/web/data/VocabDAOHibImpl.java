/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Vocab;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class VocabDAOHibImpl extends AbstractHibernateDAO<Vocab, Integer> implements VocabDAO {
    public VocabDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }


    public List<Vocab> findAllOrderedByName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Vocab> findByPartialName(String partialName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
