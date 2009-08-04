/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
public class GridinAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String answer;
    //this can be used to explain the answer or it's format.
    private String description;
    private Boolean range;
    private String lowAnswer;
    private String highAnswer;

    
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
        if (!(object instanceof GridinAnswer)) {
            return false;
        }
        GridinAnswer other = (GridinAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.GridinAnswer[id=" + id + "]";
    }

    /**
     * @return the answer
     */
    @Audited
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
     * @return the range
     */
    @Basic(optional = false)
    @Audited
    public Boolean getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(Boolean range) {
        this.range = range;
    }

    /**
     * @return the lowAnswer
     */
    @Audited
    public String getLowAnswer() {
        return lowAnswer;
    }

    /**
     * @param lowAnswer the lowAnswer to set
     */
    public void setLowAnswer(String lowAnswer) {
        this.lowAnswer = lowAnswer;
    }

    /**
     * @return the highAnswer
     */
    @Audited
    public String getHighAnswer() {
        return highAnswer;
    }

    /**
     * @param highAnswer the highAnswer to set
     */
    public void setHighAnswer(String highAnswer) {
        this.highAnswer = highAnswer;
    }

}
