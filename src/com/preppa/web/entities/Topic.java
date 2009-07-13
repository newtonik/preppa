/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
@Audited
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @NonVisual
    private Integer id;
    private String name;

    private Set<Article> articles;
    private Testsubject testsubject;
    @NonVisual
    private Date createdAt;
    @NonVisual
    private Date updatedAt;

    public Topic() {
        
    }
    public Topic(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * @return the name
     */
    @Column(unique=false)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the articles
     */
    @ManyToMany(targetEntity = Article.class)
    public Set<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return the createdAt
     */
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the testsubject
     */
    @Basic(optional = false)
    @ManyToOne(targetEntity = Testsubject.class)
    @JoinColumn(name="testsubject_id")
    public Testsubject getTestsubject() {
        return testsubject;
    }

    /**
     * @param testsubject the testsubject to set
     */
    public void setTestsubject(Testsubject testsubject) {
        this.testsubject = testsubject;
    }

}
