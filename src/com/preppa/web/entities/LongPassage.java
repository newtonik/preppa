/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.PassageType;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;


/**
 *
 * @author nwt
 */
@Entity
public class LongPassage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String sources;
    private List<Question> questions = new ArrayList<Question>();
    private List<Tag> taglist = new ArrayList<Tag>();
    private Date createdAt;
    private Date updatedAt;
    private Boolean complete = false;
    private PassageType passagetype;
    private String summary;
    private User user;
    private String passage;
    private String title;
    private Integer numQuestions = 0;
    private ContentFlag status;
    private String revComment;
    private User updatedBy;
    private List<Flag> flags = new ArrayList<Flag>();
    private List<ReviewComment> reviewcomments;

    @Id
    @NonVisual
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
     * @return the sources
     */
    @Lob
    @Audited
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
     * @return the passage
     */
    @Lob
    @Audited
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
    @Audited
    public String getTitle() {
        return title;
    }

    /**
     * @return the taglist
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "LongPassage_Tag", joinColumns = {@JoinColumn(name = "longpassage_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
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
     * @return the complete
     */
    @Column(nullable = false)
    @Audited
    public Boolean getComplete() {
        return complete;
    }
    
    /**
     * @param complete the complete to set
     */
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    /**
     * @return the passagetype
     */
    @Audited
    public PassageType getPassagetype() {
        return passagetype;
    }

    /**
     * @param passagetype the passagetype to set
     */
    public void setPassagetype(PassageType passagetype) {
        this.passagetype = passagetype;
    }

    /**
     * @return the numQuestions
     */
    @Column(nullable = false, columnDefinition = "bigint(20) default 0")
    @Audited
    public Integer getNumQuestions() {
        return numQuestions;
    }

    /**
     * @param numQuestions the numQuestions to set
     */
    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions = numQuestions;
    }
        /**
     * @return the questions
     */
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Question.class)
    @Audited
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @return the user
     */
     @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
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
     * @return the summary
     */
    @Lob
    @Audited
    @Column(nullable=false)
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
     * @return the updatedBy
     */
    @Audited
    @ManyToOne
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
     * @return the flags
     */
    @OneToMany(mappedBy = "longpassage", cascade=CascadeType.ALL, targetEntity=Flag.class)
    @Audited
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Flag> getFlags() {
        return flags;
    }

    /**
     * @param flags the flags to set
     */
    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

    @OneToMany(targetEntity=ReviewComment.class, cascade=CascadeType.ALL)
    @JoinTable(name="LongPassage_ReviewComment",
        joinColumns= {
        @JoinColumn(name="longpassage_id")
        },
        inverseJoinColumns = {
        @JoinColumn(name="comment_id", unique=true)
    }
    )
    @OrderBy("createdAt ASC")
    public List<ReviewComment> getReviewcomments() {
        return reviewcomments;
    }

    /**
     * @param reviewcomments the reviewcomments to set
     */
    public void setReviewcomments(List<ReviewComment> reviewcomments) {
        this.reviewcomments = reviewcomments;
    }


}
