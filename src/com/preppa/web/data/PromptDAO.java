  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.data;

import com.preppa.web.entities.Prompt;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author Jan Jan
 */
public interface PromptDAO extends GenericDAO<Prompt, Integer> {
    Prompt findById(Integer id);
    List<Prompt> findByPartialName(String partialName);
    List<Prompt> findByTopic(String topic);
    List<Prompt> findAllByAwaiting();
     List<Prompt> findAllByApproved();
     List<Prompt> findByUserIds(List<Integer> ids);
}
