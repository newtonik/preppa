  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.entities.ShortDualPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewDualShortPassage {
    @Inject
    private ShortDualPassageDAO shortdualpassageDAO;
    @Property
    private ShortDualPassage shortdualpassage;
    @Property
    private List<ShortDualPassage> shortdualpassages;

    Object onActivate() {
        this.shortdualpassages = shortdualpassageDAO.findAll();
        return null;
    }
}
