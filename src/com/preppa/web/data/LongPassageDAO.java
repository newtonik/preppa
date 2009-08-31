/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.LongPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface LongPassageDAO extends GenericDAO<LongPassage, Integer> {

    LongPassage findById(Integer id);
    List<LongPassage> findByUserId(Integer id);
}
