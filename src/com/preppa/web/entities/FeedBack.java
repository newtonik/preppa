  
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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

/**
 *
 * @author Jan Jan
 */
@Entity
public class FeedBack implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String comment;
    private Date createdAt;
    private User user;
    private Essay essay;
    private Integer rating;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    @Lob
    @Audited
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }


    /**
     * @param comment the comment to set
     */
    @Lob
    @Audited
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NonVisual
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the user
     */
     @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @Audited
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the essays
     */
    @ManyToOne
    public Essay getEssay() {
        return essay;
    }

    /**
     * @param essay the essay to set
     */
    public void setEssay(Essay essay) {
        this.essay = essay;
    }
}
