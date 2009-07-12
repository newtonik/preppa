/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.UserProfile;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface UserProfileDAO extends GenericDAO <UserProfile, Integer>{

    UserProfile findById(Integer id);
    UserProfile findByUsername(String username);
    UserProfile findByEmail(String email);
    List<UserProfile> findAllOrderedByLoginName();
    List<UserProfile> findByPartialName(String partialName);
    Integer findCountByLoginId(String login);
    UserProfile findByActivationCode(String code);
    UserProfile findByPasswordResetCode(String code);
}
