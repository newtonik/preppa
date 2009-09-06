/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import com.preppa.web.utils.PracticeSetType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author newtonik
 */
@Entity
public class PracticeSet implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private PracticeSetType setType;
    private Integer numofquestions;
    private Boolean randomsubjects;
    private Boolean math;
    private Boolean writing;
    private Boolean reading;
    private Boolean multichoice;
    private Boolean gridin;
    private Integer multinum;
    private Integer gridinnum;
    private Boolean sentencecompletion;
    private Boolean longpassage;
    private Boolean longdualpassage;
    private Boolean shortpassage;
    private Boolean shortdualpassage;
    private Integer longpassnum;
    private Integer longdualpassnum;
    private Integer shortpassnum;
    private Integer shortdualpassnum;
    private Integer sentcompnum;
    private User owner;

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

    /**
     * @return the setType
     */
    @Enumerated(EnumType.ORDINAL)
    public PracticeSetType getSetType() {
        return setType;
    }

    /**
     * @param setType the setType to set
     */
    public void setSetType(PracticeSetType setType) {
        this.setType = setType;
    }

    /**
     * @return the sentencecompletion
     */
    public Boolean getSentencecompletion() {
        return sentencecompletion;
    }

    /**
     * @param sentencecompletion the sentencecompletion to set
     */
    public void setSentencecompletion(Boolean sentencecompletion) {
        this.sentencecompletion = sentencecompletion;
    }

    /**
     * @return the longpassage
     */
    public Boolean getLongpassage() {
        return longpassage;
    }

    /**
     * @param longpassage the longpassage to set
     */
    public void setLongpassage(Boolean longpassage) {
        this.longpassage = longpassage;
    }

    /**
     * @return the longdualpassage
     */
    public Boolean getLongdualpassage() {
        return longdualpassage;
    }

    /**
     * @param longdualpassage the longdualpassage to set
     */
    public void setLongdualpassage(Boolean longdualpassage) {
        this.longdualpassage = longdualpassage;
    }

    /**
     * @return the shortpassage
     */
    public Boolean getShortpassage() {
        return shortpassage;
    }

    /**
     * @param shortpassage the shortpassage to set
     */
    public void setShortpassage(Boolean shortpassage) {
        this.shortpassage = shortpassage;
    }

    /**
     * @return the longpassnum
     */
    public Integer getLongpassnum() {
        return longpassnum;
    }

    /**
     * @param longpassnum the longpassnum to set
     */
    public void setLongpassnum(Integer longpassnum) {
        this.longpassnum = longpassnum;
    }

    /**
     * @return the longdualpassnum
     */
    public Integer getLongdualpassnum() {
        return longdualpassnum;
    }

    /**
     * @param longdualpassnum the longdualpassnum to set
     */
    public void setLongdualpassnum(Integer longdualpassnum) {
        this.longdualpassnum = longdualpassnum;
    }

    /**
     * @return the shortpassnum
     */
    public Integer getShortpassnum() {
        return shortpassnum;
    }

    /**
     * @param shortpassnum the shortpassnum to set
     */
    public void setShortpassnum(Integer shortpassnum) {
        this.shortpassnum = shortpassnum;
    }

    /**
     * @return the shortdualpassnum
     */
    public Integer getShortdualpassnum() {
        return shortdualpassnum;
    }

    /**
     * @param shortdualpassnum the shortdualpassnum to set
     */
    public void setShortdualpassnum(Integer shortdualpassnum) {
        this.shortdualpassnum = shortdualpassnum;
    }

    /**
     * @return the shortdualpassage
     */
    public Boolean getShortdualpassage() {
        return shortdualpassage;
    }

    /**
     * @param shortdualpassage the shortdualpassage to set
     */
    public void setShortdualpassage(Boolean shortdualpassage) {
        this.shortdualpassage = shortdualpassage;
    }

    /**
     * @return the sentcompnum
     */
    public Integer getSentcompnum() {
        return sentcompnum;
    }

    /**
     * @param sentcompnum the sentcompnum to set
     */
    public void setSentcompnum(Integer sentcompnum) {
        this.sentcompnum = sentcompnum;
    }

    /**
     * @return the owner
     */
    @ManyToOne
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

}
