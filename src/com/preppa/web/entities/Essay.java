/*
 * Preppa, Inc.
 *
 * Copyright 2009. All rights
reserved.
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author Jan Jan
 */
@Entity
@Indexed
public class Essay implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String body;
    private Prompt prompt;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private User updatedBy;
    private List<Tag> taglist = new ArrayList<Tag>();
    private List<FeedBack> feedback = new ArrayList<FeedBack>();

    /**
     * @return the taglist
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "Essay_Tag", joinColumns = {@JoinColumn(name = "essay_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @Audited
    @IndexedEmbedded
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
     * @return the updatedBy
     */
    @Audited
    @ManyToOne
    @IndexedEmbedded(depth = 1, prefix = "updatedBy_")
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the question
     */
    @Lob
    @Audited
    public String getBody() {
        return body;
    }

    /**
     * @param quote the quote to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    @Id
    @NonVisual
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @NonVisual
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
    @NonVisual
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
     * @return the user
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @IndexedEmbedded(depth = 1, prefix = "ownedBy_")
    @Audited
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
     * @return the prompt
     */
    @ManyToOne
    public Prompt getPrompt() {
        return prompt;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    /**
     * @return the essays
     */
    @Audited
    @OneToMany(mappedBy = "essay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<FeedBack> getFeedBack() {
        return feedback;
    }

    /**
     * @param essays the essay to set
     */
    public void setFeedBack(List<FeedBack> feedback) {
        this.feedback = feedback;
    }
    @Transient
    public String getTeaser() {
         return getBody().substring(0, Math.min(getBody().length(),100));
    }
}
