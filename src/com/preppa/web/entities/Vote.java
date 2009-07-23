package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
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
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;



/**
 *
 * @author nwt
 */
@javax.persistence.Entity
@javax.persistence.Table(name="Vote")
@Table(appliesTo="Vote", indexes = {
    @Index(name="contType_contId_uid", columnNames={"contentType","contentId", "user_id"}),
    @Index(name="contType_userId", columnNames={"contentType","user_id"}),
    @Index(name="content_source", columnNames={"contentType","contentId","source"})})
public class Vote implements Serializable {
    private Long id;
    private List<Article> articles;
    private static final long serialVersionUID = 1L;
    private Integer value;
    private Date createdAt;
    private User user;
    private String source;
    private String contentType;
    private Long contentId;

    
     @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
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
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Vote[id=" + id + "]";
    }

    
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the up
     */
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    /**
     * @param up the up to set
     */
    public void setUp(Integer value) {
        this.value = value;
    }

    @ManyToMany
    @JoinTable(name = "Article_Vote",
    joinColumns = {
      @JoinColumn(name="article_id")
        }
    )
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return the createdAt
     */
    @Column(name = "created_at", nullable = false)
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
     * @return the user
     */

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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the contentId
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentId the contentId to set
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

}
