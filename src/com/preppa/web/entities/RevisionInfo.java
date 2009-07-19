/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

/**
 *
 * @author newtonik
 */
@Entity
@RevisionEntity
@Table(name="REVINFO")
public class RevisionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @RevisionTimestamp
    private long timestamp;
    private String revisionComment;



    @Id
    @RevisionNumber
    @Column(name="REV")
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
        if (!(object instanceof RevisionInfo)) {
            return false;
        }
        RevisionInfo other = (RevisionInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return "com.preppa.web.entities.RevisionInfo[id=" + id + "]";
    }

    /**
     * @return the timestamp
     */
    @Column(name="REVTSTMP")
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the revisionComment
     */
    @Lob
    public String getRevisionComment() {
        return revisionComment;
    }

    /**
     * @param revisionComment the revisionComment to set
     */
    public void setRevisionComment(String revisionComment) {
        this.revisionComment = revisionComment;
    }

}
