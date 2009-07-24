/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import com.preppa.web.utils.ContentFlag;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic; 
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
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
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private String title;
    private List<Topic> topics = new LinkedList<Topic>();
    private String body;
    private String teaser;
    private Testsubject testsubject;
    private String sources;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private List<Tag> taglist = new LinkedList<Tag>();
    private Set<Vote> votes;
    private Integer voteScore;
    private ContentFlag status;

    @Id
    @NonVisual
    @Basic(optional = false)
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 20)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Validate("required")
    @Audited
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the testsubject
     */
    @Basic(optional = true)
    @ManyToOne(targetEntity=Testsubject.class)
    @JoinColumn(name="testsubject_id")
      @Audited
    public Testsubject getTestsubject() {
        return testsubject;
    }

    /**
     * @param testsubject the testsubject to set
     */
    public void setTestsubject(Testsubject testsubject) {
        this.testsubject = testsubject;
    }
    @Lob
    @Validate("required")
    @Audited
    @Field(index=Index.TOKENIZED, store=Store.NO)
    @Column( nullable = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Lob
    @Audited
    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }
        /**
     * @return the links
     */
   
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

    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    @Audited
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Articles[id=" + id + "]";
    }

    /**
     * @return the teaser
     */
    @NonVisual
    @Lob
    public String getTeaser() {
        return teaser;
    }

    /**
     * @param teaser the teaser to set
     */
    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }


    /**
     * @return the topics
     */
     @ManyToMany(targetEntity=Topic.class)
    @JoinTable(name = "ArticleTopic",
    joinColumns = {
      @JoinColumn(name="articleId")
        },
    inverseJoinColumns = {
      @JoinColumn(name="topicId")
    })
    @Audited
    @IndexedEmbedded
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
     * @return the taglist
     */
    @ManyToMany(targetEntity = Tag.class)
     @JoinTable(name = "Article_Tag",
    joinColumns = {
      @JoinColumn(name="Article_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="Tag_id")
    })
    @Audited
    @IndexedEmbedded
    public List<Tag> getTaglist() {
        return taglist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(List<Tag> tags) {
        this.taglist = tags;
    }

    /**
     * @return the votes
     */
   
    @ManyToMany(mappedBy = "articles")
    public Set<Vote> getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    /**
     * @return the voteScore
     */
    public Integer getVoteCount() {
        return voteScore;
    }

    /**
     * @param voteScore the voteScore to set
     */
    public void setVoteCount(Integer voteCount) {
        this.voteScore = voteCount;
    }

    /**
     * @return the status
     */
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


}
