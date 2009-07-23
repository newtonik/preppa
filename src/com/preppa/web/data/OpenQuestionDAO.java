package com.preppa.web.data;

import com.preppa.web.entities.OpenQuestion;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface OpenQuestionDAO extends GenericDAO <OpenQuestion, Long>{
    OpenQuestion findById(Long id);

}
