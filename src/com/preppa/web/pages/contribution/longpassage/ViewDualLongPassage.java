  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.longpassage;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.entities.LongDualPassage;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewDualLongPassage {
    @Property
    private List<LongDualPassage> longdualpassages;
    @Property
    private LongDualPassage longdualpassage;
    @Inject
    private LongDualPassageDAO longdualpDAO;

    void onActivate() {
        List<LongDualPassage> temp = longdualpDAO.findAll();
        longdualpassages = longdualpDAO.findAll();
    }
}
