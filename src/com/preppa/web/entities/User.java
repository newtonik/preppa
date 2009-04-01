/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

/**
 *
 * @author newtonik
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Validate("required")
    private String email;
    @Column(name = "firstName")
    @Validate("required")
    private String firstName = "John";
    @Column(name = "lastName")
    @Validate("required")
    private String lastName = "Smith";

    


    private String sex;

    //@Validate("required")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;

    @NonVisual
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NonVisual
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;




    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



//
//    public User(Integer id) {
//        this.id = id;
//    }
    public  Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }


}
