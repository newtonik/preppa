  
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
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author Jan Jan
 */
public interface EssayDAO extends GenericDAO <Essay, Integer> {
    Essay findById(Integer id);
}
