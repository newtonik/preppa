
package com.preppa.web.utils;

import com.preppa.web.entities.User;
import java.util.Date;

/**
 * Helper class to help the display of Revisions on web pages
 * @author nwt
 */
public class Revision {

    private String name;
    private Integer revisionNumber;
    private User user;
    private String username;
    private Integer userid;
    private Date revisionTime;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the revisionNumber
     */
    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * @param revisionNumber the revisionNumber to set
     */
    public void setRevisionNumber(Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    /**
     * @return the Usertname
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param Usertname the Usertname to set
     */
    public void setUsername(String Username) {
        this.username = Username;
    }

    /**
     * @return the revisionTime
     */
    public Date getRevisionTime() {
        return revisionTime;
    }

    /**
     * @param revisionTime the revisionTime to set
     */
    public void setRevisionTime(Date revisionTime) {
        this.revisionTime = revisionTime;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
        this.userid = user.getId();
        this.username = user.getUsername();
    }

    /**
     * @return the userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
