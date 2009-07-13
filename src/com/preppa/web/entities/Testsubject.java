/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "testsubject", catalog = "preppa_tapestry", schema = "")
@NamedQueries({@NamedQuery(name = "Testsubject.findById", query = "SELECT t FROM Testsubject t WHERE t.id = :id"), @NamedQuery(name = "Testsubject.findByName", query = "SELECT t FROM Testsubject t WHERE t.name = :name"), @NamedQuery(name = "Testsubject.findByCreatedAt", query = "SELECT t FROM Testsubject t WHERE t.createdAt = :createdAt"), @NamedQuery(name = "Testsubject.findByUpdatedAt", query = "SELECT t FROM Testsubject t WHERE t.updatedAt = :updatedAt")})
@Audited
public class Testsubject implements Serializable {
  
    private Integer id;    
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List questionTypes = new ArrayList();

    public Testsubject() {
    }

    @Id
    @NonVisual
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "name", length = 255)
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    @Lob
    @Column(name = "description", length = 65535)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic(optional = false)
    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Basic(optional = false)
    @NonVisual
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
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
        if (!(object instanceof Testsubject)) {
            return false;
        }
        Testsubject other = (Testsubject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return the questionTypes
     */
   @OneToMany(
            targetEntity = Questiontype.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "testsubject_id")
    public List getQuestionTypes() {
        return questionTypes;
    }

    /**
     * @param questionTypes the questionTypes to set
     */
    public void setQuestionTypes(List questionTypes) {
        this.questionTypes = questionTypes;
    }

}
