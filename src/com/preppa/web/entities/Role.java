package com.preppa.web.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;
import org.springframework.security.GrantedAuthority;

/**
 * A GrantedAuthority (aka Role) implementation. hashCode, equals and compareTo are
 * a bit messy (auto-generated), you might want to change this.
 *
 * @author Ulrich St&auml;rk -- newtonik
 */
@Entity
@Audited
public class Role implements GrantedAuthority, Serializable
{
    private static final long serialVersionUID = -117212611936641518L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String authority;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    @Override
    public int hashCode()
    {
        return authority.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() == String.class) return obj.equals(authority);
        if (getClass() != obj.getClass()) return false;
        final Role other = (Role) obj;
        if (authority == null)
        {
            if (other.authority != null) return false;
        }
        else if (!authority.equals(other.authority)) return false;
        return true;
    }

    public int compareTo(Object o)
    {
        if (this == o) return 0;
        if (o == null) return -1;
        if (o.getClass() == String.class) return authority.compareTo((String) o);
        if (getClass() != o.getClass()) return -1;
        final Role other = (Role) o;
        if (authority == null)
        {
            if (other.authority != null) return 1;
        }
        else
            return authority.compareTo(other.authority);
        return -1;
    }
}
