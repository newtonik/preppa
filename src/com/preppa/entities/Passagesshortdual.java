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
@Table(name = "passagesshortdual")
@NamedQueries({@NamedQuery(name = "Passagesshortdual.findAll", query = "SELECT p FROM Passagesshortdual p"), @NamedQuery(name = "Passagesshortdual.findById", query = "SELECT p FROM Passagesshortdual p WHERE p.id = :id"), @NamedQuery(name = "Passagesshortdual.findByCreatedAt", query = "SELECT p FROM Passagesshortdual p WHERE p.createdAt = :createdAt"), @NamedQuery(name = "Passagesshortdual.findByUpdatedAt", query = "SELECT p FROM Passagesshortdual p WHERE p.updatedAt = :updatedAt")})
public class Passagesshortdual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "passage1")
    private String passage1;
    @Lob
    @Column(name = "passage2")
    private String passage2;
    @Lob
    @Column(name = "source")
    private String source;
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

    public Passagesshortdual() {
    }

    public Passagesshortdual(Integer id) {
        this.id = id;
    }

    public Passagesshortdual(Integer id, Date createdAt, Date updatedAt) {
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

    public String getPassage1() {
        return passage1;
    }

    public void setPassage1(String passage1) {
        this.passage1 = passage1;
    }

    public String getPassage2() {
        return passage2;
    }

    public void setPassage2(String passage2) {
        this.passage2 = passage2;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        if (!(object instanceof Passagesshortdual)) {
            return false;
        }
        Passagesshortdual other = (Passagesshortdual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Passagesshortdual[id=" + id + "]";
    }

}
