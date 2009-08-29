  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt.essay;

import com.preppa.web.data.EssayDAO;
import com.preppa.web.entities.Essay;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewEssay {
    @Inject
    private EssayDAO essayDAO;
    @Property
    private List<Essay> essays;
    @Property
    private Essay essay;
    
    Object onActivate(String type) {
        if (type.contains("Graded")) {
            essays = essayDAO.findAllByGraded();
        }
        else if (type.contains("Non")) {
            essays = essayDAO.findAllByNonGraded();
        }
        return null;
    }
}
