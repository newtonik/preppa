    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.entities;

import com.preppa.web.data.Gender;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"loginId"})})
public class User implements Serializable {

    static public final String ADMIN_LOGINID = "admin";
    private long id;
    private String loginId;
    private String email;
    private String password;
    private String password_confirmation;
    private String firstName;
    private String lastName;
    private Date dob;
    private Gender gender;
    private Date createdAt;
    private Date updatedAt;
    //@Validate("required")

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 20)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the loginId
     */
    @Column(length = 15, nullable = false, unique = true)
    @Validate("required")
    public String getLoginId() {
        return loginId;
    }


    /**
     * @return the email
     */
    @Column(unique = true, nullable = false)
    @Validate("required")
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    @Column(length = 32)
    @Validate("required")
    public String getPassword() {
        return password;
    }

    /**
     * @return the _password_confimration
     */
    @Validate("required")
    public String getPassword_confirmation() {
        return password_confirmation;
    }

    @Column(name = "firstName")
    @Validate("required")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    @Validate("required")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the dob
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the createdAt
     */
    @NonVisual
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        if (this.createdAt == null) {
            this.createdAt = createdAt;
        }
    }

    /**
     * @return the updatedAt
     */
    @NonVisual
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param password_confimration the _password_confimration to set
     */
    public void setPassword_confirmation(String password_confimration) {
        this.password_confirmation = password_confimration;
    }
}
