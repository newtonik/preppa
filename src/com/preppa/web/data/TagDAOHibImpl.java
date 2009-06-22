package com.preppa.web.data;

import com.preppa.web.entities.Tag;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class TagDAOHibImpl extends AbstractHibernateDAO<Tag , Integer> implements TagDAO
{

    public TagDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }
    @Override
    public Tag findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Tag t");
        if(id != null)
        {
             sqlString.addWhereClause("t.id = '" + id + "'");
        }

        return (Tag) findByQuery(sqlString.toString()).get(0);
    }

    @Override
    public Tag findByName(String name) {
      SQLString sqlString = new SQLString("FROM Tag t");
        if(name != null && name.length() > 0)
        {
             sqlString.addWhereClause("t.name = '" + name + "'");
        }

        return (Tag) findByQuery(sqlString.toString()).get(0);
    }
    @Override
    public List<Tag> findByPartialName(String partialName) {
        SQLString sqlString = new SQLString("FROM Tag t");
        if(partialName != null && partialName.length() > 0)
        {
             sqlString.addOrderField("t.name");
                 sqlString.addWhereClause("t.name LIKE '%" + partialName + "%'");
        }

        return  findByQuery(sqlString.toString());
    
    }

}
