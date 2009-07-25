/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface UserProfileDAO extends GenericDAO <UserProfile, Integer>{

    UserProfile findById(Integer id);
    UserProfile findByUserId(Integer id);
    UserProfile findByUser(User user);
}
