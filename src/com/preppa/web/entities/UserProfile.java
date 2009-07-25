  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

/**
 *
 * @author Jan Jan
 */

@Entity
@Audited
//@Table(name = "userprofile", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
  //  private Integer user_id;
    private String aboutme;
    private String activities;
    private String interests;
    private Date createdAt;
    private Date updatedAt;
    private ContentFlag status;
    private User user;


    public UserProfile() {
    }

    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
//    public void setUserId(Integer uid) {
//        this.user_id = uid;
//    }
//    @Column(name = "user_id", nullable = true, unique =true)
//    public int getUserId() {
//        return this.user_id;
//    }

    public void setAboutMe(String aboutme) {
        this.aboutme = aboutme;
    }
    @Lob
    @Column(name = "aboutme", length = 5000)
    public String getAboutMe() {
        return aboutme;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
    @Lob
    @Column(name = "activities", length = 5000)
    public String getActivities() {
        return activities;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
    @Lob
    @Column(name = "interests", length = 5000)
    public String getInterests() {
        return interests;
    }

    public void setCreatedAt(Date now) {
        this.createdAt = now;
    }

    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date now) {
        this.updatedAt = now;
    }

    @Basic(optional = false)
    @NonVisual
    @Column(name = "updated_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return the status
     */
    public ContentFlag getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ContentFlag status) {
        this.status = status;
    }

    @OneToOne(mappedBy = "userProfile")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}