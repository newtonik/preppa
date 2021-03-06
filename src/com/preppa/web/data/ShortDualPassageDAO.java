/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.data;

import com.preppa.web.entities.ShortDualPassage;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface ShortDualPassageDAO extends GenericDAO<ShortDualPassage, Integer> {

    List<ShortDualPassage> findByUserId(Integer id);

    ShortDualPassage findById(Integer id);

    List<ShortDualPassage> findByUserIds(List<Integer> ids);

    List<ShortDualPassage> findAllByAwaiting();

    List<ShortDualPassage> findAllByApproved();
}
