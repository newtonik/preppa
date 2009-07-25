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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

/**
 *
 * @author newtonik
 */
@Entity
@Audited
//@Table(name = "vocab", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
//@NamedQueries({ @NamedQuery(name = "Vocab.findById", query = "SELECT v FROM Vocab v WHERE v.id = :id"), @NamedQuery(name = "Vocab.findByName", query = "SELECT v FROM Vocab v WHERE v.name = :name"), @NamedQuery(name = "Vocab.findByPartofspeech", query = "SELECT v FROM Vocab v WHERE v.partofspeech = :partofspeech"), @NamedQuery(name = "Vocab.findByCreatedAt", query = "SELECT v FROM Vocab v WHERE v.createdAt = :createdAt"), @NamedQuery(name = "Vocab.findByUpdatedAt", query = "SELECT v FROM Vocab v WHERE v.updatedAt = :updatedAt")})
public class Vocab implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String partofspeech;
    private String definition;
    private String tags;
    private ExampleSentence sentence;
    private String formsentence;
    private User user;
    private Date createdAt;
    private Date updatedAt;
    private List<Tag> vlist = new LinkedList<Tag>();
    private ContentFlag status;
    private String revComment;

    public Vocab() {
    }


    
   

   

    @Id
    @NonVisual
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", length = 255, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "partofspeech", length = 255)
    public String getPartofspeech() {
        return partofspeech;
    }

    public void setPartofspeech(String partofspeech) {
        this.partofspeech = partofspeech;
    }

    @Lob
    @Column(name = "definition", length = 65535)
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Lob
    @Column(name = "tags", length = 65535)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        if (!(object instanceof Vocab)) {
            return false;
        }
        Vocab other = (Vocab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Vocab[id=" + id + "]";
    }

    /**
     * @return the sentence
     */
    @Basic(optional = false)
    @ManyToOne(targetEntity = ExampleSentence.class)
    @JoinColumn(name = "sentence_id")
    @Fetch(value = FetchMode.JOIN)
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    public ExampleSentence getSentence() {
        return sentence;
    }

    /**
     * @param sentence the sentence to set
     */
    public void setSentence(ExampleSentence sentence) {
        this.sentence = sentence;
    }

    /**
     * @return the user
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
     @Fetch(value = FetchMode.JOIN)
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
     * @return the formsentence
     */
    @Lob
    public String getFormsentence() {
        return formsentence;
    }

    /**
     * @param formsentence the formsentence to set
     */
    public void setFormsentence(String formsentence) {
        this.formsentence = formsentence;
    }

    @ManyToMany(targetEntity = Tag.class, cascade=CascadeType.ALL)
     @JoinTable(name = "Vocab_Tag",
    joinColumns = {
      @JoinColumn(name="Vocab_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="Tag_id")
    })
   public List<Tag> getTaglist() {
        return vlist;
    }

    /**
     * @param taglist the taglist to set
     */
    public void setTaglist(LinkedList<Tag> taglist) {
        this.vlist = taglist;
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
    public String getRevComment() {
        return revComment;
    }

    /**
     * @param revComment the revComment to set
     */
    public void setRevComment(String revComment) {
        this.revComment = revComment;
    }

}
