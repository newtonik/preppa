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
@Table(name = "questionanswers")
@NamedQueries({@NamedQuery(name = "Questionanswers.findAll", query = "SELECT q FROM Questionanswers q"), @NamedQuery(name = "Questionanswers.findById", query = "SELECT q FROM Questionanswers q WHERE q.id = :id"), @NamedQuery(name = "Questionanswers.findByQuestionId", query = "SELECT q FROM Questionanswers q WHERE q.questionId = :questionId"), @NamedQuery(name = "Questionanswers.findByCorrect", query = "SELECT q FROM Questionanswers q WHERE q.correct = :correct"), @NamedQuery(name = "Questionanswers.findByCreatedAt", query = "SELECT q FROM Questionanswers q WHERE q.createdAt = :createdAt"), @NamedQuery(name = "Questionanswers.findByUpdatedAt", query = "SELECT q FROM Questionanswers q WHERE q.updatedAt = :updatedAt")})
public class Questionanswers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "correct")
    private Short correct;
    @Lob
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Questionanswers() {
    }

    public Questionanswers(Integer id) {
        this.id = id;
    }

    public Questionanswers(Integer id, Date createdAt, Date updatedAt) {
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Short getCorrect() {
        return correct;
    }

    public void setCorrect(Short correct) {
        this.correct = correct;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        if (!(object instanceof Questionanswers)) {
            return false;
        }
        Questionanswers other = (Questionanswers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Questionanswers[id=" + id + "]";
    }

}
