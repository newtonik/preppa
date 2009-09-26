package com.preppa.web.entities;

import com.preppa.web.data.Gender;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import javax.persistence.UniqueConstraint;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.envers.Audited;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username", "email"})})
public class User implements UserDetails, Serializable
{
    private List<Flag> flags;
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
    private String activationcode;
    private boolean recentlyactivated;
    private Date activatedAt;
    private String passwordResetCode;
    private boolean recentlyreset;
    private UserProfile userProfile;
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
    @Audited
    @Column(unique=true, nullable = false)
    public String getEmail() {
        return email;
    }



    @Column(name = "firstName")
    @Validate("required")
    @Audited
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    @Validate("required")
    @Audited
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
    @Enumerated(EnumType.STRING)
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
    @Audited
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
    @Override
    @Field(index = Index.TOKENIZED)
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
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

    /**
     * @return the activationcode
     */
    public String getActivationcode() {
        return activationcode;
    }

    /**
     * @param activationcode the activationcode to set
     */
    public void setActivationcode(String activationcode) {
        this.activationcode = activationcode;
    }

    /**
     * @return the recentlyactivated
     */
    public boolean isRecentlyactivated() {
        return recentlyactivated;
    }

    /**
     * @param recentlyactivated the recentlyactivated to set
     */
    public void setRecentlyactivated(boolean recentlyactivated) {
        this.recentlyactivated = recentlyactivated;
    }

    /**
     * @return the activatedAt
     */
     @NonVisual
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getActivatedAt() {
        return activatedAt;
    }

    /**
     * @param activatedAt the activatedAt to set
     */
    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    /**
     * @return the passwordResetCode
     */
    public String getPasswordResetCode() {
        return passwordResetCode;
    }

    /**
     * @param passwordResetCode the passwordResetCode to set
     */
    public void setPasswordResetCode(String passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }

    /**
     * @return the recentlyreset
     */
    public boolean isRecentlyreset() {
        return recentlyreset;
    }

    /**
     * @param recentlyreset the recentlyreset to set
     */
    public void setRecentlyreset(boolean recentlyreset) {
        this.recentlyreset = recentlyreset;
    }

    /**
     * @return the userProfile
     */
   
    @OneToOne
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * @param userProfile the userProfile to set
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @OneToMany(mappedBy = "flagger")
    public List<Flag> getFlags() {
        return flags;
    }

    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }
}
