/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class Flag implements Serializable {
    private ImprovingParagraph improvingParagraph;
    private Question question;
    private Article article;
    private LongDualPassage longdualpassage;
    private ShortDualPassage shortdualpassage;
    private LongPassage longpassage;
    private ShortPassage shortpassage;
    private Prompt prompt;
    private static final long serialVersionUID = 1L;
   
    private Long id;
    private ContentFlag flagtype;
    private String description;
    private ContentType contentType;
    private User flagger;
    private FlagStatus status;
    private Date createdAt;
    private Date updatedAt;
    private User assignee;
    private Vocab vocab;
    private Gridin gridin;

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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flag)) {
            return false;
        }
        Flag other = (Flag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Flag[id=" + id + "]";
    }

    /**
     * @return the flagtype
     */
    @Enumerated(EnumType.ORDINAL)
    public ContentFlag getFlagtype() {
        return flagtype;
    }

    /**
     * @param flagtype the flagtype to set
     */
    public void setFlagtype(ContentFlag flagtype) {
        this.flagtype = flagtype;
    }

    /**
     * @return the description
     */
    @Lob
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the contentType
     */
    @Enumerated(EnumType.ORDINAL)
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the flagger
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    public User getFlagger() {
        return flagger;
    }

    /**
     * @param flagger the flagger to set
     */
    public void setFlagger(User flagger) {
        this.flagger = flagger;
    }

    /**
     * @return the status
     */
   @Enumerated(EnumType.ORDINAL)
    public FlagStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(FlagStatus status) {
        this.status = status;
    }

    @NonVisual
    @Basic(optional = false)
    @Column(name = "created_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @Audited
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToOne
    @JoinColumn(name="contentId")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        if(contentType != null)
        {
            if(contentType == ContentType.Article) {
                 this.article = article;
            }
            else
            {
                this.article = null;
            }
        }
        else
        {
            this.contentType = ContentType.Article;
             this.article = article;
        }

    }

    /**
     * @return the assignee
     */
    @ManyToOne
    @JoinColumn(name="assignee_id")
    public User getAssignee() {
        return assignee;
    }

    /**
     * @param assignee the assignee to set
     */
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    /**
     * @return the vocab
     */
    @ManyToOne
    public Vocab getVocab() {
        return vocab;
    }

    /**
     * @param vocab the vocab to set
     */
    public void setVocab(Vocab vocab) {
        if(contentType != null)
        {
            if(contentType == ContentType.Vocab)
            {
                this.vocab = vocab;
            }
            else
            {
               this.vocab = null;
            }
        }
        else {
            this.contentType = ContentType.Vocab;
            this.vocab = vocab;
        }
    }

    @ManyToOne
    public Gridin getGridin() {
        return gridin;
    }

    public void setGridin(Gridin gridin) {
        /* if(contentType != null)
        {
            if(contentType == ContentType.GridIn)
            {
                this.gridin = gridin;
            }
            else
            {
               this.gridin = null;
            }
        }
        else {
            this.contentType = ContentType.GridIn;
            this.gridin = gridin;
        }*/
        this.gridin = gridin;
        this.contentType = ContentType.GridIn;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
         if(contentType != null)
        {
            if(contentType == ContentType.Question)
            {
                this.question = question;
            }
            else
            {
               this.question = null;
            }
        }
        else {
            this.contentType = ContentType.Question;
            this.question = question;
        }
    }

    @ManyToOne
    public LongDualPassage getlongdualpassage() {
        return longdualpassage;
    }

    public void setlongdualpassage(LongDualPassage longdualpassage) {
         if(contentType != null)
        {
            System.out.println("*A");
            if(contentType == ContentType.LongDualPassage)
            {
                System.out.println("*B");
                this.longdualpassage = longdualpassage;
            }
            else
            {
               System.out.println("*C");
               System.out.println(contentType);
               this.longdualpassage = null;
            }
        }
        else {
            System.out.println("*D");
            this.contentType = ContentType.LongDualPassage;
            this.longdualpassage = longdualpassage;
        }
    }

    @ManyToOne
    public LongPassage getlongpassage() {
        return longpassage;
    }

    public void setlongpassage(LongPassage longpassage) {
         if(contentType != null)
        {
            System.out.println("*A");
            if(contentType == ContentType.LongPassage)
            {
                System.out.println("*B");
                this.longpassage = longpassage;
            }
            else
            {
               System.out.println("*C");
               System.out.println(contentType);
               this.longpassage = null;
            }
        }
        else {
            System.out.println("*D");
            this.contentType = ContentType.LongPassage;
            this.longpassage = longpassage;
        }
    }

    @ManyToOne
    public ShortDualPassage getshortdualpassage() {
        return shortdualpassage;
    }

    public void setshortdualpassage(ShortDualPassage shortdualpassage) {
         if(contentType != null)
        {
            System.out.println("*A");
            if(contentType == ContentType.ShortDualPassage)
            {
                System.out.println("*B");
                this.shortdualpassage = shortdualpassage;
            }
            else
            {
               System.out.println("*C");
               System.out.println(contentType);
               this.shortdualpassage = null;
            }
        }
        else {
            System.out.println("*D");
            this.contentType = ContentType.ShortDualPassage;
            this.shortdualpassage = shortdualpassage;
        }
    }

    @ManyToOne
    public ShortPassage getshortpassage() {
        return shortpassage;
    }

    public void setshortpassage(ShortPassage shortpassage) {
         if(contentType != null)
        {
            System.out.println("*A");
            if(contentType == ContentType.ShortPassage)
            {
                System.out.println("*B");
                this.shortpassage = shortpassage;
            }
            else
            {
               System.out.println("*C");
               System.out.println(contentType);
               this.shortpassage = null;
            }
        }
        else {
            System.out.println("*D");
            this.contentType = ContentType.ShortPassage;
            this.shortpassage = shortpassage;
        }
    }

    @ManyToOne
    public Prompt getPrompt() {
        return prompt;
    }

    public void setPrompt(Prompt prompt) {
         if(contentType != null)
        {
            System.out.println("*A");
            if(contentType == ContentType.Prompt)
            {
                System.out.println("*B");
                this.prompt = prompt;
            }
            else
            {
               System.out.println("*C");
               System.out.println(contentType);
               this.prompt = null;
            }
        }
        else {
            System.out.println("*D");
            this.contentType = ContentType.Prompt;
            this.prompt = prompt;
        }
    }

    @ManyToOne
    public ImprovingParagraph getImprovingParagraph() {
        return improvingParagraph;
    }

    public void setImprovingParagraph(ImprovingParagraph improvingParagraph) {
        this.improvingParagraph = improvingParagraph;
    }
}
