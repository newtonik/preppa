package com.preppa.web.data;

import com.preppa.web.entities.OpenQuestion;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
public class OpenQuestionDAOHimpl extends AbstractHibernateDAO<OpenQuestion, Long> implements OpenQuestionDAO{

   public OpenQuestionDAOHimpl(Logger logger, Session session)
    {
        super(logger, session);
    }
    @Override
    public OpenQuestion findById(Long id) {
       SQLString sqlString = new SQLString("FROM OpenQuestion oq");
        if(id != null)
        {
             sqlString.addWhereClause("oq.id = '" + id + "'");
        }

        return (OpenQuestion) findByQuery(sqlString.toString()).get(0);
    }

}
