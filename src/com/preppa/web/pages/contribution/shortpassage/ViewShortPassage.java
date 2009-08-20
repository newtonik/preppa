  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.entities.ShortPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewShortPassage {
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Property
    private ShortPassage shortpassage;
    @Property
    private List<ShortPassage> shortpassages;

    Object onActivate() {
        this.shortpassages = shortpassageDAO.findAll();
        return null;
    }
}
