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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;


/**
 *
 * @author nwt
 */
@Entity
public class LongPassage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonVisual
    private Integer id;
    @Lob
    private String tags;
    @Lob
    private String sources;
    @OneToMany(cascade=CascadeType.ALL, targetEntity=Question.class)
    private List<Question> questions;
     @ManyToMany(cascade=CascadeType.ALL, targetEntity = Tag.class)
     @JoinTable(name = "LongPassage_Tag",
    joinColumns = {
      @JoinColumn(name="longpassage_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="tag_id")
    })
    private List<Tag> taglist = new ArrayList<Tag>();
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
    
    //@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity=Passage.class, fetch=FetchType.EAGER)
    //@JoinColumn(name = "passage_id")
    @Lob
    private String passage;
    private String title;
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
        if (!(object instanceof LongPassage)) {
            return false;
        }
        LongPassage other = (LongPassage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the sources
     */
    public String getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(String source) {
        this.sources = source;
    }

    /**
     * @return the createdAt
     */
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
     * @return the passage
     */
    public String getPassage() {
        return passage;
    }

    /**
     * @param passage the passage to set
     */
    public void setPassage(String passage) {
        this.passage = passage;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    /**
     * @return the taglist
     */
    public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }
}
