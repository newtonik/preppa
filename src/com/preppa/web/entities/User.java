package com.preppa.web.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * A minimalistic UserDetails implementation providing a username only. Storing
 * a password is not necessary since the OpenID provider will do the authentication.
 *
 * @author Ulrich St&auml;rk --- newtonik
 */
@Entity
public class User implements UserDetails, Serializable
{
    private static final long serialVersionUID = 4068206679084877888L;

    private int id;

    private String username;

    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
    public GrantedAuthority[] getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for (Role role : getRoles())
        {
            authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
        }

        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

    @Transient
    public String getPassword()
    {
        return "notused";
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Transient
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Transient
    public boolean isEnabled()
    {
        return true;
    }
}
