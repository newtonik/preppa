  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.FeedBack;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author Jan Jan
 */
public interface FeedBackDAO extends GenericDAO <FeedBack, Integer> {
    FeedBack findById(Integer id);
}
