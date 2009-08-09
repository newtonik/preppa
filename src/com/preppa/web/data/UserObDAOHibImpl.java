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
public class UserObDAOHibImpl extends AbstractHibernateDAO<User, Integer> implements UserObDAO {

    public UserObDAOHibImpl(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public User findById(Integer id) {
        SQLString sqlString = new SQLString("FROM User p");
        if (id != null) {
            sqlString.addWhereClause("p.id = '" + id + "'");
        }

        return (User) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public List<User> findAllOrderedByLoginName() {
        return findAll("loginName");
    }

    @Override
    public List<User> findByPartialName(String partialName) {
        SQLString sqlString = new SQLString("FROM User u");

        if (partialName != null && partialName.length() > 0) {
            sqlString.addWhereClause("u.firstName LIKE '" + partialName + "%'");
        }

        return findByQuery(sqlString.toString());
    }

    @Override
    public Long findCountByLoginId(String login) {
        SQLString sqlString = new SQLString("select count(*) From User u");

        if (login != null && login.length() > 0) {
            sqlString.addWhereClause("u.loginId LIKE '" + login + "%'");

        }
        List<User> result = findByQuery(sqlString.toString());
        //return findByQuery(sqlString.toString()).size();
        Long sum;
        sum = 0L;
        //System.out.println(result);
        if (result != null) {
            if (result.toArray().length > 0) {
               sum = (Long) result.toArray()[0];
                //sum = Long.valueOf(s.longValue());
            }
        }
        return sum;

    }
      @Override
    public Long findCountByEmail(String email) {
        SQLString sqlString = new SQLString("select count(*) From User u");

        if (email != null && email.length() > 0) {
            sqlString.addWhereClause("u.email = '" + email + "'");

        }
        List<User> result = findByQuery(sqlString.toString());
        //return findByQuery(sqlString.toString()).size();
        Long sum;
        sum = 0L;
        //System.out.println(result);
        if (result != null) {
            if (result.toArray().length > 0) {
               sum = (Long) result.toArray()[0];
                //sum = Long.valueOf(s.longValue());
            }
        }
        return sum;

    }

    @Override
    public User findByUsername(String username) {
        SQLString sqlString = new SQLString("FROM User u");
        if (username != null) {
            sqlString.addWhereClause("u.username = '" + username + "'");
        }

        return (User) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public User findByEmail(String email) {
        SQLString sqlString = new SQLString("FROM User u");
        if (email != null) {
            sqlString.addWhereClause("u.email = '" + email + "'");
        }

        User out = null;
        out = (User) findByQuery(sqlString.toString()).get(0);
        return out;
    }

    @Override
    public User findByActivationCode(String code) {
        SQLString sqlString = new SQLString("FROM User u");
        if (code != null) {
            sqlString.addWhereClause("u.activationcode = '" + code + "'");
        }

        return (User) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public User findByPasswordResetCode(String code) {
        SQLString sqlString = new SQLString("FROM User u");
        if (code != null) {
            sqlString.addWhereClause("u.passwordResetCode = '" + code + "'");
        }

        return (User) findByQuery(sqlString.toString()).get(0);
    }
}
