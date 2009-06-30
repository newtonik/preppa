
package com.preppa.web.services;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.Role;
import com.preppa.web.entities.User;
import java.util.HashSet;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;



/**
 * @author newtonik
 */
public class UserDetailsWithPasswordServiceImpl implements UserDetailsService
{
    private HibernateSessionManager sessionManager;

    private Logger logger;
    private PasswordEncoder encoder;
    private SaltSource salt;
    @Inject
    UserObDAO userDAO;

    public UserDetailsWithPasswordServiceImpl(HibernateSessionManager sessionManager, Logger logger, PasswordEncoder encoder, SaltSource salt )
    {
        this.sessionManager = sessionManager;
        this.logger = logger;
        this.salt = salt;
        this.encoder = encoder;
    }

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        logger.debug("trying to find user " + username);

        Session session = sessionManager.getSession();

        User u = (User) session.createCriteria(User.class).add(
                Restrictions.eq("username", username)).uniqueResult();

        if (u == null)
        {
            throw new UsernameNotFoundException("The username " + username + "cannot be found!");
        }


        
        logger.debug("returning user " + u.getUsername());

        sessionManager.commit();

        return u;
    }

    public UserDetails RegisterUser(User user) {
        if( user != null)
        {
            userDAO.doSave(user);
            Object salter = salt.getSalt(user);
            String passwordToencode = user.getPassword() + user.getUsername() + user.getId();
            String encodpassword = encoder.encodePassword(passwordToencode, salter);
            user.setPassword(encodpassword);
           // userDAO.doSave(user);
           Session session = sessionManager.getSession();
            Role r = (Role) session.createCriteria(Role.class).add(
                    Restrictions.eq("authority", "ROLE_USER")).uniqueResult();

            if (r == null)
            {
                logger.debug("role not found, creating");

                r = new Role();

                r.setAuthority("ROLE_USER");

                session.saveOrUpdate(r);
            }


            user.setRoles(new HashSet<Role>());

            user.getRoles().add(r);

            //session.saveOrUpdate(u);
            userDAO.doSave(user);
        }

        logger.debug("returning user " + user.getUsername());

        sessionManager.commit();

        return user;

        
        
    }


}
