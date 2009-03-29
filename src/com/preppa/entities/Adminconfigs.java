/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nwt
 */
@Entity
@Table(name = "adminconfigs")
@NamedQueries({@NamedQuery(name = "Adminconfigs.findAll", query = "SELECT a FROM Adminconfigs a"), @NamedQuery(name = "Adminconfigs.findById", query = "SELECT a FROM Adminconfigs a WHERE a.id = :id")})
public class Adminconfigs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Adminconfigs() {
    }

    public Adminconfigs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Adminconfigs)) {
            return false;
        }
        Adminconfigs other = (Adminconfigs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.entities.Adminconfigs[id=" + id + "]";
    }

}
