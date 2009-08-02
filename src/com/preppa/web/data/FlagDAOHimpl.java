package com.preppa.web.data;

import com.preppa.web.entities.Flag;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class FlagDAOHimpl  extends AbstractHibernateDAO<Flag, Integer> implements FlagDAO {

   public FlagDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    @Override
    public List<Flag> FindAllByContentType(ContentType t) {

         SQLString sqlString = new SQLString("FROM Flag f");
        if(t != null)
        {
             sqlString.addWhereClause("f.contentType = '" + t + "'");
        }
       

        return findByCriteria(Restrictions.eq("contentType", t));
    }

    @Override
    public List<Flag> FindAllByFlagType(ContentType t, ContentFlag type) {
           SQLString sqlString = new SQLString("FROM Flag f");
        if(t != null)
        {
             sqlString.addWhereClause("f.contentType = '" + t + "'");
        }


        return findByCriteria(Restrictions.eq("contentType", t), Restrictions.eq("flagtype", type));
    }



}