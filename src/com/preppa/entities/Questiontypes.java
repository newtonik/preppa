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
@Table(name = "questiontypes")
@NamedQueries({@NamedQuery(name = "Questiontypes.findAll", query = "SELECT q FROM Questiontypes q"), @NamedQuery(name = "Questiontypes.findById", query = "SELECT q FROM Questiontypes q WHERE q.id = :id"), @NamedQuery(name = "Questiontypes.findByName", query = "SELECT q FROM Questiontypes q WHERE q.name = :name"), @NamedQuery(name = "Questiontypes.findByTestsubjectId", query = "SELECT q FROM Questiontypes q WHERE q.testsubjectId = :testsubjectId"), @NamedQuery(name = "Questiontypes.findByCreatedAt", query = "SELECT q FROM Questiontypes q WHERE q.createdAt = :createdAt"), @NamedQuery(name = "Questiontypes.findByUpdatedAt", query = "SELECT q FROM Questiontypes q WHERE q.updatedAt = :updatedAt")})
public class Questiontypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "testsubject_id")
    private int testsubjectId;
    @Lob
    @Column(name = "sources")
    private String sources;
    @Lob
    @Column(name = "tags")
    private String tags;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Questiontypes() {
    }

    public Questiontypes(Integer id) {
        this.id = id;
    }

    public Questiontypes(Integer id, String name, int testsubjectId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.testsubjectId = testsubjectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public int getTestsubjectId() {
        return testsubjectId;
    }

    public void setTestsubjectId(int testsubjectId) {
        this.testsubjectId = testsubjectId;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
        if (!(object instanceof Questiontypes)) {
            return false;
        }
        Questiontypes other = (Questiontypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Questiontypes[id=" + id + "]";
    }

}
