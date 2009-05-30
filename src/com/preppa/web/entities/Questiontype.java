/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "questiontype")
@NamedQueries({@NamedQuery(name = "Questiontype.findById", query = "SELECT q FROM Questiontype q WHERE q.id = :id"), @NamedQuery(name = "Questiontype.findByName", query = "SELECT q FROM Questiontype q WHERE q.name = :name"), @NamedQuery(name = "Questiontype.findByCreatedAt", query = "SELECT q FROM Questiontype q WHERE q.createdAt = :createdAt"), @NamedQuery(name = "Questiontype.findByUpdatedAt", query = "SELECT q FROM Questiontype q WHERE q.updatedAt = :updatedAt")})
public class Questiontype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @ManyToOne(targetEntity=Testsubject.class)
    @JoinColumn(name="testsubject_id")
    private Testsubject testsubject;
    @NonVisual
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Questiontype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the testsubject
     */
    public Testsubject getTestsubject() {
        return testsubject;
    }

    /**
     * @param testsubject the testsubject to set
     */
    public void setTestsubject(Testsubject testsubject) {
        this.testsubject = testsubject;
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
        if (!(object instanceof Questiontype)) {
            return false;
        }
        Questiontype other = (Questiontype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }



}
