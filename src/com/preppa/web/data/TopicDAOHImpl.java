/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Topic;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class TopicDAOHImpl extends AbstractHibernateDAO<Topic, Integer> implements TopicDAO  {

    public TopicDAOHImpl(Logger logger, Session session) {
        super(logger, session);
    }

    public List<Topic> findByPartialName(String partial) {
        SQLString sqlString = new SQLString("FROM Topic topics");
        if(partial != null)
        {
             sqlString.addOrderField("topics.name");
                 sqlString.addWhereClause("topics.name LIKE '%" + partial + "%'");
        }

        return  findByQuery(sqlString.toString());
    }

}
