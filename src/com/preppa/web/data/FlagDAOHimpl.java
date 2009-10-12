package com.preppa.web.data;

import com.preppa.web.entities.Flag;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
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
             //sqlString.addWhereClause("f.contentType = '" + t.ordinal() + "'");
             //sqlString.addWhereClause("f.contentType = '" + t + "'");
        }



        //return findByQuery(sqlString.toString());
        return findByCriteria(Restrictions.eq("contentType", t));
    }

    @Override
    public List<Flag> FindAllByFlagType(ContentType t, ContentFlag type) {
     
        if(t == null) {
            return findByCriteria(Restrictions.eq("flagtype", type));
        }
        else
        {
               return findByCriteria(Restrictions.eq("contentType", t), Restrictions.eq("flagtype", type));
        }
        
    }

    @Override
    public List<Flag> FindAllByFlagStatus(ContentType t, FlagStatus status) {
           
        if(t == null) {
            return findByCriteria(Restrictions.eq("flagtype", status));
        }
        else {

            return findByCriteria(Restrictions.eq("contentType", t), Restrictions.eq("status", status));
        }
    }


}
