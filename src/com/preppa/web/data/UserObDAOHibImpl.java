/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.UserOb;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;
/**
 *
 * @author newtonik
 */
public class UserObDAOHibImpl extends AbstractHibernateDAO<UserOb, Integer> implements UserObDAO
{
    public UserObDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public UserOb findById(Integer id) {
        SQLString sqlString = new SQLString("FROM UserOb p");
        if(id != null)
        {
             sqlString.addWhereClause("p.id = '" + id + "'");
        }

        return (UserOb) findByQuery(sqlString.toString()).get(0);
    }

    public List<UserOb> findAllOrderedByLoginName() {
         return findAll("loginName");
    }

    public List<UserOb> findByPartialName(String partialName) {
       SQLString sqlString = new SQLString("FROM Users u");

        if (partialName != null && partialName.length() > 0)
            sqlString.addWhereClause("u.firstName LIKE '" + partialName + "%'");

        return findByQuery(sqlString.toString());
    }

}
