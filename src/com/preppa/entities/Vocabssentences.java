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
@Table(name = "vocabssentences")
@NamedQueries({@NamedQuery(name = "Vocabssentences.findAll", query = "SELECT v FROM Vocabssentences v"), @NamedQuery(name = "Vocabssentences.findById", query = "SELECT v FROM Vocabssentences v WHERE v.id = :id"), @NamedQuery(name = "Vocabssentences.findBySentence", query = "SELECT v FROM Vocabssentences v WHERE v.sentence = :sentence"), @NamedQuery(name = "Vocabssentences.findByVocabId", query = "SELECT v FROM Vocabssentences v WHERE v.vocabId = :vocabId"), @NamedQuery(name = "Vocabssentences.findByCreatedAt", query = "SELECT v FROM Vocabssentences v WHERE v.createdAt = :createdAt"), @NamedQuery(name = "Vocabssentences.findByUpdatedAt", query = "SELECT v FROM Vocabssentences v WHERE v.updatedAt = :updatedAt")})
public class Vocabssentences implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sentence")
    private String sentence;
    @Column(name = "vocab_id")
    private Integer vocabId;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Vocabssentences() {
    }

    public Vocabssentences(Integer id) {
        this.id = id;
    }

    public Vocabssentences(Integer id, Date createdAt, Date updatedAt) {
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

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getVocabId() {
        return vocabId;
    }

    public void setVocabId(Integer vocabId) {
        this.vocabId = vocabId;
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
        if (!(object instanceof Vocabssentences)) {
            return false;
        }
        Vocabssentences other = (Vocabssentences) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Vocabssentences[id=" + id + "]";
    }

}
