/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nwt
 */
@Entity
@Table(name = "mathgridins")
@NamedQueries({@NamedQuery(name = "Mathgridins.findAll", query = "SELECT m FROM Mathgridins m"), @NamedQuery(name = "Mathgridins.findById", query = "SELECT m FROM Mathgridins m WHERE m.id = :id"), @NamedQuery(name = "Mathgridins.findByRange", query = "SELECT m FROM Mathgridins m WHERE m.range = :range"), @NamedQuery(name = "Mathgridins.findByAnswerlow", query = "SELECT m FROM Mathgridins m WHERE m.answerlow = :answerlow"), @NamedQuery(name = "Mathgridins.findByAnswerhigh", query = "SELECT m FROM Mathgridins m WHERE m.answerhigh = :answerhigh"), @NamedQuery(name = "Mathgridins.findBySingleanswer", query = "SELECT m FROM Mathgridins m WHERE m.singleanswer = :singleanswer"), @NamedQuery(name = "Mathgridins.findByCreatedAt", query = "SELECT m FROM Mathgridins m WHERE m.createdAt = :createdAt"), @NamedQuery(name = "Mathgridins.findByUpdatedAt", query = "SELECT m FROM Mathgridins m WHERE m.updatedAt = :updatedAt")})
public class Mathgridins implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "question")
    private String question;
    @Column(name = "range")
    private Short range;
    @Column(name = "answerlow")
    private String answerlow;
    @Column(name = "answerhigh")
    private String answerhigh;
    @Column(name = "singleanswer")
    private String singleanswer;
    @Lob
    @Column(name = "explanation")
    private String explanation;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Mathgridins() {
    }

    public Mathgridins(Integer id) {
        this.id = id;
    }

    public Mathgridins(Integer id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Short getRange() {
        return range;
    }

    public void setRange(Short range) {
        this.range = range;
    }

    public String getAnswerlow() {
        return answerlow;
    }

    public void setAnswerlow(String answerlow) {
        this.answerlow = answerlow;
    }

    public String getAnswerhigh() {
        return answerhigh;
    }

    public void setAnswerhigh(String answerhigh) {
        this.answerhigh = answerhigh;
    }

    public String getSingleanswer() {
        return singleanswer;
    }

    public void setSingleanswer(String singleanswer) {
        this.singleanswer = singleanswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        if (!(object instanceof Mathgridins)) {
            return false;
        }
        Mathgridins other = (Mathgridins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Mathgridins[id=" + id + "]";
    }

}
