/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

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
    public UserProfile findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserProfile findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserProfile> findAllOrderedByLoginName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserProfile> findByPartialName(String partialName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer findCountByLoginId(String login) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserProfile findByActivationCode(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserProfile findByPasswordResetCode(String code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
