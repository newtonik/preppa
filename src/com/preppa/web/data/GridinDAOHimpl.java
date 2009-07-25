package com.preppa.web.data;

import com.preppa.web.entities.Gridin;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class GridinDAOHimpl extends AbstractHibernateDAO<Gridin, Integer> implements GridinDAO {

    public GridinDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public Gridin findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Gridin g");
        if(id != null)
        {
             sqlString.addWhereClause("g.id = '" + id + "'");
        }

        return (Gridin) findByQuery(sqlString.toString()).get(0);
    }

}
