/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongDualPassage;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface LongDualPassageDAO extends GenericDAO<LongDualPassage, Integer> {

    LongDualPassage findById(Integer id);
}