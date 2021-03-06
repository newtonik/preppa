/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.envers.Audited;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Indexed;


/**
 *
 * @author newtonik
 */
@Entity
//@NamedQueries({ @NamedQuery(name = "Announcements.findByStartsAt", query = "SELECT a FROM Announcements a WHERE a.startsAt = :startsAt"), @NamedQuery(name = "Announcements.findByEndsAt", query = "SELECT a FROM Announcements a WHERE a.endsAt = :endsAt"), @NamedQuery(name = "Announcements.findByCreatedAt", query = "SELECT a FROM Announcements a WHERE a.createdAt = :createdAt"), @NamedQuery(name = "Announcements.findByUpdatedAt", query = "SELECT a FROM Announcements a WHERE a.updatedAt = :updatedAt"), @NamedQuery(name = "Announcements.findByLocationId", query = "SELECT a FROM Announcements a WHERE a.locationId = :locationId")})
public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private String title;
    @Lob
    @Validate("required")
    @Column(name = "message")
    private String message;
    @Column(name = "starts_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startsAt;
    @Column(name = "ends_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endsAt;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "location_id")
    private Integer locationId;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Fetch(value = FetchMode.JOIN)
    @Audited
    public User getUser()
    {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Announcement() {
    }

    public Announcement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.preppa.web.entities.Announcements[id=" + id + "]";
    }



}
