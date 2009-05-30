/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Announcement;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class AnnouncementDAOHibImpl  extends AbstractHibernateDAO <Announcement, Integer> implements AnnouncementDAO {

    public AnnouncementDAOHibImpl(Logger logger, Session session) {
         super(logger, session);
    }

    public Announcement findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
