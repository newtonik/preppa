/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.apache.tapestry5.beaneditor.NonVisual;

/**
 *
 * @author newtonik
 */
@Entity
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy="topics")  // map info is in person class
    private Set<Article> articles;

    public Topic() {
        
    }
    public Topic(String name) {
        this.name = name;
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
    public Set<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

}