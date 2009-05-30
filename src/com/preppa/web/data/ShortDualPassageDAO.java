/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortDualPassage;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface ShortDualPassageDAO extends GenericDAO<ShortDualPassage, Integer> {

    ShortDualPassage findById(Integer id);
}
