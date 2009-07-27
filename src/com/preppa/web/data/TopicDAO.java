/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nwt
 */
public interface TopicDAO  extends GenericDAO <Topic, Integer>{

    public List<Topic> findByPartialName(String partial);
     public Integer findSizeByName(String name, Testsubject subject);
    public List<Topic> findByPartialName(String partial, Testsubject subject);
    public Topic findById(Integer id);
    public Integer findSizeByPartialName(String partial, Testsubject subject);

}
