/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;


import com.preppa.web.utils.ContentFlag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.search.annotations.Field;
import org.apache.tapestry5.beaneditor.NonVisual;
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
    private List<OpenAnswer> answers = new ArrayList<OpenAnswer>();
    private Date createdAt;
    private Date updatedAt;
    private List<Tag> taglist = new ArrayList<Tag>();
    private Integer votes;
    private User user;
    private ContentFlag status;
    private String revComment;
    private Boolean image;
    private String imagePath;
    private User updatedBy;


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
    @Basic(optional = false)
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
    @Basic(optional = false)
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
    //@Fetch(value = FetchMode.JOIN)
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

    /**
     * @return the answers
     */
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @IndexedEmbedded
    @Audited
    public List<OpenAnswer> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(List<OpenAnswer> answers) {
        this.answers = answers;
    }

    /**
     * @return the status
     */
    @Enumerated(EnumType.ORDINAL)
    @Audited
    public ContentFlag getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ContentFlag status) {
        this.status = status;
    }

    /**
     * @return the revComment
     */
    @Lob
    @Audited
    public String getRevComment() {
        return revComment;
    }

    /**
     * @param revComment the revComment to set
     */
    public void setRevComment(String revComment) {
        this.revComment = revComment;
    }

    /**
     * @return the image
     */
    @Audited
    public Boolean getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Boolean image) {
        this.image = image;
    }

    /**
     * @return the imagePath
     */
    @Audited
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the updatedBy
     */
    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    //@Fetch(value = FetchMode.JOIN)
    @JoinColumn(name="updatedby")
    @Audited
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

}
