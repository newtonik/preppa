
package com.preppa.web.services;

import com.preppa.web.entities.Role;
import com.preppa.web.entities.User;
import java.util.HashSet;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;



/**
 * @author Ulrich St&auml;rk
 */
public class UserDetailsServiceImpl implements UserDetailsService
{
    private HibernateSessionManager sessionManager;

    private Logger logger;

    public UserDetailsServiceImpl(HibernateSessionManager sessionManager, Logger logger)
    {
        this.sessionManager = sessionManager;
        this.logger = logger;
    }

    /**
     * Try to find the given user in the local database. Since we are using OpenID that user
     * might not exist in our database yet. If it doesn't, create a new user and store it.
     *
     * WARNING: This implementation will permit EVERY OpenID authenticated user to log in. In
     * a real environment you want to restrict this to trusted OpenID providers or you have
     * to restrict those users to non-sensible information (by means of roles).
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        logger.debug("trying to find user " + username);

        Session session = sessionManager.getSession();

        User u = (User) session.createCriteria(User.class).add(
                Restrictions.eq("username", username)).uniqueResult();

        if (u == null)
        {
            logger.debug("user not found, creating");

            Role r = (Role) session.createCriteria(Role.class).add(
                    Restrictions.eq("authority", "ROLE_USER")).uniqueResult();

            if (r == null)
            {
                logger.debug("role not found, creating");

                r = new Role();

                r.setAuthority("ROLE_USER");

                session.saveOrUpdate(r);
            }

            u = new User();

            u.setUsername(username);

            u.setRoles(new HashSet<Role>());

            u.getRoles().add(r);

            session.saveOrUpdate(u);
        }

        logger.debug("returning user " + u.getUsername());

        sessionManager.commit();

        return u;
    }

}
