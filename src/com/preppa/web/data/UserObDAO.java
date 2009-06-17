/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.UserOb;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface UserObDAO extends GenericDAO <UserOb, Integer>{

    UserOb findById(Integer id);
    List<UserOb> findAllOrderedByLoginName();
    List<UserOb> findByPartialName(String partialName);
}
