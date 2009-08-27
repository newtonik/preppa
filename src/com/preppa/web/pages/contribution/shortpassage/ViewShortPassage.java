  
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
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.ShortPassage;
import java.util.ArrayList;
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
    @Inject
    private VoteDAO voteDAO;


    private final int APPROVESIZE = 1;
    /*Object onActivate() {
        this.shortpassages = shortpassageDAO.findAll();
        return null;
    }*/

   Object onActivate(String type) {
        System.out.println("In onActivate");

        if(type.contains("Approved"))
        {
            System.out.println("Approved");
            List<ShortPassage> temp = shortpassageDAO.findAll();
            shortpassages = new ArrayList<ShortPassage>();
            if (shortpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("1. " + (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE));
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) >= APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  shortpassages.contains(temp.get(i)) == false) {
                        shortpassages.add(temp.get(i));
                    }
                }
            }
        }
        else if(type.contains("Awaiting"))
        {
            System.out.println("Awaiting");
            List<ShortPassage> temp = shortpassageDAO.findAll();
            shortpassages = new ArrayList<ShortPassage>();
            if (shortpassages != null) {
                for (int i = 0; i < temp.size(); i++) {
                    if (voteDAO.findSumByLongDualPassageId(temp.get(i).getId()) < APPROVESIZE && temp.get(i).getFlags().isEmpty() &&  shortpassages.contains(temp.get(i)) == false) {
                        shortpassages.add(temp.get(i));
                    }
                }
            }
        }

        return null;
    }
}
