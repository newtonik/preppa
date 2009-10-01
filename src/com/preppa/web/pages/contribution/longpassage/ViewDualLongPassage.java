  
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
import com.preppa.web.utils.Constants;
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
    @Property
    private boolean isApproved;

    private final int APPROVESIZE = Constants.getApprovalThreshhold();

    /*void onActivate() {
        List<LongDualPassage> temp = longdualpDAO.findAll();
        longdualpassages = longdualpDAO.findAll();
    }*/

    Object onActivate(String type) {
        System.out.println("In onActivate");

        if(type.contains("Approved"))
        {
            isApproved = true;
            System.out.println("Approved");

            longdualpassages = longdualpDAO.findAllByApproved();
//            List<LongDualPassage> temp = longdualpDAO.findAll();
//            longdualpassages = new ArrayList<LongDualPassage>();
//            if (longdualpassages != null) {
//                for (int i = 0; i < temp.size(); i++) {
//                    System.out.println("1. " + (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE));
//                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  longdualpassages.contains(temp.get(i)) == false) {
//                        longdualpassages.add(temp.get(i));
//                    }
//                }
//            }
        }
        else if(type.contains("Awaiting"))
        {
            isApproved = false;
            longdualpassages = longdualpDAO.findAllByAwaiting();
//            System.out.println("Awaiting");
//            List<LongDualPassage> temp = longdualpDAO.findAll();
//            longdualpassages = new ArrayList<LongDualPassage>();
//            if (longdualpassages != null) {
//                for (int i = 0; i < temp.size(); i++) {
//                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) < APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  longdualpassages.contains(temp.get(i)) == false) {
//                        longdualpassages.add(temp.get(i));
//                    }
//                }
//            }
//        }
        }
        return null;
    }
}
