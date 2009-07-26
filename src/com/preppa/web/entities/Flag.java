/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
public class Flag implements Serializable {
    private List<Article> articles;
    private static final long serialVersionUID = 1L;
   
    private Long id;
    private ContentFlag flagtype;
    private String description;
    private ContentType contentType;
    private User flagger;
    private FlagStatus status;
    private Date createdAt;
    private Date updatedAt;


     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flag)) {
            return false;
        }
        Flag other = (Flag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Flag[id=" + id + "]";
    }

    /**
     * @return the flagtype
     */
    @Enumerated(EnumType.ORDINAL)
    public ContentFlag getFlagtype() {
        return flagtype;
    }

    /**
     * @param flagtype the flagtype to set
     */
    public void setFlagtype(ContentFlag flagtype) {
        this.flagtype = flagtype;
    }

    /**
     * @return the description
     */
    @Lob
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the contentType
     */
    @Enumerated(EnumType.ORDINAL)
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the flagger
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getFlagger() {
        return flagger;
    }

    /**
     * @param flagger the flagger to set
     */
    public void setFlagger(User flagger) {
        this.flagger = flagger;
    }

    /**
     * @return the status
     */
   @Enumerated(EnumType.ORDINAL)
    public FlagStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(FlagStatus status) {
        this.status = status;
    }

    @ManyToMany(mappedBy = "flags")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonVisual
    @Basic(optional = false)
    @Column(name = "created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @Audited
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
