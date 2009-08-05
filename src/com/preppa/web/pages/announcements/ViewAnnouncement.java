  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.announcements;

import com.preppa.web.data.AnnouncementDAO;
import com.preppa.web.entities.Announcement;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;


/**
 *
 * @author Jan Jan
 */
public class ViewAnnouncement {
    @Inject
    private AnnouncementDAO announcementDAO;

    @Property
    private Announcement announcement;

    /**
     * @return the the articles queried
     */
    public List<Announcement> getAllAnnouncements() {
        List<Announcement> returnVal;
        returnVal = announcementDAO.findAllOrderedByDate();

        return returnVal;
    }


}
