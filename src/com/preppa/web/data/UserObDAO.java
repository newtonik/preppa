/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.User;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface UserObDAO extends GenericDAO <User, Integer>{

    User findById(Integer id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllOrderedByLoginName();
    List<User> findByPartialName(String partialName);
    Long findCountByLoginId(String login);
    Long findCountByEmail(String email);
    User findByActivationCode(String code);
    User findByPasswordResetCode(String code);
}
