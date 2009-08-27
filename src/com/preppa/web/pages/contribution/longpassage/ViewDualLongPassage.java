  
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
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.LongDualPassage;
import java.util.ArrayList;
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
    @Inject
    private VoteDAO voteDAO;

    private final int APPROVESIZE = 1;

    /*void onActivate() {
        List<LongDualPassage> temp = longdualpDAO.findAll();
        longdualpassages = longdualpDAO.findAll();
    }*/

    Object onActivate(String type) {
        System.out.println("In onActivate");

        if(type.contains("Approved"))
        {
            System.out.println("Approved");
            List<LongDualPassage> temp = longdualpDAO.findAll();
            longdualpassages = new ArrayList<LongDualPassage>();
            if (longdualpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("1. " + (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE));
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  longdualpassages.contains(temp.get(i)) == false) {
                        longdualpassages.add(temp.get(i));
                    }
                }
            }
        }
        else if(type.contains("Awaiting"))
        {
            System.out.println("Awaiting");
            List<LongDualPassage> temp = longdualpDAO.findAll();
            longdualpassages = new ArrayList<LongDualPassage>();
            if (longdualpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) < APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  longdualpassages.contains(temp.get(i)) == false) {
                        longdualpassages.add(temp.get(i));
                    }
                }
            }
        }

        return null;
    }
}
