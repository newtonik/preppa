  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.Essay;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author Jan Jan
 */
public interface EssayDAO extends GenericDAO <Essay, Integer> {
    Essay findById(Integer id);
    List<Essay> findAllByNewest();
    List<Essay> findAllByGraded();
    List<Essay> findAllByNonGraded();
}
