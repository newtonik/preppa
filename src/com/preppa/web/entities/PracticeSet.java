/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author newtonik
 */
@Entity
public class PracticeSet implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer numofquestions;
    private Boolean randomsubjects;
    private Boolean math;
    private Boolean writing;
    private Boolean reading;
    private Boolean multichoice;
    private Boolean gridin;
    private Integer multinum;
    private Integer gridinnum;

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
        if (!(object instanceof PracticeSet)) {
            return false;
        }
        PracticeSet other = (PracticeSet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.PracticeSet[id=" + id + "]";
    }

    /**
     * @return the numofquestions
     */
    public Integer getNumofquestions() {
        return numofquestions;
    }

    /**
     * @param numofquestions the numofquestions to set
     */
    public void setNumofquestions(Integer numofquestions) {
        this.numofquestions = numofquestions;
    }

    /**
     * @return the randomsubjects
     */
    public Boolean getRandomsubjects() {
        return randomsubjects;
    }

    /**
     * @param randomsubjects the randomsubjects to set
     */
    public void setRandomsubjects(Boolean randomsubjects) {
        this.randomsubjects = randomsubjects;
    }

    /**
     * @return the math
     */
    public Boolean getMath() {
        return math;
    }

    /**
     * @param math the math to set
     */
    public void setMath(Boolean math) {
        this.math = math;
    }

    /**
     * @return the writing
     */
    public Boolean getWriting() {
        return writing;
    }

    /**
     * @param writing the writing to set
     */
    public void setWriting(Boolean writing) {
        this.writing = writing;
    }

    /**
     * @return the reading
     */
    public Boolean getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    /**
     * @return the multichoice
     */
    public Boolean getMultichoice() {
        return multichoice;
    }

    /**
     * @param multichoice the multichoice to set
     */
    public void setMultichoice(Boolean multichoice) {
        this.multichoice = multichoice;
    }

    /**
     * @return the gridin
     */
    public Boolean getGridin() {
        return gridin;
    }

    /**
     * @param gridin the gridin to set
     */
    public void setGridin(Boolean gridin) {
        this.gridin = gridin;
    }

    /**
     * @return the multinum
     */
    public Integer getMultinum() {
        return multinum;
    }

    /**
     * @param multinum the multinum to set
     */
    public void setMultinum(Integer multinum) {
        this.multinum = multinum;
    }

    /**
     * @return the gridinnum
     */
    public Integer getGridinnum() {
        return gridinnum;
    }

    /**
     * @param gridinnum the gridinnum to set
     */
    public void setGridinnum(Integer gridinnum) {
        this.gridinnum = gridinnum;
    }

}
