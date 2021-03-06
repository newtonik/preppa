/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.announcements;

import com.preppa.web.data.AnnouncementDAO;
import com.preppa.web.entities.Announcement;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class EditAnnouncement {
    @Property
    @Persist
    private Announcement announce;
    @Inject
    private AnnouncementDAO announceDAO;
    @Component(parameters = {"value=aAnnouncement"})
    private Editor messageedit;
    @InjectPage
    private ShowAnnouncement showannounce;
    @Property
    private String aTitle;
    @Property
    private String aAnnouncement;
	@Component(id = "announcementedit")
	private Form _formedit;
    @Inject
    private AnnouncementDAO announcementDAO;

    void onActivate(int id) {
        this.announce = announcementDAO.findById(id);
        if(announce != null) {
            aTitle = announce.getTitle();
            aAnnouncement = announce.getMessage();
        }

    }

    @CommitAfter
    Object onSuccess() {
        if (announce == null) {
            announce = new Announcement();
        }
        announce.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        announce.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        announce.setMessage(aAnnouncement);
        announce.setTitle(aTitle);




        announceDAO.doSave(announce);
        showannounce.setannounce(announce);
        return showannounce;
    }
}
