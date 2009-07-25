/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;
/**
 *
 * @author newtonik
 */
public class UserProfileDAOHibImpl extends AbstractHibernateDAO<UserProfile, Integer> implements UserProfileDAO
{
    public UserProfileDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public UserProfile findById(Integer id) {
        SQLString sqlString = new SQLString("FROM UserProfile p");
        if(id != null)
        {
             sqlString.addWhereClause("p.id = '" + id + "'");
        }

        return (UserProfile) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public UserProfile findByUserId(Integer id) {
        SQLString sqlString = new SQLString("FROM UserProfile p");
        if(id != null)
        {
             sqlString.addWhereClause("p.user_id = '" + id + "'");
        }

        List<UserProfile> returnVal = findByQuery(sqlString.toString());
        if (!returnVal.isEmpty()) {
            return (UserProfile) returnVal.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public UserProfile findByUser(User user) {
            SQLString sqlString = new SQLString("FROM UserProfile p");
        if(user != null)
        {
             sqlString.addWhereClause("p.user = '" + user + "'");
        }

        List<UserProfile> returnVal = findByQuery(sqlString.toString());
        if (!returnVal.isEmpty()) {
            return (UserProfile) returnVal.get(0);
        }
        else {
            return null;
        }

    }



}
