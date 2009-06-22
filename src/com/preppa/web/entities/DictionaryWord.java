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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "dictionaryword", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DictionaryWord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique =true )
    private Integer id;
    @Column(name = "name", length = 255)
    @Validate("required")
    private String name;
    @Column(name = "partofspeech", length = 255)
    private String partofspeech;
    @Lob
    @Validate("required")
    @Column(name = "definition", length = 65535)
    private String definition;


    public DictionaryWord() {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DictionaryWord)) {
            return false;
        }
        DictionaryWord other = (DictionaryWord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.DictionaryWord[id=" + id + "]";
    }

}
