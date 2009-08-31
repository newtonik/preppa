/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.ShortPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface ShortPassageDAO  extends GenericDAO< ShortPassage, Integer> {
    ShortPassage findById(Integer id);
    List<ShortPassage> findByUserId(Integer id);
    List<ShortPassage> findByUserIds(List<Integer> ids);
}
