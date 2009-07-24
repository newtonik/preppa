/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.search.annotations.Field;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Target;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;


/**
 *
 * @author newtonik
 */
@Entity
@Indexed
public class OpenQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String question;
    private Date createdAt;
    private Date updatedAt;
    private List<Tag> taglist = new ArrayList<Tag>();
    private Integer votes;
    private User user;


    @Id
    @NonVisual
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof OpenQuestion)) {
            return false;
        }
        OpenQuestion other = (OpenQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.OpenQuestion[id=" + id + "]";
    }

    /**
     * @return the createdAt
     */
    @Basic(optional = false)
    @NonVisual
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
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
    @NonVisual
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Audited
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
     * @return the taglist
     */
    @Audited
    @ManyToMany(cascade=CascadeType.ALL,  targetEntity=Tag.class)
     @JoinTable(name = "OpenQuestion_Tag",
    joinColumns = {
      @JoinColumn(name="question_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="tag_id")
    })
   @IndexedEmbedded
   @Target(Tag.class)
   public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @return the question
     */
    @Lob
    @Audited
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String Question) {
        this.question = Question;
    }

    /**
     * @return the title
     */
    @Audited
    @Fields( {
            @Field(index=Index.TOKENIZED, store=Store.NO),
            @Field(name = "title_sort", index=Index.UN_TOKENIZED,
                    store=Store.YES)
    })
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the votes
     */
    @Audited
    @Field(index=Index.UN_TOKENIZED, store=Store.YES)
    public Integer getRating() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setRating(Integer rating) {
        this.votes = rating;
    }

    /**
     * @return the user
     */
    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name="user_id")
    @Audited
    public User getOwner() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setOwner(User owner) {
        this.user = owner;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }

}
