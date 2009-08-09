/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Testsubject;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface TestsubjectDAO extends GenericDAO <Testsubject, Integer> {
    Testsubject findById(Integer id);
    List<Testsubject> findAllWithQuestions();
}
