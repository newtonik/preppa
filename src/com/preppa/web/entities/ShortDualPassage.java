/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import com.preppa.web.utils.PassageType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;

/**
 *
 * @author nwt
 */
@Entity
public class ShortDualPassage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Lob
    private String tags;
    @Lob
    private String source;
    @OneToMany(cascade=CascadeType.ALL, targetEntity=Question.class)
    private List<Question> questions;
     @ManyToMany(targetEntity = Tag.class)
     @JoinTable(name = "ShortDualPassage_Tag",
    joinColumns = {
      @JoinColumn(name="ShortDualPassage_id")
        },
    inverseJoinColumns = {
      @JoinColumn(name="Tag_id")
    })
    private List<Tag> taglist;
    @Basic(optional = false)
    @NonVisual
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NonVisual
    @Basic(optional = false)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(nullable = false)
    private Boolean complete = false;
     private PassageType passagetype;
//    @OneToOne(targetEntity = Passage.class)
//    @Fetch(value = FetchMode.JOIN)
//    @JoinColumn(name = "passage1_id")
    @Lob
    private String passageone;
//    @OneToOne(targetEntity = Passage.class)
//    @Fetch(value = FetchMode.JOIN)
//    @JoinColumn(name = "passage2_id")
    @Lob
    private String passagetwo;
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
        if (!(object instanceof ShortDualPassage)) {
            return false;
        }
        ShortDualPassage other = (ShortDualPassage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.ShortDualPassages[id=" + id + "]";
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
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
     * @return the createdAt
     */
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
     * @return the passageone
     */
    public String getPassageone() {
        return passageone;
    }

    /**
     * @param passageone the passageone to set
     */
    public void setPassageone(String passageone) {
        this.passageone = passageone;
    }

    /**
     * @return the passagetwo
     */
    public String getPassagetwo() {
        return passagetwo;
    }

    /**
     * @param passagetwo the passagetwo to set
     */
    public void setPassagetwo(String passagetwo) {
        this.passagetwo = passagetwo;
    }

    /**
     * @return the taglist
     */
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
    public Boolean getComplete() {
        return complete;
    }
    public Boolean isComplete() {
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
    public PassageType getPassagetype() {
        return passagetype;
    }

    /**
     * @param passagetype the passagetype to set
     */
    public void setPassagetype(PassageType passagetype) {
        this.passagetype = passagetype;
    }
}
