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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.NonVisual;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "vocab", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NamedQueries({ @NamedQuery(name = "Vocab.findById", query = "SELECT v FROM Vocab v WHERE v.id = :id"), @NamedQuery(name = "Vocab.findByName", query = "SELECT v FROM Vocab v WHERE v.name = :name"), @NamedQuery(name = "Vocab.findByPartofspeech", query = "SELECT v FROM Vocab v WHERE v.partofspeech = :partofspeech"), @NamedQuery(name = "Vocab.findByCreatedAt", query = "SELECT v FROM Vocab v WHERE v.createdAt = :createdAt"), @NamedQuery(name = "Vocab.findByUpdatedAt", query = "SELECT v FROM Vocab v WHERE v.updatedAt = :updatedAt")})
public class Vocab implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "partofspeech", length = 255)
    private String partofspeech;
    @Lob
    @Column(name = "definition", length = 65535)
    private String definition;
    @Lob
    @Column(name = "tags", length = 65535)
    private String tags;
    @Basic(optional = false)
    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Vocab() {
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
        if (!(object instanceof Vocab)) {
            return false;
        }
        Vocab other = (Vocab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Vocab[id=" + id + "]";
    }

}
