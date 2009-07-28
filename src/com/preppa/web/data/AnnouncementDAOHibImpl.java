/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Announcement;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;
import com.preppa.web.entities.Article;
import org.slf4j.Logger;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import java.util.List;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author newtonik
 */
public class AnnouncementDAOHibImpl  extends AbstractHibernateDAO<Announcement, Integer> implements AnnouncementDAO{

    @Inject
    private HibernateSessionManager sessionManger;
    @Inject
    private AuditReader reader;

    public AnnouncementDAOHibImpl(Logger logger, Session session) {
         super(logger, session);
    }


    public List<Announcement> findAllOrderedByDate() {
        SQLString sqlString = new SQLString("FROM Announcement announcements");
        sqlString.addOrderField("createdAt");

        List<Announcement> returnVal = findByQuery(sqlString.toString());

        if (returnVal.isEmpty()) {
            return null;
        }
        else {
            return returnVal;
        }
    }

    public Announcement findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Announcement announcements");
        if(id != null)
        {
             sqlString.addWhereClause("announcements.id = '" + id + "'");
        }

        List<Announcement> returnVal = findByQuery(sqlString.toString());

        if (returnVal.isEmpty()) {
            return null;
        }
        else {
            return returnVal.get(0);
        }

    }
    
}
