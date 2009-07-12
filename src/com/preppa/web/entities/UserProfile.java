  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Jan Jan
 */

@Entity
//@Table(name = "userprofile", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class UserProfile implements Serializable {
    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_id", nullable = true, unique =true)
    private Integer user_id;
    @Column(name = "aboutme", length = 5000)
    private String aboutme;
    @Column(name = "activities", length = 5000)
    private String activities;
    @Column(name = "interests", length = 5000)
    private String interests;
    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public UserProfile() {
    }

    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserId(Integer uid) {
        this.user_id = uid;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setAboutMe(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getAboutMe() {
        return aboutme;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getActivities() {
        return activities;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getInterests() {
        return interests;
    }

    public void setCreatedAt(Date now) {
        this.createdAt = now;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date now) {
        this.updatedAt = now;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
