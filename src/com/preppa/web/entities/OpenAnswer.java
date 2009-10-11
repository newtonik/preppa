package com.preppa.web.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;



/**
 *
 * @author nwt
 */
@Entity
@Indexed
public class OpenAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String answer;
    private Date createdAt;
    private Date updatedAt;
    private Integer votes;
    private User user;
    private List<Flag> flags = new ArrayList<Flag>();

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
        if (!(object instanceof OpenAnswer)) {
            return false;
        }
        OpenAnswer other = (OpenAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.OpenAnswers[id=" + id + "]";
    }

    /**
     * @return the answer
     */
    @Basic(optional = false)
    @Lob
    @Audited
    @Field(index=Index.TOKENIZED, store=Store.NO)
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
     * @return the votes
     */
    @Audited
    public Integer getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    /**
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Audited
    @IndexedEmbedded(depth=1, prefix ="ownedBy_")
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
     * @return the flags
     */
    @OneToMany(mappedBy = "openAnswer")
    public List<Flag> getFlags() {
        return flags;
    }

    /**
     * @param flags the flags to set
     */
    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }

}
