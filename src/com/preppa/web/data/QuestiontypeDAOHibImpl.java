/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Testsubject;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class QuestiontypeDAOHibImpl extends AbstractHibernateDAO<Questiontype, Integer> implements  QuestiontypeDAO{


    public QuestiontypeDAOHibImpl(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public List<Questiontype> findByTestsubject(Testsubject testsubject) {
        SQLString sqlString = new SQLString("FROM Questiontype q");
        if(testsubject != null)
        {

                 sqlString.addWhereClause("q.testsubject = '" + testsubject.getId() + "'");


        }

        List<Questiontype> result =  findByQuery(sqlString.toString());
        
        return result;

    }

    @Override
    public Questiontype findById(Integer id) {
           SQLString sqlString = new SQLString("FROM Questiontype q");
        if(id > 0)
        {

                 sqlString.addWhereClause("q.id = '" + id + "'");
        }

        return  (Questiontype) findByQuery(sqlString.toString()).get(0);
    }
}
