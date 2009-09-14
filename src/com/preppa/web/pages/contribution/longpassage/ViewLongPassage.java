  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.longpassage;

import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewLongPassage {
    @Inject
    private LongPassageDAO longpassageDAO;
    @Property
    private LongPassage longpassage;
    @Property
    private List<LongPassage> longpassages;
    @Inject
    private VoteDAO voteDAO;

    Object onActivate(String type) {
        if(type.contains("Approved"))
        {
            System.out.println("In Approved");
            List<LongPassage> temp = longpassageDAO.findAll();
            longpassages = new ArrayList<LongPassage>();
            if (temp.isEmpty() == false) {
                System.out.println("IN LOOP");
                for (int i = 0; i < temp.size(); i++) {
                    if (voteDAO.findSumByLongPassageId(temp.get(i).getId()) >= Constants.getApprovalThreshhold() ) {
                        System.out.println("Sum is " + voteDAO.findSumByLongPassageId(temp.get(i).getId()));
                        System.out.println("ADDING");
                        longpassages.add(temp.get(i));
                    }
                }
            }
        }
        // Assume it is awaiting approval
        else {
            System.out.println("Non-Approved");
            List<LongPassage> temp = longpassageDAO.findAll();
            longpassages = new ArrayList<LongPassage>();
            if (temp.isEmpty() == false) {
                for (int i = 0; i < temp.size(); i++) {
                    if (voteDAO.findSumByLongPassageId(temp.get(i).getId()) < Constants.getApprovalThreshhold() ) {
                        longpassages.add(temp.get(i));
                    }
                }
            }
        }
        return null;
    }
}
