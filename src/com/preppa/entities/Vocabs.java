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
@Table(name = "vocabs")
@NamedQueries({@NamedQuery(name = "Vocabs.findAll", query = "SELECT v FROM Vocabs v"), @NamedQuery(name = "Vocabs.findById", query = "SELECT v FROM Vocabs v WHERE v.id = :id"), @NamedQuery(name = "Vocabs.findByName", query = "SELECT v FROM Vocabs v WHERE v.name = :name"), @NamedQuery(name = "Vocabs.findByPartofspeech", query = "SELECT v FROM Vocabs v WHERE v.partofspeech = :partofspeech"), @NamedQuery(name = "Vocabs.findByCreatedAt", query = "SELECT v FROM Vocabs v WHERE v.createdAt = :createdAt"), @NamedQuery(name = "Vocabs.findByUpdatedAt", query = "SELECT v FROM Vocabs v WHERE v.updatedAt = :updatedAt")})
public class Vocabs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "partofspeech")
    private String partofspeech;
    @Lob
    @Column(name = "definition")
    private String definition;
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

    public Vocabs() {
    }

    public Vocabs(Integer id) {
        this.id = id;
    }

    public Vocabs(Integer id, Date createdAt, Date updatedAt) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartofspeech() {
        return partofspeech;
    }

    public void setPartofspeech(String partofspeech) {
        this.partofspeech = partofspeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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
        if (!(object instanceof Vocabs)) {
            return false;
        }
        Vocabs other = (Vocabs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Vocabs[id=" + id + "]";
    }

}
