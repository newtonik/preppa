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
public interface UserDAO extends GenericDAO <User, Integer>{

    User findById(Integer id);
    List<User> findAllOrderedByLoginName();
    List<User> findByPartialName(String partialName);
}
