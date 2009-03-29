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
@Table(name = "mathmulitplechoice")
@NamedQueries({@NamedQuery(name = "Mathmulitplechoice.findAll", query = "SELECT m FROM Mathmulitplechoice m"), @NamedQuery(name = "Mathmulitplechoice.findById", query = "SELECT m FROM Mathmulitplechoice m WHERE m.id = :id"), @NamedQuery(name = "Mathmulitplechoice.findByImagelink", query = "SELECT m FROM Mathmulitplechoice m WHERE m.imagelink = :imagelink"), @NamedQuery(name = "Mathmulitplechoice.findByCreatedAt", query = "SELECT m FROM Mathmulitplechoice m WHERE m.createdAt = :createdAt"), @NamedQuery(name = "Mathmulitplechoice.findByUpdatedAt", query = "SELECT m FROM Mathmulitplechoice m WHERE m.updatedAt = :updatedAt")})
public class Mathmulitplechoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Boolean id;
    @Column(name = "imagelink")
    private String imagelink;
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

    public Mathmulitplechoice() {
    }

    public Mathmulitplechoice(Boolean id) {
        this.id = id;
    }

    public Mathmulitplechoice(Boolean id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
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
        if (!(object instanceof Mathmulitplechoice)) {
            return false;
        }
        Mathmulitplechoice other = (Mathmulitplechoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Mathmulitplechoice[id=" + id + "]";
    }

}
