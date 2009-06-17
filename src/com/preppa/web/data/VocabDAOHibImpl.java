/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Vocab;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;


/**
 *
 * @author newtonik
 */
public class VocabDAOHibImpl extends AbstractHibernateDAO<Vocab, Integer> implements VocabDAO {
    public VocabDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public Vocab findById(Integer id) {
        SQLString sqlString = new SQLString("FROM Vocab v");
        if(id != null)
        {
             sqlString.addWhereClause("v.id = '" + id + "'");
        }

        return (Vocab) findByQuery(sqlString.toString()).get(0);
    }

    public List<Vocab> findByLetter(Character lower) {
        Character upper = Character.toUpperCase(lower);
        SQLString sqlString = new SQLString("FROM Vocab v");
        if(lower != null && upper != null)
        {
             sqlString.addWhereClause("v.name LIKE '" + lower + "%' OR v.name LIKE '" + upper + "%'");
        }
        sqlString.addOrderField("name");

        return (List <Vocab>) findByQuery(sqlString.toString());
    }

    public List<Vocab> findAllOrderedByPartOfSpeech(String pos) {
        SQLString sqlString = new SQLString("FROM Vocab v");
        if(pos != null)
        {
             sqlString.addWhereClause("v.partofspeech = '" + pos + "'");
        }
        return (List<Vocab>) findByQuery(sqlString.toString());
    }

    public List<Vocab> findAllOrderedByName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Vocab> findByPartialName(String partialName) {
        //throw new UnsupportedOperationException("Not supported yet.");
        SQLString sqlString = new SQLString("FROM Vocab v");
        if(partialName != null)
        {
             sqlString.addWhereClause("v.name LIKE '" + partialName + "%'");
        }

        return (List <Vocab>) findByQuery(sqlString.toString());
    }

    public List<Vocab> findByName(String name) {
        //throw new UnsupportedOperationException("Not supported yet.");
        SQLString sqlString = new SQLString("FROM Vocab v");
        if(name != null)
        {
             sqlString.addWhereClause("v.name = '" + name + "'");
        }

        return (List <Vocab>) findByQuery(sqlString.toString());
    }
}
