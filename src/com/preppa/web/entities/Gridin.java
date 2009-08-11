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
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
public class Gridin implements Serializable {
    private static long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String question;
    private List<GridinAnswer> answers = new ArrayList<GridinAnswer>();
    private Date createdAt;
    private Date updatedAt;
    private List<Tag> taglist = new ArrayList<Tag>();
    private Integer voteScore;
    private User user;
    private ContentFlag status;
    private String revComment;
    private List<Topic> topics = new ArrayList<Topic>();
    private Boolean image;
    private String imagePath;
    private User updatedBy;
    

    @Id
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gridin)) {
            return false;
        }
        Gridin other = (Gridin) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getQuestion();
    }

    /**
     * @return the title
     */
    @Audited
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
     * @return the question
     */
       @Audited
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
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
    @Audited
    @Basic(optional = false)
    @NonVisual
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
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
    @ManyToMany
    @JoinTable(name = "Gridin_Tag",
    joinColumns = {
      @JoinColumn(name="gridin_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="tag_id")
    })
    @Audited
    public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> taglist) {
        this.taglist = taglist;
    }

    /**
     * @return the voteScore
     */
    @Audited
    public Integer getRating() {
        return voteScore;
    }

    /**
     * @param voteScore the voteScore to set
     */
    public void setRating(Integer rating) {
        this.voteScore = rating;
    }

    /**
     * @return the user
     */
    @Audited
    @ManyToOne
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the answers
     */
    @OneToMany(targetEntity = GridinAnswer.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Audited
    public List<GridinAnswer> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(List<GridinAnswer> answers) {
        this.answers = answers;
    }

    /**
     * @return the status
     */
    @Audited
    @Enumerated(EnumType.ORDINAL)
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
     * @return the topics
     */
    @ManyToMany(targetEntity=Topic.class)
    @JoinTable(name = "Gridin_Topic",
    joinColumns = {
      @JoinColumn(name="gridinId")
        },
    inverseJoinColumns = {
      @JoinColumn(name="topicId")
    })
    @Audited
    public List<Topic> getTopics() {
        return topics;
    }

    /**
     * @param topics the topics to set
     */
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
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
    @ManyToOne
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
