/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.User;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;
/**
 *
 * @author newtonik
 */
public class UserDAOHibernate extends AbstractHibernateDAO<User, Integer> implements UserDAO
{
    public UserDAOHibernate(Logger logger, Session session)
    {
        super(logger, session);
    }

    public User findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Users users");
        if(id != null)
        {
             sqlString.addWhereClause("users.id = '" + id + "'");
        }

        return (User) findByQuery(sqlString.toString());
    }

    public List<User> findAllOrderedByLoginName() {
         return findAll("loginName");
    }

    public List<User> findByPartialName(String partialName) {
       SQLString sqlString = new SQLString("FROM Users users");

        if (partialName != null && partialName.length() > 0)
            sqlString.addWhereClause("users.firstName LIKE '" + partialName + "%'");

        return findByQuery(sqlString.toString());
    }

   
}
