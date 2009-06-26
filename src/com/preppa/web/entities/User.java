package com.preppa.web.entities;

import com.preppa.web.data.Gender;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * A minimalistic UserDetails implementation providing a username only. Storing
 * a password is not necessary since the OpenID provider will do the authentication.
 *
 * @author  newtonik
 */
@Entity
public class User implements UserDetails, Serializable
{
    private static final long serialVersionUID = 4068206679084877888L;

    private int id;

    private String username;
    private String password;
    private String loginId;
    private String email;
    private String firstName;
    private String lastName;
    private Date dob;
    private Gender gender;
    private Date createdAt;
    private Date updatedAt;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<Role> roles;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the email
     */
    @Column(unique=true, nullable = false)
    @Validate("required")
    public String getEmail() {
        return email;
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

    @ManyToMany
    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    @Transient
    @Override
    public GrantedAuthority[] getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for (Role role : getRoles())
        {
            authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
        }

        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

  
    @Override
    public String getPassword()
    {
        return password;
    }
    @Column(unique=true, nullable = false)
    @Validate("required")
    @Override
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @param accountNonExpired the accountNonExpired to set
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * @param accountNonLocked the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * @param credentialsNonExpired the credentialsNonExpired to set
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
