  
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
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.utils.Constants;
import java.util.ArrayList;
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
    @Inject
    private VoteDAO voteDAO;


    private final int APPROVESIZE = Constants.getApprovalThreshhold() ;

    /*Object onActivate() {
        this.shortdualpassages = shortdualpassageDAO.findAll();
        return null;
    }*/

    Object onActivate(String type) {
        System.out.println("In onActivate");

        if(type.contains("Approved"))
        {
            System.out.println("Approved");
            List<ShortDualPassage> temp = shortdualpassageDAO.findAll();
            shortdualpassages = new ArrayList<ShortDualPassage>();
            if (shortdualpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("1. " + (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE));
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  shortdualpassages.contains(temp.get(i)) == false) {
                        shortdualpassages.add(temp.get(i));
                    }
                }
            }
        }
        else if(type.contains("Awaiting"))
        {
            System.out.println("Awaiting");
            List<ShortDualPassage> temp = shortdualpassageDAO.findAll();
            shortdualpassages = new ArrayList<ShortDualPassage>();
            if (shortdualpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) < APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  shortdualpassages.contains(temp.get(i)) == false) {
                        shortdualpassages.add(temp.get(i));
                    }
                }
            }
        }

        return null;
    }
}
