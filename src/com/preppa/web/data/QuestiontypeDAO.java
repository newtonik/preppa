/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Testsubject;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik
 */
public interface QuestiontypeDAO  extends GenericDAO <Questiontype, Integer>{
    List<Questiontype> findByTestsubject(Testsubject testsubject);
    Questiontype findById(Integer id);
     Questiontype findByName(String name);
}
